package com.tang4j.security.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    private HttpServletRequest request;

    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
