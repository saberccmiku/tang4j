package com.tang4j.core.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang4j.core.exception.BusinessException;
import com.tang4j.core.mapper.AbstractMapper;
import com.tang4j.core.model.AbstractModel;
import com.tang4j.core.service.AbstractService;
import com.tang4j.core.util.DataUtil;
import com.tang4j.core.util.ExceptionUtil;
import com.tang4j.core.util.ReflectUtil;
import com.tang4j.core.util.UnderscoreUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by saber on 2019/10/26.
 * 基础service实现类
 */
@Slf4j
public class AbstractServiceImpl<M extends AbstractMapper<T>, T extends AbstractModel> implements AbstractService<T> {

    @Autowired
    private M mapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public T update(T t) {
        T result;
        try {
            if (DataUtil.isEmpty(t.getId())) {
                t.setCreateDate(new Date());
                t.setId(String.valueOf(IdWorker.getId()));
                this.mapper.insert(t);
                result = this.mapper.selectById(t.getId());
                t.setId(null);
            } else {
                t.setUpdateDate(new Date());
                this.mapper.updateById(t);
                result = this.mapper.selectById(t.getId());
            }
            return result;
        } catch (DuplicateKeyException dke) {
            log.error(ExceptionUtil.getStackTraceAsString(dke));
            throw new BusinessException("数据已存在.");
        } catch (Exception ex) {
            log.error(ExceptionUtil.getStackTraceAsString(ex));
            throw new RuntimeException(ExceptionUtil.getStackTraceAsString(ex));
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void del(List<Long> ids, Long userId) {
        ids.forEach(id -> del(id, userId));
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void del(Long id, Long userId) {
        T t = this.selectById(id);
        t.setEnable(0);
        t.setUpdateDate(new Date());
        t.setUpdateBy(userId);
        this.mapper.updateById(t);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(Serializable id) {
        mapper.deleteById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer deleteByEntity(T t) {
        Wrapper<T> wrapper = new UpdateWrapper<>(t);
        return mapper.delete(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer deleteByMap(Map<String, Object> params) {
        return mapper.deleteByMap(params);
    }

    @Override
    public T selectById(Serializable id) {
        return mapper.selectById(id);
    }


    @Override
    public IPage<T> getPage(Page<Long> page) {
        IPage<T> iPage = new Page<>(page.getCurrent(), page.getSize());
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return mapper.selectPage(iPage, wrapper);
    }

    @Override
    public List<T> queryList(QueryWrapper<T> wrapper) {
        return mapper.queryList(wrapper);
    }

    @Override
    public IPage<T> queryList(IPage<T> page, Wrapper<T> wrapper) {
        page.setRecords(this.mapper.queryList(page, wrapper));
        return page;
    }

    @Override
    public IPage<T> normalQuery(IPage<T> page, T t) {
        page.setRecords(this.mapper.queryList(page, initNormalQuerySql(t)));
        return page;
    }

    @Override
    public List<T> normalQuery(T t) {
        return this.mapper.queryList(null, initNormalQuerySql(t));
    }


    @Override
    public IPage<T> fuzzyQuery(IPage<T> page, T t) {
        page.setRecords(this.mapper.queryList(page, initFuzzyQuerySql(t)));
        return page;
    }

    @Override
    public List<T> fuzzyQuery(T t) {
        return this.mapper.queryList(null, initFuzzyQuerySql(t));
    }

    private Wrapper<T> initFuzzyQuerySql(T t) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        //通过反射获取子类及其父类变量字段
        Field[] allDeclaredFields = ReflectUtil.getAllDeclaredFields(t.getClass());
        StringBuffer sb;
        for (Field field : allDeclaredFields) {
            sb = new StringBuffer();
            try {
                field.setAccessible(true);
                Object obj = field.get(t);
                //成员变量不是空并且非final态变量
                if (!DataUtil.isEmpty(obj) && !Modifier.isFinal(field.getModifiers())) {

                    //判断字段是否被mybaits的TableField标注了
                    boolean isIdField = field.isAnnotationPresent(TableField.class);
                    if (isIdField) {
                        TableField tableField = field.getAnnotation(TableField.class);
                        //被mybaits的TableField标注并且注释不在数据库的字段中的成员变量不应该纳入查询范围
                        if (tableField.exist()) {
                            wrapper.like(sb.append("t.").append(UnderscoreUtil.underscoreName(field.getName())).toString(), obj);
                        }
                    } else {
                        //这里当实体类成员变量不是来自数据库就会报错 调用该方法时需要注意 可以使用上面的方面过滤
                        wrapper.like(sb.append(UnderscoreUtil.underscoreName(field.getName())).toString(), obj);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
            field.setAccessible(false);
        }

        return wrapper;
    }


    private Wrapper<T> initNormalQuerySql(T t) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        //通过反射获取子类及其父类变量字段
        Field[] allDeclaredFields = ReflectUtil.getAllDeclaredFields(t.getClass());
        StringBuffer sb;
        for (Field field : allDeclaredFields) {
            sb = new StringBuffer();
            try {
                field.setAccessible(true);
                Object obj = field.get(t);
                //成员变量不是空并且非final态变量
                if (!DataUtil.isEmpty(obj) && !Modifier.isFinal(field.getModifiers())) {

                    //判断字段是否被mybaits的TableField标注了
                    boolean isIdField = field.isAnnotationPresent(TableField.class);
                    if (isIdField) {
                        TableField tableField = field.getAnnotation(TableField.class);
                        //被mybaits的TableField标注并且注释不在数据库的字段中的成员变量不应该纳入查询范围
                        if (tableField.exist()) {
                            wrapper.eq(sb.append("t.").append(UnderscoreUtil.underscoreName(field.getName())).toString(), obj);
                        }
                    } else {
                        //这里当实体类成员变量不是来自数据库就会报错 调用该方法时需要注意 可以使用上面的方面过滤
                        wrapper.eq(sb.append(UnderscoreUtil.underscoreName(field.getName())).toString(), obj);
                    }
                    //排序 默认按照order_no字段 如果没有则需要指定
                    if (obj.equals("orderNo")) {
                        wrapper.orderByAsc("order_no");
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
            field.setAccessible(false);
        }
        return wrapper;
    }


    @Override
    public Integer count(T t) {
        Wrapper<T> wrapper = new QueryWrapper<>(t);
        return this.mapper.selectCount(wrapper);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return this.mapper.selectCount(params);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateBatch(List<T> entityList) {
        return this.mapper.updateBatch(entityList);
    }

    @Override
    public List<T> queryByAccount(Serializable val) {
        return this.mapper.queryByAccount(val);
    }

    /**
     * 根据用户Id以及其他关键信息来查询相关信息
     *
     * @param t 对象
     * @return 需要查询的信息
     */
    @Override
    public List<T> selectByUserIdAndKey(T t) {
        return this.mapper.selectByUserIdAndKey(t);
    }


}
