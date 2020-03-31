package com.tang4j.model;

import lombok.Data;

@Data
public class SysRole {

    private String id;

    private String code;

    private String name;

    public SysRole (){}

    public SysRole (String id,String code){
        this.id = id;
        this.code = code;
    }

}
