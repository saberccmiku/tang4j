package com.tang4j.security.handler;

import com.tang4j.core.support.http.HttpCode;
import com.tang4j.core.util.ResponseUtil;
import com.tang4j.model.SysAdmin;
import com.tang4j.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 登录成功处理类
 * 用户登录系统成功后，需要做的业务操作
 * 包括：1.生成token;2.将用户信息保存到redis;3.将身份存储到SecurityContext里
 * @Author: fjy
 * @Date: 2019/11/06
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        SysAdmin securityUserDetails = (SysAdmin) authentication.getPrincipal();
        //token = JwtTokenUtil.tokenPrefix + token;
        String randomKey = JwtTokenUtil.getRandomString(6);
        String token = JwtTokenUtil.generateAccessToken(securityUserDetails, randomKey);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("token",token);
        map.put("data", dataMap);
        map.put("random", randomKey);
        ResponseUtil.out(response, ResponseUtil.resultMap(HttpCode.OK, map));
        log.info("-----------【" + ResponseUtil.resultMap(HttpCode.OK, map) + "】map---------");
        log.info("-----------【" + token + "】token---------");
        log.info("-----------【" + securityUserDetails.getUsername() + "】登录成功---------");
    }
}
