package com.tang4j.core.model.easyui;

import java.util.Objects;

public class KeyValueItem {

    /**
     *下拉框显示的文本
     */
    private String text;

    /**
     * 下拉框显示的数值（隐藏的内容）
     */
    private String value;

    /**
     * 扩展内容全部写在这里
     */
    private Object tag;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }






}
