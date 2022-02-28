package com.xiaokw.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaokw.server.constant.MailConstants;
import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.AjaxResultPage;
import com.xiaokw.server.entity.TEmployee;
import com.xiaokw.server.entity.TMailLog;
import com.xiaokw.server.mapper.TEmployeeMapper;
import com.xiaokw.server.mapper.TMailLogMapper;
import com.xiaokw.server.service.ITEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Service
public class TEmployeeServiceImpl extends ServiceImpl<TEmployeeMapper, TEmployee> implements ITEmployeeService {

    @Autowired
    private TEmployeeMapper employeeMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TMailLogMapper mailLogMapper;
    @Override
    public AjaxResultPage getEmployeeByPage(Integer currentPage, Integer size, TEmployee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<TEmployee> page = new Page<>(currentPage, size, true);
        IPage<TEmployee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        AjaxResultPage ajaxResultPage = new AjaxResultPage(employeeByPage.getTotal(), employeeByPage.getRecords());
        return ajaxResultPage;
    }

    /**
     * 获取工号
     *
     * @return
     */
    @Override
    public AjaxResult getMaxWorkID()    {
        Object o = employeeMapper.selectMaps(new QueryWrapper<TEmployee>().select("max(workID)")).get(0).get("max(workID)");
        int i = Integer.parseInt(o.toString()) + 1;
        AjaxResult success = AjaxResult.success();
        success.put(AjaxResult.DATA_TAG, String.format("%08d", i));
        return success;
    }

    @Override
    public AjaxResult addEmp(TEmployee employee) {
        employee.setId(null);
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00"); // 保留两位小数
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (employeeMapper.insert(employee) == 1) {
            // 数据库记录发送的消息
            String msgId = UUID.randomUUID().toString();
            TMailLog mailLog = new TMailLog();
            mailLog.setMsgId(msgId);
            mailLog.setStatus(0);
            mailLog.setEid(employee.getId());
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLog.setCount(0); // 重试次数
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT)); // 这里表示时间累加几分钟
            mailLogMapper.insert(mailLog);
            // 发送信息
            TEmployee employee1 = employeeMapper.getEmployee(employee.getId()).get(0);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, employee1, new CorrelationData(msgId));
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @Override
    public List<TEmployee> getAllEmployee() {
        return employeeMapper.getEmployee(null);
    }

    @Override
    public List<TEmployee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }
}
