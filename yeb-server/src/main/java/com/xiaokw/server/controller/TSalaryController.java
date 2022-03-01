package com.xiaokw.server.controller;


import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TSalary;
import com.xiaokw.server.service.ITSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Api(tags = "工资套账管理")
@RestController
@RequestMapping("/salary/sob")
public class TSalaryController {

    @Autowired
    private ITSalaryService salaryService;

    @ApiOperation("获取所有工资账套")
    @GetMapping
    public List<TSalary> getAllSalaries(){
        return salaryService.list();
    }


    @ApiOperation("删除工资套账")
    @DeleteMapping("/{id}")
    public AjaxResult deleteSalary(@PathVariable Integer id){
        if (salaryService.removeById(id)) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @ApiOperation("添加工资套账")
    @PostMapping
    public AjaxResult addSalary(@RequestBody TSalary tSalary){
        if (salaryService.save(tSalary)) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }


    @ApiOperation("更新工资账套")
    @PutMapping
    public AjaxResult updateSalary(@RequestBody TSalary tSalary){
        if (salaryService.updateById(tSalary)) {
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }

}

