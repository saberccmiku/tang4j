package com.tang4j.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by saber on 2019/10/26.
 * 基础model类
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
public class AbstractModel implements Serializable {

    @TableId(value = "id", type = IdType.ID_WORKER)
    @TableField("id")
    protected String id;

    @TableField(
            exist = false
    )
    protected Integer enable;

    @TableField("create_by")
    @ApiModelProperty(
            hidden = true
    )
    protected Long createBy;

    @TableField("create_date")
    @ApiModelProperty(
            hidden = true
    )
    protected Date createDate;

    @TableField("update_by")
    @ApiModelProperty(
            hidden = true
    )
    protected Long updateBy;

    @TableField("update_date")
    @ApiModelProperty(
            hidden = true
    )
    protected Date updateDate;

    @TableField(
            exist = false
    )
    @ApiModelProperty(
            hidden = true
    )
    protected String keyword;

    @TableField(
            exist = false
    )
    @ApiModelProperty(
            hidden = true
    )
    protected String orderBy;

    @TableField(
            exist = false
    )
    @ApiModelProperty(
            hidden = true
    )
    protected List<Long> ids;

    @TableField(
            exist = false
    )
    @ApiModelProperty(
            hidden = true
    )
    protected String userId;

    @TableField(
            exist = false
    )
    @ApiModelProperty(
            hidden = true
    )
    protected String userName;

    /**
     * 第几页
     */
    @TableField(
            exist = false
    )
    private Integer page = 1;
    /**
     * 每页多少条
     */
    @TableField(
            exist = false
    )
    private Integer rows = 10;

    /**
     * 正序还是倒序
     */
    @TableField(
            exist = false
    )
    private String order;
    /**
     * 排序字段
     */
    @TableField(
            exist = false
    )
    private String sort;

    public AbstractModel() {
    }

}
