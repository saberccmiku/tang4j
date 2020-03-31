package com.tang4j.core.model;

import com.tang4j.core.support.http.HttpCode;

public class ResponseModel {

    private Integer code;
    private String message;
    private Object data;

    public ResponseModel() {

    }


    /**
     * 默认响应成功,携带其他数据
     * @param data 携带的数据
     */
    public ResponseModel(Object data) {
        this.code = HttpCode.OK.getCode();
        this.message = HttpCode.OK.getMsg();
        this.data = data;
    }


    /**
     * 自定义响应代码信息，携带其他数据
     * @param code 响应编号
     * @param message 响应信息
     * @param data 携带的数据
     */
    public ResponseModel(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 从HttpCode选取对应的响应信息，携带其他数据
     * @param httpCode HttpCode
     * @param data 携带的数据
     */
    public ResponseModel(HttpCode httpCode,Object data) {
        this.code = httpCode.getCode();
        this.message = httpCode.getMsg();
        this.data = data;
    }

    /**
     * 自定义响应代码信息,不携带其他数据
     * @param code 响应编号
     * @param message 响应信息
     */
    public ResponseModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 从HttpCode选取对应的响应信息,不携带其他数据
     * @param httpCode httpCode
     */
    public ResponseModel(HttpCode httpCode) {
        this.code = httpCode.getCode();
        this.message = httpCode.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public ResponseModel setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseModel setData(Object data) {
        this.data = data;
        return this;
    }
}
