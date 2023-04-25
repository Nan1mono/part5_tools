package com.part5.tools.core.toolkit;

@FunctionalInterface
public interface ReportDataConvert {

    <T> T convert(Class<T> tClass, String name);

}
