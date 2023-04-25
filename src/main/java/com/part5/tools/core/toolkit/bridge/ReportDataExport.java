package com.part5.tools.core.toolkit.bridge;

import com.part5.tools.core.toolkit.SpringBeanContext;
import com.part5.tools.entity.ExcelTable;
import com.part5.tools.entity.ReportData;
import com.part5.tools.util.ExcelTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 导出行为接口
 * 可以定义多种导出模式
 * 所有导出模式均遵守ReportData风格
 */
@Component
public abstract class ReportDataExport<H, D> {

    private static SpringBeanContext springBeanContext;

    @Autowired
    private void setApplicationContext(SpringBeanContext springBeanContext) {
        ReportDataExport.springBeanContext = springBeanContext;
    }

    /**
     * excel导出方式
     *
     * @param response   导出结果返回
     * @param reportData 导出数据
     * @param title      标题
     */
    public void exportExcel(HttpServletResponse response, ReportData reportData,
                            Class<?> tClass, String title) {
        List<?> convert = Arrays.asList(reportData.getBody().toArray());
        List list = new ArrayList<>();
        for (Object obj : convert) {
            if (obj != null) {
                list.add(tClass.cast(obj));
            }
        }
        ExcelTransfer transfer = this.getBean(ExcelTransfer.class);
        try {
            transfer.exportExcel(response, list, title, "sheet1", tClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract ExcelTable handler(List<H> header, List<D> data);

    /**
     * 动态列excel导出
     *
     * @param response   导出结果返回
     * @param excelTable 动态excel数据
     * @param title      标题
     */
    public void exportExcel(HttpServletResponse response, ExcelTable excelTable, String title) {
        try {
            ExcelTransfer.exportExcel(response, title, excelTable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 创建excel-header集合
     */
    public List<List<String>> createExcelHeaderList(List<String> fixedHeaderList, List<String> dynamicHeaderList) {
        List<List<String>> headerList = new ArrayList<>();
        for (String fixedHeader : fixedHeaderList) {
            headerList.add(Collections.singletonList(fixedHeader));
        }
        for (String dynamicHeader : dynamicHeaderList) {
            headerList.add(Collections.singletonList(dynamicHeader));
        }
        return headerList;
    }


    /**
     * 获取指定类型的mapper/Service/...等所有的Bean
     */
    public <E> E getBean(Class<E> clazz) {
        return springBeanContext.getBean(clazz);
    }

}
