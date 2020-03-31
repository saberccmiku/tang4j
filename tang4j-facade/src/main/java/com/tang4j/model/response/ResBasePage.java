package com.tang4j.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBasePage {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 查询的list内容
     */
    private Object rows;

}
