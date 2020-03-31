package com.tang4j.security.handler;

import com.tang4j.core.support.http.HttpCode;
import com.tang4j.core.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 认证失败处理类
 * 在访问一个受保护的资源，用户没有通过登录认证
 * @Author: fjy
 * @Date: 2019/11/06
 */
@Slf4j
@Component
public class MyAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info(MyAuthenticationEntryPointHandler.class.getName(), HttpCode.AUTHENTICATION_FAILED);
        ResponseUtil.out(httpServletResponse, ResponseUtil.resultMap(HttpCode.AUTHENTICATION_FAILED));

    }
}