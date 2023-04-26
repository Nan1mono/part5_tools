package com.part5.tools.core.toolkit.builder;

import com.part5.tools.entity.Table;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 顶层报表建造接口，是报表建造的最终规划者，
 * 所有的报表都会遵循它的生产规则，也必须遵循它的生产规则
 * 在报表的整个生产阶段中，大致可以分离为一下四个阶段
 * 1、获取传参和指定Mapper
 * 2、初始化数据（确定报表初始化时需要用到的数据）
 * 3、数据处理
 * 4、报表组装
 */
@ComponentScan(basePackages = "com.part5.tools.core.toolkit")
@Configuration
public interface ITableBuilder {

    /**
     * 初始化数据
     */
    TableAbstractBuilder initData();

    /**
     * 处理数据
     */
    void handling();

    /**
     * 组装报表
     */
    Table build();

}
