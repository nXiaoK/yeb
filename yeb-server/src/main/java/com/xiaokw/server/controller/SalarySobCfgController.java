package com.xiaokw.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.AjaxResultPage;
import com.xiaokw.server.entity.TEmployee;
import com.xiaokw.server.entity.TSalary;
import com.xiaokw.server.service.ITEmployeeService;
import com.xiaokw.server.service.ITSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ITSalaryService salaryService;
    @Autowired
    private ITEmployeeService employeeService;

    @ApiOperation("获取所有工资账套")
    @GetMapping
    public List<TSalary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation("获取所有员工账套")
    @GetMapping("/empWithSalary")
    public AjaxResultPage getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeWithSalary(currentPage, size);
    }

    @ApiOperation("更新员工账套")
    @PutMapping
    public AjaxResult updateEmployeeSalary(Integer eid, Integer sid) {
        if (employeeService.update(new UpdateWrapper<TEmployee>().set("salaryId", sid).eq("id", eid))) {
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }
}
