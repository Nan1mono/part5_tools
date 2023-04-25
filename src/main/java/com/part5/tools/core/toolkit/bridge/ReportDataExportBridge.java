package com.part5.tools.core.toolkit.bridge;

import com.part5.tools.entity.ReportData;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 导出桥接
 * 手机各种导出类型
 */
public class ReportDataExportBridge {

    private ReportDataExport<?, ?> reportDataExport;

    public ReportDataExportBridge() {

    }

    /**
     * 初始化
     *
     * @param reportDataExport 导出行为对象
     */
    public ReportDataExportBridge(ReportDataExport<?, ?> reportDataExport) {
        this.reportDataExport = reportDataExport;
    }

    public void setReportDataExport(ReportDataExport<?, ?> reportDataExport) {
        this.reportDataExport = reportDataExport;
    }

    /**
     * excel导出方式
     *
     * @param response   导出结果返回
     * @param reportData 导出数据
     * @param title      标题
     */
    public void excelExport(HttpServletResponse response,
                            ReportData reportData,
                            Class<?> tClass,
                            String title) {
        this.reportDataExport.exportExcel(response, reportData, tClass, title);
    }

    /**
     * 动态列excel导出
     *
     * @param response   导出结果返回
     * @param reportData 动态excel数据
     * @param title      标题
     */
    public void exportExcel(HttpServletResponse response, ReportData reportData, String title) {
        List body = Arrays.asList(reportData.getBody().toArray());
        List header = Arrays.asList(reportData.getHeader().toArray());
        this.reportDataExport.exportExcel(response, reportDataExport.handler(header, body), title);
    }

}
