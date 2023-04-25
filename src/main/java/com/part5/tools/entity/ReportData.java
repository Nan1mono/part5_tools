package com.part5.tools.entity;

import com.part5.tools.core.builder.ReportDataAbstractBuilder;
import lombok.*;

/**
 * 报表数据核心类，建造基础
 * 所有报表都符合该类的规范且必须要以该类型返回给调用者
 * 接下来的所有的报表格式都将存在该产出规则
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportData {

    // 指定列头
    public static final String HEADER = "header";

    // 指定列
    public static final String BODY = "body";

    private String title;

    private Object header;

    private Object body;

    public static ReportData create(ReportDataAbstractBuilder builder) {
        return builder.build();
    }

    /**
     * 用于转换行成指定类型
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T convert(Class<T> tClass, String name) {
        try {
            if (BODY.equals(name)) {
                return tClass.cast(body);
            } else if (HEADER.equals(name)) {
                return tClass.cast(header);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
