package com.tang4j.security.util;

import com.alibaba.fastjson.JSON;
import com.tang4j.core.util.HttpKit;
import com.tang4j.model.SysAdmin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @Description: jwt工具类
 * 提供校验token 、生成token、根据token获取用户等方法
 * @Author: fjy
 * @Date: 2019/11/6
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -5883980282405596071L;

    //
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";
    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";
    private static final String CLAIM_KEY_ACCOUNT_ENABLED = "enabled";
    private static final String CLAIM_KEY_ACCOUNT_NON_LOCKED = "non_locked";
    private static final String CLAIM_KEY_ACCOUNT_NON_EXPIRED = "non_expired";
    private static final String CLAIM_KEY_USER_ACCOUNT = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    //签名方式
    //private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * JWT签名加密key
     */
    public static String secret;
    /**
     * token分割
     */
    public static String tokenPrefix;
    /**
     * token参数头
     */
    public static String tokenHeader;
    /**
     * 权限参数头
     */
    public static String authHeader;
    /**
     * token有效期
     */
    public static Long tokenExpire;
    /**
     * token超时时间
     */
    private static Long accessToken;
    /**
     * 刷新token时间   单位秒
     */
    private static Long refreshToken;
    /**
     * #md5加密混淆key
     */
    private static String md5Key;

    /**
     * 不需要认证的接口
     */
    public static String antMatchers;


    @Value("${jwt.tokenHeader}")
    public void setTokenHeader(String tokenHeader) {
        JwtTokenUtil.tokenHeader = tokenHeader;
    }

    @Value("${jwt.tokenPrefix}")
    public void setTokenPrefix(String tokenPrefix) {
        JwtTokenUtil.tokenPrefix = tokenPrefix;
    }

    @Value("${jwt.authHeader}")
    public void setAuthHeader(String authHeader) {
        JwtTokenUtil.authHeader = authHeader;
    }

    //密匙
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtTokenUtil.secret = secret;
    }

    @Value("${jwt.accessToken}")
    public void setAccessToken(Long accessToken) {
        JwtTokenUtil.accessToken = accessToken;
    }

    @Value("${jwt.refreshToken}")
    public void setRefreshToken(Long refreshToken) {
        JwtTokenUtil.refreshToken = refreshToken;
    }

    //过期时间
    @Value("${jwt.tokenExpire}")
    public void setTokenExpire(Long tokenExpire) {
        JwtTokenUtil.tokenExpire = tokenExpire;
    }

    @Value("${jwt.antMatchers}")
    public void setAntMatchers(String antMatchers) {
        JwtTokenUtil.antMatchers = antMatchers;
    }

    @Value("${jwt.md5Key}")
    public void setMd5Key(String md5Key) {
        JwtTokenUtil.md5Key = md5Key;
    }

    /**
     * 根据token 获取用户信息
     *
     * @param token
     * @return
     */
    public static SysAdmin getUserFromToken(String token) {
        SysAdmin userDetails;
        try {
            userDetails = new SysAdmin();
            final Claims claims = getClaimsFromToken(token);
            String userId = getUserIdFromToken(token);
            String account = claims.getSubject();
            List<?> roles = (List<?>) claims.get(CLAIM_KEY_AUTHORITIES);
            boolean isEnabled = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_ENABLED);
            boolean isAccountNonLocked = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_LOCKED);
            boolean isAccountNonExpired = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_EXPIRED);
            userDetails.setId(userId);
            userDetails.setUsername(account);
        } catch (Exception e) {
            userDetails = null;
        }
        return userDetails;
    }

    /**
     * 根据token 获取用户ID
     *
     * @param token
     * @return
     */
    public static String getUserIdFromToken(String token) {
        String userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = claims != null ? claims.get(CLAIM_KEY_USER_ID).toString() : "0";
        } catch (Exception e) {
            e.printStackTrace();
            userId = "0";
        }
        return userId;
    }

    /**
     * 根据token 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据token 获取生成时间
     *
     * @param token
     * @return
     */
    public static Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 根据token 获取过期时间
     *
     * @param token
     * @return
     */
    public static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /***
     * 解析token 信息
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)   //签名的key
                    .parseClaimsJws(token)   // 签名token
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成失效时间
     *
     * @param expiration
     * @return
     */
    private static Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * token 是否过期
     *
     * @param token
     * @return
     */
    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 生成时间是否在最后修改时间之前
     *
     * @param created           生成时间
     * @param lastPasswordReset 最后修改密码时间
     * @return
     */
    private static Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 根据用户信息 生成token
     *
     * @param userDetails
     * @return
     */
    public static String generateAccessToken(SysAdmin userDetails, String randomKey) {
        Map<String, Object> claims = generateClaims(userDetails);
        claims.put(JwtTokenUtil.md5Key, randomKey);
        //claims.put(CLAIM_KEY_AUTHORITIES, JSON.toJSON(authoritiesToArray(userDetails.getAuthorities())));
        return generateAccessToken(userDetails.getUsername(), claims);
    }

    /**
     * 重置(更新)token 过期时间
     *
     * @param token
     * @param expiration
     */
    public static String restTokenExpired(String token, long expiration) {

        final Claims claims = getClaimsFromToken(token);
        Jwts.builder()
                .setClaims(claims)   //一个map 可以资源存放东西进去
                .setSubject(claims.getSubject()) //  用户名写入标题
                .setExpiration(new Date(expiration));
        //claims.setExpiration(new Date(expiration));
        // String refreshedToken = generateAccessToken(claims.getSubject(), claims,expiration);
        return "";
    }

    private static Map<String, Object> generateClaims(SysAdmin user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, user.getId());
        claims.put(CLAIM_KEY_ACCOUNT_ENABLED, user.isEnabled());
        claims.put(CLAIM_KEY_ACCOUNT_NON_LOCKED, user.isAccountNonLocked());
        claims.put(CLAIM_KEY_ACCOUNT_NON_EXPIRED, user.isAccountNonExpired());
        return claims;
    }

    /**
     * 生成token
     *
     * @param subject 用户名
     * @param claims
     * @return
     */
    private static String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, tokenExpire);
    }


    /**
     * 生成token
     *
     * @param subject 用户名
     * @param claims
     * @return
     */
    private static String generateAccessToken(String subject, Map<String, Object> claims, long expiration) {
        return generateToken(subject, claims, expiration);
    }

    /**
     * 用户所拥有的资源权限
     *
     * @param authorities
     * @return
     */
    private static List<?> authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    private static Collection<? extends GrantedAuthority> parseArrayToAuthorities(List<?> roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Object role : roles) {
            authority = new SimpleGrantedAuthority(role.toString());
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 根据用户信息 重新获取token
     *
     * @param userDetails
     * @return
     */
    public static String generateRefreshToken(UserDetails userDetails) {
        SysAdmin user = (SysAdmin) userDetails;
        Map<String, Object> claims = generateClaims(user);
        // 只授于更新 token 的权限
        String roles[] = new String[]{ROLE_REFRESH_TOKEN};
        claims.put(CLAIM_KEY_AUTHORITIES, JSON.toJSON(roles));
        return generateRefreshToken(user.getUsername(), claims);
    }

    /**
     * 重新获取token
     *
     * @param subject 用户名
     * @param claims
     * @return
     */
    private static String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, tokenExpire);
    }

    public static Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    /**
     * 刷新重新获取token
     *
     * @param token 源token
     * @return
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateAccessToken(claims.getSubject(), claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    private static String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)   //一个map 可以资源存放东西进去
                .setSubject(subject) //  用户名写入标题
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))  //过期时间
                //.setNotBefore(now)              //系统时间之前的token都是不可以被承认的
                .signWith(SIGNATURE_ALGORITHM, secret) //数字签名
                .compact();


    }

    /**
     * 验证token 是否合法
     *
     * @param token       token
     * @param userDetails 用户信息
     * @return
     */
    public static Boolean validateToken(String token, UserDetails userDetails) {
        SysAdmin user = (SysAdmin) userDetails;
        final String userId = getUserIdFromToken(token);
        final String username = getUsernameFromToken(token);
        // final Date created = getCreatedDateFromToken(token);
        // final Date expiration = getExpirationDateFromToken(token);
        return (userId.equals(user.getId())
                && username.equals(user.getUsername())
                && !isTokenExpired(token)
                /* && !isCreatedBeforeLastPasswordReset(created, userDetails.getLastPasswordResetDate()) */
        );
    }


    /**
     * 获取请求token
     *
     * @return 去掉前缀的token字符串
     */
    public static String getToken() {
        return HttpKit.getRequest().getHeader(JwtTokenUtil.tokenHeader).replace(JwtTokenUtil.tokenPrefix, "");
    }

    /**
     * 获取md5 key从token中
     */
    public static String getMd5KeyFromToken(String token) {
        return getClaimsFromToken(token).get(JwtTokenUtil.md5Key).toString();
    }

    /**
     * 获取随机位数的字符串
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}