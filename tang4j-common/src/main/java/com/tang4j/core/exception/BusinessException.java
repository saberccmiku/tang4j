package com.tang4j.core.exception;

import com.tang4j.core.support.http.HttpCode;

/**
 * 业务冲突异常
 */

public class BusinessException extends AbstractException{

    public BusinessException() {
    }

    public BusinessException(Throwable ex) {
        super(ex);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable ex) {
        super(message, ex);
    }

    protected HttpCode getCode() {
        return HttpCode.CONFLICT;
    }

}
