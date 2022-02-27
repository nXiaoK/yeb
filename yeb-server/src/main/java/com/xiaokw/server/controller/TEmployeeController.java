package com.xiaokw.server.controller;


import com.xiaokw.server.entity.AjaxResultPage;
import com.xiaokw.server.entity.TEmployee;
import com.xiaokw.server.service.ITEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Api(tags = "员工管理")
@RestController
@RequestMapping("/employee/basic")
public class TEmployeeController {

    @Autowired
    private ITEmployeeService employeeService;

    @ApiOperation("获取所有员工(分页)")
    @GetMapping
    public AjaxResultPage getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size,
                                      TEmployee employee, LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }
}

