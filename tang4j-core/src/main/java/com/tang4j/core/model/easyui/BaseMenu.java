package com.tang4j.core.model.easyui;

/**
 * 基础导航菜单的内容
 */
public class BaseMenu {

    private String id;
    /**
     * 名称
     */
    private String text;
    /**
     * 图标样式
     */
    private String iconCls;
    /**
     * 菜单状态 展开或者折叠 0,1
     */
    private String state;
    /**
     * 标签页
     */
    private String tag;
    /**
     * 编码
     */
    private String code;
    /**
     * 父类编码
     */
    private String pCode;
    /**
     * 父类Id
     */
    private String parentId;
    /**
     * url
     */
    private String url;

    public String getId() {
        return id;
    }

    public BaseMenu setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public BaseMenu setText(String text) {
        this.text = text;
        return this;
    }

    public String getIconCls() {
        return iconCls;
    }

    public BaseMenu setIconCls(String iconCls) {
        this.iconCls = iconCls;
        return this;
    }

    public String getState() {
        return state;
    }

    public BaseMenu setState(String state) {
        this.state = state;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public BaseMenu setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public BaseMenu setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BaseMenu setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getpCode() {
        return pCode;
    }

    public BaseMenu setPcode(String pCode) {
        this.pCode = pCode;
        return this;
    }

    public String getCode() {
        return code;
    }

    public BaseMenu setCode(String code) {
        this.code = code;
        return this;
    }

}
