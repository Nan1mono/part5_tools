package com.part5.tools.core.toolkit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Bean工具
 * 用于从引用的IOC中获取相应的Bean
 * 在抽象类中不推荐使用@Autowired或者@Resources进行自动注入
 */
@ComponentScan(basePackages = "com.part5.tools.core.toolkit")
@Configuration
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