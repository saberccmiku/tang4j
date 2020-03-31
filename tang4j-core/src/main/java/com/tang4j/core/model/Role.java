package com.tang4j.core.model;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Role {
    /**
     * 角色id
     */
    private String id;
    /**
     * 角色编号
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 菜单容器
     */
    private List<MenuContainer> menuContainers;

    public String getId() {
        return id;
    }

    public Role setId(String id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Role setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Role setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Role setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<MenuContainer> getMenuContainers() {
        return menuContainers;
    }

    public Role setMenuContainers(List<MenuContainer> menuContainers) {
        this.menuContainers = menuContainers;
        return this;
    }
}

