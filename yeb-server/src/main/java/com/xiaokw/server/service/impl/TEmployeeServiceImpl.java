package com.xiaokw.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.AjaxResultPage;
import com.xiaokw.server.entity.TEmployee;
import com.xiaokw.server.mapper.TEmployeeMapper;
import com.xiaokw.server.service.ITEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
            // 发送信息
            TEmployee employee1 = employeeMapper.getEmployee(employee.getId()).get(0);
            rabbitTemplate.convertAndSend("mail.welcome", employee1);
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @Override
    public List<TEmployee> getAllEmployee() {
        return employeeMapper.getEmployee(null);
    }
}
