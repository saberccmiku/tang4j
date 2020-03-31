package com.tang4j.core.model.easyui;

import java.util.List;

public class TreeKeyValueItem extends KeyValueItem {
    public List<TreeKeyValueItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeKeyValueItem> children) {
        this.children = children;
    }

    /**
     * 扩展的键值名对，用于树形结构的。
     */
    private List<TreeKeyValueItem> children;



}
