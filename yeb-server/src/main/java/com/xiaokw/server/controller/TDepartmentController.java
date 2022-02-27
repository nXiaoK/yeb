package com.xiaokw.server.controller;


import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TDepartment;
import com.xiaokw.server.service.ITDepartmentService;
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
@Api(tags = "部门管理")
@RestController
@RequestMapping("/system/basic/department")
public class TDepartmentController {

    @Autowired
    private ITDepartmentService departmentService;

    @ApiOperation("获取所有部门")
    @GetMapping
    public List<TDepartment> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @ApiOperation("添加部门")
    @PostMapping
    public AjaxResult addDep(@RequestBody TDepartment department) {

        if (departmentService.addDep(department)) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public AjaxResult addDep(@PathVariable Integer id) {
        Integer result = departmentService.deleteDep(id);
        if (result == -2) {
            return AjaxResult.error("删除失败，该部门下还有子部门");
        }
        if (result == -1) {
            return AjaxResult.error("删除失败，该部门下存在成员");
        }
        return AjaxResult.success("删除成功");
    }
}

