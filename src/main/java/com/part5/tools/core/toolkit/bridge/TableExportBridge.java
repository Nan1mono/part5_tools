package com.part5.tools.core.toolkit.bridge;

import com.part5.tools.entity.Table;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 导出桥接
 * 手机各种导出类型
 */
public class TableExportBridge {

    private TableExport<?, ?> tableExport;

    public TableExportBridge() {

    }

    /**
     * 初始化
     *
     * @param tableExport 导出行为对象
     */
    public TableExportBridge(TableExport<?, ?> tableExport) {
        this.tableExport = tableExport;
    }

    public void setReportDataExport(TableExport<?, ?> tableExport) {
        this.tableExport = tableExport;
    }

    /**
     * excel导出方式
     *
     * @param response   导出结果返回
     * @param table 导出数据
     * @param title      标题
     */
    public void excelExport(HttpServletResponse response,
                            Table table,
                            Class<?> tClass,
                            String title) {
        this.tableExport.exportExcel(response, table, tClass, title);
    }

    /**
     * 动态列excel导出
     *
     * @param response   导出结果返回
     * @param table 动态excel数据
     * @param title      标题
     */
    public void exportExcel(HttpServletResponse response, Table table, String title) {
        List body = Arrays.asList(table.getBody().toArray());
        List header = Arrays.asList(table.getHeader().toArray());
        this.tableExport.exportExcel(response, tableExport.handler(header, body), title);
    }

}
