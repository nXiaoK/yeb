package com.xiaokw.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaokw.server.constant.MailConstants;
import com.xiaokw.server.entity.TEmployee;
import com.xiaokw.server.entity.TMailLog;
import com.xiaokw.server.mapper.TEmployeeMapper;
import com.xiaokw.server.service.ITEmployeeService;
import com.xiaokw.server.service.ITMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 邮件发送定时任务
 */
@Component
public class MailTask {
    @Autowired
    private ITMailLogService mailLogService;
    @Autowired
    private ITEmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 邮件发送定时任务
     * 10s执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask() {
        List<TMailLog> list = mailLogService.list(new QueryWrapper<TMailLog>().eq("status", MailConstants.DELIVERING).lt("tryTime", LocalDateTime.now()));
        list.forEach(mailLog -> {
            // 如果重试次数超过3次，更新状态为投递失败，不再重试
            if (3 <= mailLog.getCount()) {
                mailLogService.update(new UpdateWrapper<TMailLog>().set("status", MailConstants.FAILURE).eq("msgId", mailLog.getMsgId()));
            }
            // 累加重试次数
            mailLogService.update(new UpdateWrapper<TMailLog>().set("count", mailLog.getCount() + 1).set("updateTime", LocalDateTime.now()).set("tryTime", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT)).eq("msgId",mailLog.getMsgId()));
            // 获取员工
            TEmployee employee = employeeService.getEmployee(mailLog.getEid()).get(0);
            // 发送消息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, employee, new CorrelationData(mailLog.getMsgId()));
        });

    }
}
