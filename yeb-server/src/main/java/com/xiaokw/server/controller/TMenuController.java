package com.xiaokw.server.controller;


import com.xiaokw.server.entity.TMenu;
import com.xiaokw.server.service.ITAdminService;
import com.xiaokw.server.service.ITMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/system/cfg")
public class TMenuController {

    @Autowired
    private ITMenuService menuService;


    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<TMenu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }
}

