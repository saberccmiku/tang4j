package com.tang4j.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.tang4j.core.model.AbstractModel;
import com.tang4j.core.util.DataUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author saber
 * @since 2020-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysAdmin", description = "管理员表")
public class SysAdmin extends AbstractModel implements UserDetails {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<SysRole> sysRoles;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "密码盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "登录ip")
    @TableField("login_ip")
    private String loginIp;

    @ApiModelProperty(value = "失败次数")
    @TableField("login_failure")
    private Boolean loginFailure;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_date")
    private Date updateDate;

    @ApiModelProperty(value = "删除时间")
    @TableField("delete_date")
    private Date deleteDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (sysRoles != null) {
            sysRoles.forEach(sysRole -> {
                if (!DataUtil.isEmpty(sysRole.getCode())) {
                    authorities.add(new SimpleGrantedAuthority(
                            sysRole.getCode()));
                }
            });
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
