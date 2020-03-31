package com.tang4j.core.util;


import com.alibaba.fastjson.JSON;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.tang4j.core.exception.DataParseException;
import com.tang4j.core.exception.InstanceException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;


public final class InstanceUtil {

    protected static Logger logger = LogManager.getLogger();
    private static final Map<String, MethodAccess> methodMap = newHashMap();
    private static final Map<String, Field> fieldMap = newHashMap();
    public static Map<String, Class<?>> clazzMap = new HashMap<>();

    private InstanceUtil() {
    }

    public static <T> T to(Object orig, Class<T> clazz) {
        Object bean = null;

        try {
            bean = clazz.newInstance();
            Class<?> cls = orig.getClass();
            BeanInfo orgInfo = Introspector.getBeanInfo(cls);
            PropertyDescriptor[] orgPty = orgInfo.getPropertyDescriptors();
            Map<String, PropertyDescriptor> propertyMap = newHashMap();
            for (PropertyDescriptor property : orgPty) {
                propertyMap.put(property.getName(), property);
            }

            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!"class".equals(key) && propertyMap.containsKey(key)) {
                    Method getter = (propertyMap.get(key)).getReadMethod();
                    Object value = TypeParseUtil.convert(getter.invoke(orig), property.getPropertyType(), (String) null);
                    updateClassField(bean, key, value, clazz.getName(), clazz.getDeclaredField(key), clazz);
                }
            }
        } catch (Exception var19) {
            logger.error("to Error " + var19);
        }

        return (T) bean;
    }

    private static <T> void updateClassField(Object bean, String key, Object value, String name, Field declaredField, Class<T> clazz) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        try {
            String fieldName = name + "." + key;
            Field field = (Field) fieldMap.get(fieldName);
            if (field == null) {
                field = declaredField;
                fieldMap.put(fieldName, field);
            }

            field.setAccessible(true);
            field.set(bean, value);
        } catch (Exception var18) {
            PropertyUtils.setProperty(bean, key, value);
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            Map map = JSON.parseObject(json, Map.class);
            return transMap2Bean((Map<String, Object>) map, clazz);
        } catch (Exception var3) {
            logger.error("parse", var3);
            return null;
        }
    }

    public static <T> T transMap2Bean(Map<String, Object> map, Class<T> clazz) {
        T bean = null;

        try {
            bean = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                try {
                    String key = property.getName();
                    if (map.containsKey(key)) {
                        Object value = TypeParseUtil.convert(map.get(key), property.getPropertyType(), (String) null);

                        updateClassField(bean, key, value, clazz.getName(), clazz.getDeclaredField(key), clazz);
                    }
                } catch (Exception var14) {
                    logger.error("transMap2Bean setter Error ", var14);
                }
            }
        } catch (Exception var15) {
            logger.error("transMap2Bean Error ", var15);
        }

        return bean;
    }

    public static Map<String, Object> transBean2Map(Object obj) {
        Map<String, Object> map = newHashMap();
        if (obj == null) {
            return map;
        } else {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    if (!"class".equals(key)) {
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj);
                        map.put(key, value);
                    }
                }
            } catch (Exception var11) {
                logger.error("transBean2Map Error " + var11);
            }

            return map;
        }
    }

    public static <T> T getDiff(T oldBean, T newBean) {
        if (oldBean == null && newBean != null) {
            return newBean;
        } else if (newBean == null) {
            return null;
        } else {
            Class cls1 = oldBean.getClass();

            try {
                T object = (T) cls1.newInstance();
                BeanInfo beanInfo = Introspector.getBeanInfo(cls1);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    if (!"class".equals(key)) {
                        Method getter = property.getReadMethod();
                        Object oldValue = getter.invoke(oldBean);
                        Object newValue = getter.invoke(newBean);
                        if (newValue != null && !newValue.equals(oldValue)) {
                            Object value = TypeParseUtil.convert(newValue, property.getPropertyType(), (String) null);

                            try {
                                String fieldName = cls1.getName() + "." + key;
                                Field field = (Field) fieldMap.get(fieldName);
                                if (field == null) {
                                    field = cls1.getDeclaredField(key);
                                    fieldMap.put(fieldName, field);
                                }

                                field.setAccessible(true);
                                field.set(object, value);
                            } catch (Exception var17) {
                                PropertyUtils.setProperty(object, key, value);
                            }
                        }
                    }
                }

                return object;
            } catch (Exception var18) {
                throw new DataParseException(var18);
            }
        }
    }

    public static Class<?> getClass(String clazz) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            return loader != null ? Class.forName(clazz, true, loader) : Class.forName(clazz);
        } catch (ClassNotFoundException var3) {
            throw new InstanceException(var3);
        }
    }

    public static <E> List<E> getInstanceList(Class<E> cls, List<?> list) {
        ArrayList<E> resultList = newArrayList();
        E object = null;
        for (Object name : list) {
            Map<?, ?> map = (Map) name;
            object = newInstance(cls, map);
            resultList.add(object);
        }

        return resultList;
    }

    public static <E> List<E> getInstanceList(Class<E> cls, ResultSet rs) {
        ArrayList<E> resultList = newArrayList();

        try {
            E object = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();

            while (rs.next()) {
                object = cls.newInstance();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    PropertyUtils.setProperty(object, fieldName, rs.getObject(fieldName));
                }
                resultList.add(object);
            }

            return resultList;
        } catch (Exception var10) {
            throw new InstanceException(var10);
        }
    }

    public static <E> E newInstance(Class<E> cls, Map<String, ?> map) {
        E object = null;
        try {
            object = cls.newInstance();
            BeanUtils.populate(object, map);
            return object;
        } catch (Exception var4) {
            throw new InstanceException(var4);
        }
    }

    public static Object newInstance(String clazz) {
        try {
            return getClass(clazz).newInstance();
        } catch (Exception var2) {
            throw new InstanceException(var2);
        }
    }

    public static <K> K newInstance(Class<K> cls, Object... args) {
        try {
            Class<?>[] argsClass = null;
            if (args != null) {
                argsClass = new Class[args.length];
                int i = 0;

                for (int j = args.length; i < j; ++i) {
                    argsClass[i] = args[i].getClass();
                }
            }

            Constructor<K> cons = cls.getConstructor(argsClass);
            return cons.newInstance(args);
        } catch (Exception var5) {
            throw new InstanceException(var5);
        }
    }

    public static Object newInstance(String className, Object... args) {
        try {
            Class<?> newoneClass = clazzMap.get(className);
            if (newoneClass == null) {
                newoneClass = Class.forName(className);
                clazzMap.put(className, newoneClass);
            }

            return newInstance(newoneClass, args);
        } catch (Exception var3) {
            throw new InstanceException(var3);
        }
    }

    public static Object invokeMethod(Object owner, String methodName, Object... args) {
        Class<?> ownerClass = owner.getClass();
        String key = null;
        if (args != null) {
            Class<?>[] argsClass = new Class[args.length];
            int i = 0;

            for (int j = args.length; i < j; ++i) {
                if (args[i] != null) {
                    argsClass[i] = args[i].getClass();
                }
            }

            key = ownerClass + "_" + methodName + "_" + StringUtils.join(argsClass, ",");
        } else {
            key = ownerClass + "_" + methodName;
        }

        MethodAccess methodAccess = methodMap.get(key);
        if (methodAccess == null) {
            methodAccess = MethodAccess.get(ownerClass);
            methodMap.put(key, methodAccess);
        }

        return methodAccess.invoke(owner, methodName, args);
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... e) {
        ArrayList<E> list = newArrayList();
        Collections.addAll(list, e);
        return list;
    }

    public static <k, v> HashMap<k, v> newHashMap() {
        return new HashMap<>();
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <k, v> Hashtable<k, v> newHashtable() {
        return new Hashtable<>();
    }

    public static <k, v> LinkedHashMap<k, v> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    public static <k, v> TreeMap<k, v> newTreeMap() {
        return new TreeMap<>();
    }

    public static <E> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    public static <E> Vector<E> newVector() {
        return new Vector<>();
    }

    public static <k, v> WeakHashMap<k, v> newWeakHashMap() {
        return new WeakHashMap<>();
    }

    public static <k, v> HashMap<k, v> newHashMap(k key, v value) {
        HashMap<k, v> map = newHashMap();
        map.put(key, value);
        return map;
    }

    public static <k, v> HashMap<k, v> newHashMap(k[] key, v[] value) {
        HashMap<k, v> map = newHashMap();

        for (int i = 0; i < key.length; ++i) {
            map.put(key[i], value[i]);
        }

        return map;
    }

    public static <k, v> LinkedHashMap<k, v> newLinkedHashMap(k key, v value) {
        LinkedHashMap<k, v> map = newLinkedHashMap();
        map.put(key, value);
        return map;
    }

    public static <k, v> ConcurrentHashMap<k, v> newConcurrentHashMap() {
        return new ConcurrentHashMap<>();
    }

    public static <e> ConcurrentLinkedDeque<e> newConcurrentLinkedDeque() {
        return new ConcurrentLinkedDeque<>();
    }

    public static <e> ConcurrentLinkedQueue<e> newConcurrentLinkedQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    public static <e> ConcurrentSkipListSet<e> newConcurrentSkipListSet() {
        return new ConcurrentSkipListSet<>();
    }

    public static <E> Set<E> newHashSet(E[] e) {
        Set<E> set = newHashSet();
        Collections.addAll(set, e);
        return set;
    }

}
