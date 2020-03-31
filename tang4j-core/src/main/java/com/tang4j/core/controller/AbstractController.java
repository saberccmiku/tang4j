package com.tang4j.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang4j.core.model.AbstractModel;
import com.tang4j.core.model.ResponseModel;
import com.tang4j.core.model.easyui.DataGrid;
import com.tang4j.core.model.factory.ResponseModelFactory;
import com.tang4j.core.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by saber on 2019/10/26.
 * 基础controller
 */

@Slf4j
public class AbstractController<M extends AbstractService<T>, T extends AbstractModel> implements InitializingBean {

    public Logger logger = LogManager.getLogger();

    public M service;

    public AbstractController(M m) {
        this.service = m;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object object = field.get(this);
                Class<?> cls = field.getType();
                if (object == null && cls.getSimpleName().toLowerCase().contains("service")) {
                    //v = ApplicationContextHolder.getService(cls);
                    field.set(this, object);
                }
                field.setAccessible(false);
            }

        } catch (Exception ex) {
            this.logger.error("", ex);
            this.afterPropertiesSet();
        }


    }


    /**
     * 继承了AbstractService，控制器实现了
     * 数据库操作
     * T update(T t) throws BusinessException, ValidateException;
     * void del(List<Long> ids, Long userId) throws BusinessException, ValidateException;
     * void del(Long id, Long userId) throws BusinessException, ValidateException;
     * void deleteById(Long id) throws BusinessException, ValidateException;
     * Integer deleteByEntity(T t) throws BusinessException, ValidateException;
     * Integer deleteByMap(Map<String, Object> params) throws BusinessException, ValidateException;
     * 数据查询
     * T queryById(Long id);
     * Pagination<T> query(Map<String, Object> params);
     * List<T> queryList(T t);
     * Integer count(T var1) throws BusinessException, ValidateException;
     * Integer count(Map<String, Object> var1);
     * boolean updateBatch(List<T> var1) throws BusinessException, ValidateException;
     */

    /**
     * 分页加载数据 list
     *
     * @param t 前端JSON数据
     * @return 响应信息包含数据对象
     */

    @PostMapping("/getPageView")
    public ResponseModel getPageView(@RequestBody T t) {
        try {
            IPage<T> iPage = service.fuzzyQuery(new Page<>(t.getPage(), t.getRows()), t);
            DataGrid dataGrid = new DataGrid();
            dataGrid.setTotal(iPage.getTotal());
            dataGrid.setRows(iPage.getRecords());
            return ResponseModelFactory.OKWithData(dataGrid);
        } catch (Exception e) {
            return ResponseModelFactory.error(e.getMessage());
        }
    }

    /**
     * 通过菜单id获取按钮信息
     *
     * @param t 包含菜单id 分页信息
     * @return ResponseModel 按钮信息
     */
    @PostMapping("/getPageViewByKey")
    public ResponseModel getPageViewByKey(@RequestBody T t) {
        try {
            IPage<T> iPage = service.normalQuery(new Page<>(t.getPage(), t.getRows()), t);
            DataGrid dataGrid = new DataGrid();
            dataGrid.setTotal(iPage.getTotal());
            dataGrid.setRows(iPage.getRecords());
            return ResponseModelFactory.OKWithData(dataGrid);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModelFactory.error(e.getMessage());
        }
    }

    /**
     * 查询详细信息 detail
     *
     * @param id 数据id
     * @return 响应信息包含数据对象
     */
    @PostMapping("/selectView")
    public ResponseModel selectView(@RequestBody Serializable id) {
        try {
            T t = this.service.selectById(id);
            return ResponseModelFactory.OKWithData(t);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(this.getClass().getName(), e.getMessage());
            return ResponseModelFactory.error(e.getMessage());
        }
    }

    /**
     * 新增或修改信息 updateView
     *
     * @param t 数据对象JSON
     * @return 响应信息包含数据对象
     */
    @PostMapping("/updateView")
    public ResponseModel updateView(@RequestBody T t) {
        try {
            service.update(t);
            return ResponseModelFactory.OK();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseModelFactory.OK(e.getMessage());
        }
    }

    /**
     * 删除信息 deleteView
     *
     * @param id 数据id
     * @return 响应信息包含数据对象
     */
    @PostMapping("/deleteView")
    public ResponseModel deleteView(@RequestBody Serializable id) {
        try {
            service.deleteById(id);
            return ResponseModelFactory.OK();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(this.getClass().getName(), e.getMessage());
            return ResponseModelFactory.error(e.getMessage());
        }
    }

}
