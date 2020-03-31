package com.tang4j.core.model.easyui;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单栏 位于窗口顶部的水平区域
 * @author fjy
 */
public class MenuBar extends BaseMenu {

    @Setter@Getter
    List<MenuBar> children;

}
