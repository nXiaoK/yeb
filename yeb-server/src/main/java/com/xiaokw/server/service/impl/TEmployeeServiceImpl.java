package com.xiaokw.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaokw.server.entity.AjaxResultPage;
import com.xiaokw.server.entity.TEmployee;
import com.xiaokw.server.mapper.TEmployeeMapper;
import com.xiaokw.server.service.ITEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Service
public class TEmployeeServiceImpl extends ServiceImpl<TEmployeeMapper, TEmployee> implements ITEmployeeService {

    @Autowired
    private TEmployeeMapper employeeMapper;

    @Override
    public AjaxResultPage getEmployeeByPage(Integer currentPage, Integer size, TEmployee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<TEmployee> page = new Page<>(currentPage, size,true);
        IPage<TEmployee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        AjaxResultPage ajaxResultPage = new AjaxResultPage(employeeByPage.getTotal(), employeeByPage.getRecords());
        return ajaxResultPage;
    }
}
