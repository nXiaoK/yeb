package com.xiaokw.server.controller;

import com.xiaokw.server.entity.AdminLoginParam;
import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TAdmin;
import com.xiaokw.server.service.ITAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录
 */
@Api(tags = "登录")
@RestController
public class LoginController {

    @Autowired
    private ITAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(),adminLoginParam.getCode(), request);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public AjaxResult logout() {
        return AjaxResult.success("注销成功！");
    }

    @ApiOperation("获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public TAdmin getAdminInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        TAdmin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        // 设置角色列表
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }
}
