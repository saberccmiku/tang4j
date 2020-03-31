package com.tang4j.security.handler;

import com.tang4j.core.support.http.HttpCode;
import com.tang4j.core.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 权限不足处理类
 * 自定义权限不足需要做的业务操作
 * 包括：处理权限不足的逻辑
 * @Author: fjy
 * @Date: 2019/11/06
 */
@Component
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        log.info(MyAccessDeniedHandler.class.getName(), HttpCode.PERMISSION_DENIED);
        ResponseUtil.out(response, ResponseUtil.resultMap(HttpCode.PERMISSION_DENIED));
    }

}