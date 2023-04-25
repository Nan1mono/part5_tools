package com.part5.tools.entity;

import com.part5.tools.core.toolkit.ReportDataConvert;
import com.part5.tools.core.toolkit.builder.ReportDataAbstractBuilder;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 报表数据核心类，建造基础
 * 所有报表都符合该类的规范且必须要以该类型返回给调用者
 * 接下来的所有的报表格式都将存在该产出规则
 * 其中header和body用的是最大的集合接口，可以传入任何需要的集合
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportData {

    // 指定列头
    public static final String HEADER = "header";

    // 指定列
    public static final String BODY = "body";

    private String title;

    private Collection<?> header;

    private Collection<?> body;

    public static ReportData create(ReportDataAbstractBuilder builder) {
        return builder.build();
    }

    /**
     * 转换header和body的指定类型
     * 默认将转换为ArrayList
     */
    public <T> ArrayList<T> convert(Class<T> tClass, String name) {
        ArrayList<T> list = new ArrayList<>();
        Collection<?> traverseOjb = null;
        if (HEADER.equals(name)) {
            traverseOjb = this.header;
        } else if (BODY.equals(name)) {
            traverseOjb = this.body;
        }
        if (CollectionUtils.isEmpty(traverseOjb)) {
            return list;
        }
        for (Object obj : traverseOjb) {
            T cast = tClass.cast(obj);
            list.add(cast);
        }
        return list;
    }

    /**
     * 提供了自定义接口方法
     * 可以自由选择转换成需要的类型
     * 需要实现ReportDataConvert方法
     */
    public <T> T convert(ReportDataConvert reportDataConvert) {
        return null;
    }

}
