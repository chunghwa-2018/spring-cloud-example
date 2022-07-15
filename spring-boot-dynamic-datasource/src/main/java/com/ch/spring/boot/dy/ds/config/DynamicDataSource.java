package com.ch.spring.boot.dy.ds.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * <p>动态数据源</p>
 *
 * @ClassName DynamicDataSource
 * @Description 动态数据源
 * @Author zhaohongliang
 * @Date 2022-07-15 20:15
 * @Version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 创建每个线程独立其他线程的变量副本
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

    /**
     * 选择数据源之前先把多个数据源和默认数据源配置好
     *
     * @param defaultTargetDataSource   默认数据源
     * @param targetDataSources         目标数据源
     */
    public DynamicDataSource(DruidDataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    /**
     * 获取
     *
     * @return
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置
     *
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 清除
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();

    }
}
