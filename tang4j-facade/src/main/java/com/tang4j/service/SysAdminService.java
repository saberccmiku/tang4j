package com.tang4j.service;

import com.tang4j.model.SysAdmin;
import com.tang4j.core.service.AbstractService;

import java.io.Serializable;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author saber
 * @since 2020-02-27
 */
public interface SysAdminService extends AbstractService<SysAdmin> {

    /**
     * 获取用户信息
     *
     * @param val 用户账号或者userId或者手机号码或者邮箱地址
     * @return ThSysUserDto
     */
    SysAdmin queryUser(Serializable val);

}
