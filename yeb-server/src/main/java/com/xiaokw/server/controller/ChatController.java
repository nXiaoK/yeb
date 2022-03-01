package com.xiaokw.server.controller;

import com.xiaokw.server.entity.TAdmin;
import com.xiaokw.server.service.ITAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 在线聊天
 */
@Api("在线聊天")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ITAdminService adminService;

    @ApiOperation("获取所有操作员")
    @GetMapping("/admin")
    public List<TAdmin> getAllAdmins(String keywords) {
        return adminService.getAllAdmins(keywords);
    }
}
