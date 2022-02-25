package com.xiaokw.server.config.security.component;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaokw.server.constant.HttpStatus;
import com.xiaokw.server.entity.AjaxResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录或者token失效时访问接口时，自定义的返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        AjaxResult ajaxResult = AjaxResult.error("尚未登录，请登录！");
        ajaxResult.put(AjaxResult.CODE_TAG, HttpStatus.UNAUTHORIZED);
        writer.print(JSON.toJSONString(ajaxResult));
        writer.flush();
        writer.close();
    }
}
