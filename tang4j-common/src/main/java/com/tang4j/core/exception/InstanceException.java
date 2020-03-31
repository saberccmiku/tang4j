package com.tang4j.core.exception;

import com.tang4j.core.support.http.HttpCode;

/**
 * 实例化异常
 */
public class InstanceException extends AbstractException {

    public InstanceException() {
    }

    public InstanceException(Throwable t) {
        super(t);
    }

    protected HttpCode getCode() {
        return HttpCode.INTERNAL_SERVER_ERROR;
    }

}
