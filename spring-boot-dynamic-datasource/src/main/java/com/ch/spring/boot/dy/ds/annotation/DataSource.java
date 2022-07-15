package com.ch.spring.boot.dy.ds.annotation;

import com.ch.spring.boot.dy.ds.config.DataSourceConfig;
import com.ch.spring.boot.dy.ds.constant.DataSourceConstant;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>动态数据源自定义注解</p>
 *
 * @ClassName DataSource
 * @Description 动态数据源自定义注解
 * @Author zhaohongliang
 * @Date 2022-07-15 22:29
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 默认为 primary
     *
     * @return
     */
    String value() default DataSourceConstant.DATASOURCE_PRIMARY;

}
