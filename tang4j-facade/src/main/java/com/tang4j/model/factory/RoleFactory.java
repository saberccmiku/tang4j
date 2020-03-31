package com.tang4j.model.factory;

import com.tang4j.core.model.Role;
import com.tang4j.core.model.easyui.TreeGrid;
import com.tang4j.core.util.InstanceUtil;
import com.tang4j.model.SysRole;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class RoleFactory {

    public static Role createRole(SysRole sysRole) {

        return new Role().setId(sysRole.getId()).setName(sysRole.getName()).setCode(sysRole.getCode());
    }

    public static List<Role> createRoleList(List<SysRole> sysRoleList) {
        List<Role> roleList = new ArrayList<>();
        sysRoleList.forEach(sysRole -> roleList.add(RoleFactory.createRole(sysRole)));
        return roleList;
    }

    public static TreeGrid createTreeGrid(SysRole sysRole) {
        return new TreeGrid(String.valueOf(sysRole.getId()), null, sysRole.getName());
    }


    public static List<TreeGrid> createTreeGridList(@NotNull List<SysRole> sysRoleList) {
        List<TreeGrid> treeGridList = InstanceUtil.newArrayList();
        sysRoleList.forEach(sysRole -> treeGridList.add(RoleFactory.createTreeGrid(sysRole)));
        return treeGridList;
    }
}
