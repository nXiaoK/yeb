package com.xiaokw.server.controller;


import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TAdmin;
import com.xiaokw.server.entity.TRole;
import com.xiaokw.server.service.ITAdminService;
import com.xiaokw.server.service.ITRoleService;
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
@Api(tags = "操作员管理")
@RestController
@RequestMapping("/system/admin")
public class TAdminController {
    @Autowired
    private ITAdminService adminService;
    @Autowired
    private ITRoleService roleService;

    @ApiOperation("获取所有操作员")
    @GetMapping
    public List<TAdmin> getAllAdmins(String keywords) {
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation("更新操作员")
    @PutMapping
    public AjaxResult updateAdmin(@RequestBody TAdmin admin) {
        if (adminService.updateById(admin)) {
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }

    @ApiOperation("删除操作员")
    @DeleteMapping("/{id}")
    public AjaxResult deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/roles")
    public List<TRole> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation("更新操作员角色")
    @PutMapping("/role")
    public AjaxResult updateAdminRole(Integer adminId, Integer... rids) {
        return adminService.updateAdminRole(adminId, rids);
    }
}

