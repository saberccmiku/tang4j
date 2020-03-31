package com.tang4j.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class Response<T> {

    private Integer code;
    private String msg;
    private T data;
    private Date timestamp;
    private String random;
    private String sign;
}
