package com.tang4j.core.model.easyui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreeGrid {

    private String id;
    private String pId;
    private String code;
    private String pCode;
    private String text;
    private Boolean checked;
    private String state;
    private String tag;
    private String iconCls;
    private List<TreeGrid> children;

    public TreeGrid(String id,String pId,String text){
        this.id = id;
        this.pId = pId;
        this.text = text;
    }

    public TreeGrid(String id,String code,String pCode,String text,String iconCls,Boolean checked,String state){
        this.id = id;
        this.code = code;
        this.pCode = pCode;
        this.text = text;
        this.iconCls = iconCls;
        this.checked = checked;
        this.state = state;
    }


}
