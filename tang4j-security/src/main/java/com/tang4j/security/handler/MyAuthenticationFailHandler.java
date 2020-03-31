package com.tang4j.security.handler;

import com.tang4j.core.support.http.HttpCode;
import com.tang4j.core.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 登录失败处理类
 * 用户登录系统失败后需要做的业务操作
 * 包括：分类返回登录失败原因
 * @Author: fjy
 * @Date: 2019/11/06
 */
@Component
@Slf4j
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        HttpCode httpCode;
        if (e instanceof BadCredentialsException) {
            //可在此记录登录失败次数，进行锁定
            httpCode = HttpCode.LOGIN_FAILED_PASSWORD;
        } else if (e instanceof InternalAuthenticationServiceException) {
            httpCode = HttpCode.LOGIN_FAILED_ACCOUNT;
        } else if (e instanceof UsernameNotFoundException) {
            httpCode = HttpCode.LOGIN_FAILED_ACCOUNT;
        } else if (e instanceof DisabledException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(HttpCode.ACCOUNT_FORBIDDEN));
            //可以新增登录异常次数超限LoginFailLimitException
            httpCode = HttpCode.ACCOUNT_FORBIDDEN;
        } else {
            httpCode = HttpCode.LOGIN_FAIL;
        }

        log.info(MyAuthenticationEntryPointHandler.class.getName(), httpCode);
        ResponseUtil.out(response, ResponseUtil.resultMap(httpCode));
    }
}