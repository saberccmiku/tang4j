package com.tang4j.core.model.easyui;

/**
 * easyUI 的 DataGrid组件
 * T1 行数据 json对象
 * T2 页脚 json对象
 */
public class DataGrid {
    //grid的序号
    private Integer idx;

    /**
     * 总数
     */
    private Long total;
    /**
     * 行数据 json数组
     */
    private Object rows;
    /**
     * 列表的页脚 json数组
     */
    private Object footer;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public Object getFooter() {
        return footer;
    }

    public void setFooter(Object footer) {
        this.footer = footer;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public DataGrid(){}

    public DataGrid(Long total, Object rows) {
        this.total = total;
        this.rows = rows;
    }
}
