package com.part5.tools.core.toolkit.builder;

import com.part5.tools.entity.ReportData;
import com.part5.tools.core.toolkit.SpringBeanContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 第一层建造约束
 * 是顶层规则的第一层实现，该抽象类提供给开发者进行调用，同样遵守了三层生产规则
 * 但其内部额外定义了SpringBeanContext规则
 * 在整个生产阶段中尽量避免使用@Autowired或者@Resources注解进行自动注入
 * 优先考虑必须通过springBeanContext.getBean的方法进行注入，目的是为了保证整个抽象过程的强可读性
 *
 */
@ComponentScan(basePackages = "com.part5.tools.core.toolkit")
@Configuration
public abstract class ReportDataAbstractBuilder implements IReportDataBuilder{

    private Map<String, Object> param;

    private static SpringBeanContext springBeanContext;

    @Autowired
    private void setApplicationContext(SpringBeanContext springBeanContext) {
        ReportDataAbstractBuilder.springBeanContext = springBeanContext;
    }

    /**
     * 获取传入的参数
     */
    public ReportDataAbstractBuilder setParam(Map<String, Object> param) {
        this.param = param;
        return this;
    }

    /**
     * 初始化数据
     */
    @Override
    public abstract ReportDataAbstractBuilder initData();

    /**
     * 处理数据
     */
    @Override
    public abstract void handling();

    /**
     * 组装报表
     */
    @Override
    public abstract ReportData build();

    /**
     * 根据参数名称获取指定类型的参数
     */
    public <T> T getParam(String name, Class<T> tClass) {
        Object value = this.param.get(name);
        if (value != null) {
            return tClass.cast(value);
        }
        return null;
    }

    /**
     * 获取指定类型的mapper/Service/...等所有的Bean
     */
    public <E> E getBean(Class<E> clazz) {
        return springBeanContext.getBean(clazz);
    }
}
