package com.tang4j.security.config;

import com.tang4j.model.SysAdmin;
import com.tang4j.model.SysMenuResource;
import com.tang4j.model.SysRole;
import com.tang4j.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:获取用户权限的service
 * @Author: fjy
 * @Date: 2019/11/6
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysAdminService sysAdminService;

    /**
     * 定义角色
     */
    public static List<SysRole> ALL_ROLES = new ArrayList();

    public static List<SysMenuResource> COMMON_MENUS = new ArrayList();

//    static {
//        //定义角色
//        ThSysRoleDto adminRole = new ThSysRoleDto("1", "admin");
//        ThSysRoleDto commonRole = new ThSysRoleDto("2", "user");
//
//        adminRole.setThSysNavigationDtoList(ADMIN_MENUS);
//        commonRole.setThSysNavigationDtoList(COMMON_MENUS);
//        //初始化角色
//        ALL_ROLES.add(adminRole);
//        ALL_ROLES.add(commonRole);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //加载用户信息
        SysAdmin user = sysAdminService.queryUser(username);
        //加载用户角色信息
        List<SysRole> roleList = new ArrayList<>();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username: %s", username));
        }
        ALL_ROLES.clear();
        SysRole adminRole = new SysRole("1", "admin");
        roleList.add(adminRole);

        ALL_ROLES.addAll(roleList);
        user.setSysRoles(ALL_ROLES);
        return user;
    }

}