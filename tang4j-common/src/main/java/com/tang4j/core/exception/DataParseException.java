package com.tang4j.core.exception;

import com.tang4j.core.support.http.HttpCode;

/**
 * 数据解析异常
 */
public class DataParseException extends AbstractException{
    public DataParseException() {
    }

    public DataParseException(Throwable ex) {
        super(ex);
    }

    public DataParseException(String message) {
        super(message);
    }

    public DataParseException(String message, Throwable ex) {
        super(message, ex);
    }

    protected HttpCode getCode() {
        return HttpCode.INTERNAL_SERVER_ERROR;
    }
}
