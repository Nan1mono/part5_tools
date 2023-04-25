package com.part5.tools.entity;

import lombok.*;

import java.util.List;

/**
 * Excel实体类
 * 是构建excel报表导出的核心部件，所有报表操作调用者应该以此类型为基础进行所有操作
 * 调用者所接受的对象也应该以此对象为基础
 * 其中sheet是sheet名称，headerList负责表格列，dataList负责数据
 * <p>
 * lee 2023/04/25
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExcelTable {

    private String sheet;

    private List<List<String>> headerList;

    private List<List<Object>> dataList;

}
