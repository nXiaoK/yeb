package com.xiaokw.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaokw.server.config.security.component.JwtTokenUtil;
import com.xiaokw.server.entity.AjaxResult;
import com.xiaokw.server.entity.TAdmin;
import com.xiaokw.server.entity.TAdminRole;
import com.xiaokw.server.entity.TRole;
import com.xiaokw.server.mapper.TAdminMapper;
import com.xiaokw.server.mapper.TAdminRoleMapper;
import com.xiaokw.server.mapper.TRoleMapper;
import com.xiaokw.server.service.ITAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaokw.server.util.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Service
public class TAdminServiceImpl extends ServiceImpl<TAdminMapper, TAdmin> implements ITAdminService {

    @Autowired
    @Lazy // 使用懒加载解决循环依赖问题 这是springboot2.3以后存在的问题
    private UserDetailsService userDetailsService;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TAdminMapper adminMapper;
    @Autowired
    private TRoleMapper roleMapper;
    @Value("${yeb.captchaOnOff}")
    private Boolean captchaOnOff;
    @Autowired
    private TAdminRoleMapper adminRoleMapper;


    @Value("${jwt.tokenHead}")  //JWT 负载中拿到开头
    private String tokenHead;

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public AjaxResult login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (captchaOnOff && (!StringUtils.hasLength(captcha) || !captcha.equalsIgnoreCase(code))) {
            return AjaxResult.error("验证码输入错误，请重新输入");
        }
        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return AjaxResult.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return AjaxResult.error("账号被禁用，请联系管理员");
        }
        //更新security登录用户对象  这里的操作对应LoginController中的getAdminInfo(Principal principal)方法
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return AjaxResult.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param name
     * @return
     */
    @Override
    public TAdmin getAdminByUserName(String name) {
        // 根据username查询，并确认用户是否被禁用
        return adminMapper.selectOne(new QueryWrapper<TAdmin>().eq("username", name).eq("enabled", true));
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<TRole> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    @Override
    public List<TAdmin> getAllAdmins(String keywords) {

        return adminMapper.getAllAdmins(AdminUtil.getCurrentAdmin().getId(), keywords);
    }

    /**
     * 更新操作员角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional // 表示事物
    public AjaxResult updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<TAdminRole>().eq("adminId", adminId));
        return adminRoleMapper.addAdminRole(adminId, rids) == rids.length ? AjaxResult.success("更新成功") : AjaxResult.error("更新失败");

    }

    @Override
    public AjaxResult updateAdminPassword(Integer adminId, String pass, String oldPass) {
        TAdmin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 判断旧密码是否正确
        if (encoder.matches(oldPass, admin.getPassword())) {
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if (result == 1) {
                return AjaxResult.success("更新成功");
            }
        }
        return AjaxResult.error("更新失败");

    }

    @Override
    public AjaxResult updateAdminUserFace(String url, Integer id, Authentication authentication) {
        TAdmin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int result = adminMapper.updateById(admin);
        if (1 == result) {
            TAdmin principal = (TAdmin) authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            AjaxResult success = AjaxResult.success("更新成功");
            success.put("url", url);
            return success;
        }
        return AjaxResult.error("更新失败");
    }


}
