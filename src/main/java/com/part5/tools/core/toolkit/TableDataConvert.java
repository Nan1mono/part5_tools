package com.part5.tools.core.toolkit;

@FunctionalInterface
public interface TableDataConvert {

    <T> T convert(Class<T> tClass, String name);

}
