package com.part5.tools.core.builder;

import com.part5.tools.entity.ReportData;
import com.part5.tools.util.SpringBeanContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 建造者模式去约定统一的产生规则
 * 在报表的整个生产阶段中，大致可以分离为一下四个阶段
 * 1、获取传参和指定Mapper
 * 2、初始化数据（确定报表初始化时需要用到的数据）
 * 3、数据处理
 * 4、报表组装
 */
@Component
public abstract class ReportDataAbstractBuilder implements ReportDataBuilder{

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
