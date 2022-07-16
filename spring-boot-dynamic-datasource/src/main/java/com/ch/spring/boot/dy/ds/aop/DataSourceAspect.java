package com.ch.spring.boot.dy.ds.aop;

import com.ch.spring.boot.dy.ds.annotation.DataSource;
import com.ch.spring.boot.dy.ds.config.DynamicDataSource;
import com.ch.spring.boot.dy.ds.constant.DataSourceConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>动态数据源切面类</p>
 *
 * @ClassName DataSourceAspect
 * @Description 动态数据源切面类
 * @Author zhaohongliang
 * @Date 2022-07-15 22:32
 * @Version 1.0
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    @Before("@annotation(com.ch.spring.boot.dy.ds.annotation.DataSource)")
    public void beforeSwitchDataSource(JoinPoint point) {

        // 获取当前访问的 class
        Class<?> className = point.getTarget().getClass();
        // 获取当前方法名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (null != dataSource.value()) {
            DynamicDataSource.setDataSource(dataSource.value());
        } else {
            DynamicDataSource.setDataSource(DataSourceConstant.DATASOURCE_PRIMARY);
        }

    }

    @After("@annotation(com.ch.spring.boot.dy.ds.annotation.DataSource)")
    public void afterSwitchDataSource() {
        DynamicDataSource.clearDataSource();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
