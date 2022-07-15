package com.ch.spring.boot.dy.ds.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ch.spring.boot.dy.ds.constant.DataSourceConstant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>数据源配置</p>
 *
 * @ClassName DataSourceConfig
 * @Description 数据源配置
 * @Author zhaohongliang
 * @Date 2022-07-14 00:22
 * @Version 1.0
 */
@Configuration(proxyBeanMethods = false)
public class DataSourceConfig {

    /**
     * ch_student
     *
     * @return
     */
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DruidDataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * ch_teacher
     *
     * @return
     */
    @Bean("secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DruidDataSource secondaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(DruidDataSource primaryDataSource, DruidDataSource secondaryDataSource) {
        Map<Object, Object> targetDatasource = new HashMap<Object, Object>();
        targetDatasource.put(DataSourceConstant.DATASOURCE_PRIMARY, primaryDataSource);
        targetDatasource.put(DataSourceConstant.DATASOURCE_SECONDARY, secondaryDataSource);
        return new DynamicDataSource(primaryDataSource, targetDatasource);
    }
}
