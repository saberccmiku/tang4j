package com.tang4j.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang4j.core.model.AbstractModel;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by saber on 2019/10/26
 * 基础Mapper类
 */
public interface AbstractMapper<T extends AbstractModel> extends BaseMapper<T> {

    /**
     * 可以通过对象属性某些值来查询
     *
     * @param wrapper 条件构造起
     * @return 需要查询的对象
     */
    List<T> queryList(@Param(Constants.WRAPPER) Wrapper<T> wrapper);

    List<T> queryList(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> wrapper);

    List<T> selectPage(Page<Long> page, Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    boolean updateBatch(List<T> entityList);

    /**
     * 根据账号来查询相关信息 可以是邮箱账号 手机账号 注册账号（登录账号）
     *
     * @param val 账号信息
     * @return 需要查询的信息
     */
    List<T> queryByAccount(Serializable val);

    /**
     * 根据用户Id以及其他关键信息来查询相关信息
     *
     * @param t 对象
     * @return 需要查询的信息
     */
    List<T> selectByUserIdAndKey(T t);

}
