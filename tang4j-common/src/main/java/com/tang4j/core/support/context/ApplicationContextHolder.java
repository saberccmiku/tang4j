package com.tang4j.core.support.context;

import com.tang4j.core.util.InstanceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Map;

@DependsOn
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static final Logger logger = LogManager.getLogger();
    private static ApplicationContext applicationContext;
    private static final Map<String, Object> SERVICE_FACTORY = InstanceUtil.newHashMap();

    public ApplicationContextHolder() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> t) {
        assertApplicationContext();
        return applicationContext.getBean(t);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> t) {
        assertApplicationContext();
        return applicationContext.getBeansOfType(t);
    }

    public static Object getBean(String name) {
        assertApplicationContext();
        return applicationContext.getBean(name);
    }

    public static <T> T getService(Class<T> cls) {
        String clsName = cls.getName();
        T v = (T) SERVICE_FACTORY.get(clsName);
        if (v == null) {
            synchronized (clsName) {
                v = (T) SERVICE_FACTORY.get(clsName);
                if (v == null) {
                    logger.info("*****Autowire {}*****", cls);
                    v = getBean(cls);
                    logger.info("*****{} Autowired*****", cls);
                    SERVICE_FACTORY.put(clsName, v);
                }
            }
        }

        return v;
    }

    private static void assertApplicationContext() {
        if (ApplicationContextHolder.applicationContext == null) {
            throw new RuntimeException("applicationContext属性为null,请检查是否注入了ApplicationContextHolder!");
        }
    }

}
