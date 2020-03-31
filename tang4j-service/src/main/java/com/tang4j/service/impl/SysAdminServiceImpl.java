package com.tang4j.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang4j.core.validate.RegexType;
import com.tang4j.model.SysAdmin;
import com.tang4j.mapper.SysAdminMapper;
import com.tang4j.service.SysAdminService;
import com.tang4j.core.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2020-02-27
 */
@Service
public class SysAdminServiceImpl extends AbstractServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    /**
     * 获取用户信息
     *
     * @param val 用户账号或者userId或者手机号码或者邮箱地址
     * @return SysUser
     */
    @Override
    public SysAdmin queryUser(@Nullable Serializable val) {

        String tempVal = String.valueOf(val);
        StringBuffer sb = new StringBuffer();
        if (Pattern.matches(RegexType.EMAIL.value(), tempVal)) {
            sb.append("t.email");
        } else if (Pattern.matches(RegexType.PHONE.value(), tempVal)) {
            sb.append("t.phone");
        } else {
            sb.append("t.username");
        }
        QueryWrapper<SysAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq(sb.toString(), tempVal);
        List<SysAdmin> sysAdminList = sysAdminMapper.queryList(wrapper);
        if (sysAdminList != null && sysAdminList.size() != 0) {
            return sysAdminList.get(0);
        }
        return null;
    }
}
