package com.ideacome.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.ideacome.aop.DataSourceSwitcher;

/**
 * TODO
 * 通用类，后期移到 common
 * 
 * @author laosan
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceSwitcher.getDataSource();
    }
}
