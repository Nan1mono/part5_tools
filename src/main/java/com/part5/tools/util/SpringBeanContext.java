package com.part5.tools.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanContext.applicationContext = applicationContext;
    }

    /**
     * 获取指定类型的mapper/Service/...等所有的Bean
     */
    public  <E> E getBean(Class<E> clazz) {
        return applicationContext.getBean(clazz);
    }

}