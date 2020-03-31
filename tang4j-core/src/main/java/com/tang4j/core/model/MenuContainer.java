package com.tang4j.core.model;

import com.tang4j.core.model.easyui.MenuBar;
import com.tang4j.core.model.easyui.SideMenu;

import java.util.List;

public class MenuContainer {

    /**
     * easyUI左侧菜单
     */
    private List<SideMenu> sideMenuList;
    /**
     * easyUI顶部菜单
     */
    private List<MenuBar> menuBarList;

    public List<SideMenu> getSideMenuList() {
        return sideMenuList;
    }

    public MenuContainer setSideMenuList(List<SideMenu> sideMenuList) {
        this.sideMenuList = sideMenuList;
        return this;
    }

    public List<MenuBar> getMenuBarList() {
        return menuBarList;
    }

    public MenuContainer setMenuBarList(List<MenuBar> menuBarList) {
        this.menuBarList = menuBarList;
        return this;
    }
}
