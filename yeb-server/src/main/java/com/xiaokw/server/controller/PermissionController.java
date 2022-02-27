package com.xiaokw.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TMenu;
import com.xiaokw.server.entity.TMenuRole;
import com.xiaokw.server.entity.TRole;
import com.xiaokw.server.service.ITMenuRoleService;
import com.xiaokw.server.service.ITMenuService;
import com.xiaokw.server.service.ITRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {

    @Autowired
    private ITRoleService roleService;
    @Autowired
    private ITMenuService menuService;
    @Autowired
    private ITMenuRoleService menuRoleService;

    @ApiOperation("获取所有角色")
    @GetMapping
    public List<TRole> getAllRoles() {
        return roleService.list();
    }

    @PostMapping
    @ApiOperation("添加角色")
    public AjaxResult addRole(@RequestBody TRole role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public AjaxResult deleteRole(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping
    public AjaxResult deleteRoleByIds(Integer... ids) {
        if (roleService.removeByIds(Arrays.asList(ids))) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<TMenu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid) {
        return menuRoleService
                .list(new QueryWrapper<TMenuRole>()
                        .eq("rid", rid))
                .stream()
                // 查询到的是List<TMenuRole> 集合 所以需要转为List<Integer>
                .map(TMenuRole::getMid)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping
    public AjaxResult updateMenuRole(Integer rid, Integer... mids) {
        if (menuRoleService.updateMenuRole(rid, mids)) {
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }

}
