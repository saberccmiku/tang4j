package com.tang4j.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户配置信息
 */
@Data
@NoArgsConstructor
public class UserConfig {

    private UserDetails userDetails;
    /**
     * 角色集合
     */
    private List<Role> roles;
    /**
     * 菜单容器
     */
    private MenuContainer menuContainer;

}
