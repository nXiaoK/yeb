package com.xiaokw.server.controller;

import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TAdmin;
import com.xiaokw.server.service.ITAdminService;
import com.xiaokw.server.util.FastDFSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Api(tags = "账号管理")
@RestController
public class AdminInfoController {

    @Autowired
    private ITAdminService adminService;

    @ApiOperation("更新当前用户信息")
    @PutMapping("admin/info")
    public AjaxResult updateAdmin(@RequestBody TAdmin admin, Authentication authentication) {
        if (adminService.updateById(admin)) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }

    @ApiOperation("更新密码")
    @PutMapping
    public AjaxResult updateAdminPassword(@RequestBody Map<String, Object> info) {
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(adminId, pass, oldPass);
    }

    @ApiOperation("上传头像")
    @PostMapping("/admin/userface")
    public AjaxResult updateAdminUserFace(@RequestPart @RequestParam MultipartFile file, Integer id, Authentication authentication) {
        String[] filePath = FastDFSUtils.upload(file);
        String url = FastDFSUtils.getTrackUrl() + filePath[0] + "/" + filePath[1];
        return adminService.updateAdminUserFace(url, id, authentication);
    }
}
