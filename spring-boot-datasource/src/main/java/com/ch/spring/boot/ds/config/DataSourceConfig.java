package com.ch.spring.boot.ds.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

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
    @Primary
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
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
