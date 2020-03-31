package com.tang4j.core.model.easyui;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 导航菜单 导航菜单默认为垂直模式，继承基础的BaseMenu主要是扩展导航的子父结构
 *
 * @author fjy
 */
public class SideMenu extends BaseMenu{

    @Setter
    @Getter
    List<SideMenu> children;

}
