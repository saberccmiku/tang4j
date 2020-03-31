package com.tang4j.security.config;

import com.alibaba.fastjson.JSON;
import com.tang4j.core.model.BaseTransferEntity;
import com.tang4j.core.model.RequestWrapper;
import com.tang4j.core.support.http.HttpCode;
import com.tang4j.core.util.HttpKit;
import com.tang4j.core.util.MD5Util;
import com.tang4j.core.util.ResponseUtil;
import com.tang4j.security.util.DataSecurityAction;
import com.tang4j.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: jwt认证token
 * 每次请求接口时，就会进入这里验证token是否合法token，
 * 如果用户一直在操作，则token 过期时间会叠加；如果超过设置的过期时间未操作，
 * 则token 失效，需要重新登录。
 * @Author: fjy
 * @Date: 2019/11/06
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private MyUserDetailsService myUserDetailsService;
    private DataSecurityAction dataSecurityAction;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, DataSecurityAction dataSecurityAction) {
        super(authenticationManager);
        this.myUserDetailsService = myUserDetailsService;
        this.dataSecurityAction = dataSecurityAction;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        //过滤掉不需要token验证的url
        /*if(authenticationRequestMatcher != null && !authenticationRequestMatcher.matches(httpServletRequest)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }*/
        String authToken = HttpKit.getRequest().getHeader(JwtTokenUtil.tokenHeader).replace(JwtTokenUtil.tokenPrefix, "");
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        RequestWrapper requestWrapper = new RequestWrapper(request);

        if (authToken != null) {

            String username = JwtTokenUtil.getUsernameFromToken(authToken);

            log.info("checking authentication for user " + username);

            //当token中的username不为空是进行验证token是否是有效的token
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("token中的username不为空,Context中的authentication为空时,进行token的验证..");
                //TODO,从数据库得到带有密码的完整user信息
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                log.info("加载userdetails:{}", userDetails.getUsername());
                // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
                // the database compellingly. Again it's up to you ;)
                if (JwtTokenUtil.validateToken(authToken, userDetails)) {
                    //--------------开始检验加密的数据
                    String randomKey = JwtTokenUtil.getMd5KeyFromToken(authToken);
                    Object o = JSON.parseObject(requestWrapper.getBodyInputStream(), BaseTransferEntity.class);
                    //return o;
                    //先转化成原始的对象
                    BaseTransferEntity baseTransferEntity = (BaseTransferEntity) o;
                    //校验签名
                    String object = baseTransferEntity.getObject();
                    String json = dataSecurityAction.unlock(object);
                    String encrypt = MD5Util.encrypt(object + randomKey);

                    if (!encrypt.equals(baseTransferEntity.getSign().toUpperCase())) {
                        log.info(JWTAuthenticationFilter.class.getName() + ":签名校验失败,数据被改动过!");
                        ResponseUtil.out(response, ResponseUtil.resultMap(HttpCode.NOT_ACCEPTABLE));
                        return;
                    }
                    //校验签名后再转化成应该的对象
                    try {
                        requestWrapper.updateBody(json);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        ResponseUtil.out(response, ResponseUtil.resultMap(500, e.getMessage()));
                        return;
                    }
                    //--------------解析加密的数据完毕
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new MyWebAuthenticationDetailsSource().buildDetails(requestWrapper));
                    log.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.info(JWTAuthenticationFilter.class.getName(), "token超过有效期，请重新登");
                    //throw new BaseException("401","token超过有效期，请重新登录");
                    ResponseUtil.out(response, ResponseUtil.resultMap(HttpCode.UNAUTHORIZED.getCode(), HttpCode.UNAUTHORIZED.getMsg()));
                }
            }

            chain.doFilter(requestWrapper, response);
        }

    }

}