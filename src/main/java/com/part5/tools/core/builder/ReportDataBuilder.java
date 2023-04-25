package com.part5.tools.core.builder;

import com.part5.tools.entity.ReportData;
import com.part5.tools.util.SpringBeanContext;
import org.springframework.beans.factory.annotation.Autowired;

public interface ReportDataBuilder {

    /**
     * 初始化数据
     */
    ReportDataAbstractBuilder initData();

    /**
     * 处理数据
     */
    void handling();

    /**
     * 组装报表
     */
    ReportData build();

}
