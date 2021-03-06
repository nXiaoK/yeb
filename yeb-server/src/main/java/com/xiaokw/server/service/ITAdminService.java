package com.xiaokw.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TAdmin;
import com.xiaokw.server.entity.TRole;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface ITAdminService extends IService<TAdmin> {

    AjaxResult login(String username, String password, String code, HttpServletRequest request);

    TAdmin getAdminByUserName(String name);

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<TRole> getRoles(Integer adminId);

    List<TAdmin> getAllAdmins(String keywords);

    AjaxResult updateAdminRole(Integer adminId, Integer[] rids);

    AjaxResult updateAdminPassword(Integer adminId, String pass, String oldPass);

    AjaxResult updateAdminUserFace(String url, Integer id, Authentication authentication);
}
