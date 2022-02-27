package com.xiaokw.server.service;

import com.xiaokw.server.entity.AjaxResultPage;
import com.xiaokw.server.entity.TEmployee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface ITEmployeeService extends IService<TEmployee> {

    /**
     * 获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    AjaxResultPage getEmployeeByPage(Integer currentPage, Integer size, TEmployee employee, LocalDate[] beginDateScope);

}
