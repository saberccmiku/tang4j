package com.tang4j.security.util;

/**
 * @Description: url权限配置
 * @Author: fjy
 * @Date: 2019/11/6
 */
public interface UrlMatcher {
    Object compile(String paramString);

    boolean pathMatchesUrl(Object paramObject, String paramString);

    String getUniversalMatchPattern();

    boolean requiresLowerCaseUrl();
}