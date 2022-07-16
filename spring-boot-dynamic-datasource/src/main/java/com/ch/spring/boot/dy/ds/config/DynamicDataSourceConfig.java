package com.ch.spring.boot.dy.ds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import java.util.Map;

/**
 * <p>动态数据源配置类</p>
 *
 * @ClassName DynamicDataSourceConfig
 * @Description 动态数据源配置类
 * @Author zhaohongliang
 * @Date 2022-07-16 01:13
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDynamic",
        transactionManagerRef = "transactionManagerDynamic",
        basePackages = {"com.ch.spring.boot.dy.ds.*.repository"}     // repository包名
)
public class DynamicDataSourceConfig {

    @Autowired
    @Qualifier("dynamicDataSource")
    private DynamicDataSource dynamicDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean(name = "entityManagerFactoryDynamic")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDynamic(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dynamicDataSource)
                .packages("com.ch.spring.boot.dy.ds.*.model")            // model包名
                .persistenceUnit("dynamicPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryDynamic(builder).getObject().createEntityManager();
    }

    @Bean(name = "transactionManagerDynamic")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryDynamic(builder).getObject());
    }
}
