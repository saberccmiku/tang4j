package com.tang4j.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqBasePage {
    /**
     * 第几页
     */
    private Integer page = 1;
    /**
     * 每页多少条
     */
    private Integer rows = 10;
    /**
     * 正序还是倒序
     */
    private String order;
    /**
     * 排序字段
     */
    private String sort;

    private String name;

    private String code;

    private String userNo;

}
