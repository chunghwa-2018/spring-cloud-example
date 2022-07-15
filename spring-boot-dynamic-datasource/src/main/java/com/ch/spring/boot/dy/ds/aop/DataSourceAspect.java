package com.ch.spring.boot.dy.ds.aop;

import com.ch.spring.boot.dy.ds.annotation.DataSource;
import com.ch.spring.boot.dy.ds.config.DynamicDataSource;
import com.ch.spring.boot.dy.ds.constant.DataSourceConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("@annotation(com.ch.spring.boot.dy.ds.annotation.DataSource)")
    public void dataSourcecPointcut() {

    }

    @Around("dataSourcecPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource.value().equals(DataSourceConstant.DATASOURCE_PRIMARY)) {
            DynamicDataSource.setDataSource(DataSourceConstant.DATASOURCE_PRIMARY);
        } else {
            DynamicDataSource.setDataSource(DataSourceConstant.DATASOURCE_SECONDARY);
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
