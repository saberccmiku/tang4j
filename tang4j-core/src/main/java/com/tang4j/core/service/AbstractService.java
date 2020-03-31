package com.tang4j.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang4j.core.exception.BusinessException;
import com.tang4j.core.exception.ValidateException;
import com.tang4j.core.model.AbstractModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by saber on 2019/10/26.
 * 基础service类
 */
public interface AbstractService<T extends AbstractModel> {

    /**
     * 新增或者更新
     *
     * @param t 实体对象继承自 AbstractModel
     * @return 更新后的对象
     * @throws BusinessException 业务异常
     * @throws ValidateException 校验异常
     */
    T update(T t) throws BusinessException, ValidateException;

    /**
     * 批量禁用
     *
     * @param ids    被禁用数据的主键
     * @param userId 用户id
     * @throws BusinessException 业务异常
     * @throws ValidateException 校验异常
     */
    void del(List<Long> ids, Long userId) throws BusinessException, ValidateException;

    /**
     * 禁用
     *
     * @param id     被禁用对象主键
     * @param userId 用户id
     * @throws BusinessException 业务异常
     * @throws ValidateException 验异常
     */

    void del(Long id, Long userId) throws BusinessException, ValidateException;

    /**
     * 删除
     *
     * @param id 被删除对象主键
     * @throws BusinessException 业务异常
     * @throws ValidateException 验异常
     */
    void deleteById(Serializable id) throws BusinessException, ValidateException;


    /**
     * 根据条件构造器删除
     *
     * @param t
     * @return
     * @throws BusinessException
     * @throws ValidateException
     */
    Integer deleteByEntity(T t) throws BusinessException, ValidateException;

    /**
     * 根据map条件删除
     *
     * @param params 条件集合
     * @return
     * @throws BusinessException
     * @throws ValidateException
     */
    Integer deleteByMap(Map<String, Object> params) throws BusinessException, ValidateException;

    /**
     * 根据id查询数据
     *
     * @param id 主键
     * @return 数据对象
     */
    T selectById(Serializable id);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    IPage<T> getPage(Page<Long> page);

    /**
     * 根据对象t字段属性所有值执行条件查询，仅限于=
     * 例如 {该对象t是 user对象 user对象包含name account email等属性
     * 当name赋予值为'张三'时其他属性为null时 执行 where name = '张三' ；
     * 当name赋予值为'张三'并且 account赋予值为 'admin' 其他属性null
     * 执行 where name = '张三' and account = 'admin'}
     *
     * @param wrapper 条件对象
     * @return 数据对象
     */
    List<T> queryList(QueryWrapper<T> wrapper);

    /**
     * 分页查询
     *
     * @param var1    分页器
     * @param wrapper wrapper sql条件设置器
     * @return 分页数据
     */
    IPage<T> queryList(IPage<T> var1, Wrapper<T> wrapper);

    /**
     * 分页的条件查询 key = val模式
     *
     * @param var1 分页器
     * @param t    可反射对象 其非final修饰的成员变量所对应的的条件数据作为串联条件
     * @return 分页数据
     */
    IPage<T> normalQuery(IPage<T> var1, T t);

    /**
     * 分页的条件查询 key = val模式
     *
     * @param t 可反射对象 其非final修饰的成员变量所对应的的条件数据作为串联条件
     * @return 分页数据
     */
    List<T> normalQuery(T t);

    /**
     * 分页的模糊查询
     *
     * @param var1 分页器
     * @param t    可反射对象 其非final修饰的成员变量所对应的的模糊数据作为串联条件
     * @return 分页数据
     */
    IPage<T> fuzzyQuery(IPage<T> var1, T t);

    /**
     * 模糊查询
     *
     * @param t 可反射对象 其非final修饰的成员变量所对应的的模糊数据作为串联条件
     * @return 分页数据
     */
    List<T> fuzzyQuery(T t);

    Integer count(T var1) throws BusinessException, ValidateException;

    Integer count(Map<String, Object> var1);

    boolean updateBatch(List<T> var1) throws BusinessException, ValidateException;


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
