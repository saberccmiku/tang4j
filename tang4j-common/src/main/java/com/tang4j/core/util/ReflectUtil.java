package com.tang4j.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectUtil {

    /**
     * 获取子类及其父类的所有字段(含多继承)
     *
     * @param clazz 类
     * @param <T>   类型
     * @return 字段数组
     */
    @SuppressWarnings("unchecked")
    public static <T> Field[] getAllDeclaredFields(Class<T> clazz) {
        List<Field> tempList = new ArrayList<>();
        while (!DataUtil.isEmpty(clazz)) {
            Field[] declaredFields = clazz.getDeclaredFields();
            tempList.addAll(Arrays.asList(declaredFields));
            clazz = (Class<T>) clazz.getSuperclass();
        }
        Field[] allFieldArr = new Field[tempList.size()];
        tempList.toArray(allFieldArr);
        return allFieldArr;
    }

    /**
     * 获取父类的所有字段(含多继承)
     *
     * @param clazz 类
     * @param <T>   类型
     * @return 字段数组
     */
    @SuppressWarnings("unchecked")
    public static <T> Field[] getAllSuperDeclaredFields(Class<T> clazz) {
        List<Field> tempList = new ArrayList<>();
        while (!DataUtil.isEmpty(clazz) && !DataUtil.isEmpty(clazz.getSuperclass())) {
            clazz = (Class<T>) clazz.getSuperclass();
            Field[] declaredFields = clazz.getDeclaredFields();
            tempList.addAll(Arrays.asList(declaredFields));
        }
        Field[] allFieldArr = new Field[tempList.size()];
        tempList.toArray(allFieldArr);
        return allFieldArr;
    }
}
