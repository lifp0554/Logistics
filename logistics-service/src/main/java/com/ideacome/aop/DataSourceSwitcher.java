package com.ideacome.aop;

import org.springframework.util.Assert;

/**
 * TODO
 * 通用类，后期移到 common
 * 
 * @author laosan
 *
 */
public class DataSourceSwitcher {
    
    @SuppressWarnings("rawtypes")
    private static final ThreadLocal contextHolder = new ThreadLocal();

    @SuppressWarnings("unchecked")
    public static void setDataSource(String dataSource) {
        Assert.notNull(dataSource, "dataSource cannot be null");
        contextHolder.set(dataSource);
    }

    public static void setMaster() {
        clearDataSource();
    }

    public static void setSlave() {
        setDataSource("slave");
    }

    public static String getDataSource() {
        return (String) contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
