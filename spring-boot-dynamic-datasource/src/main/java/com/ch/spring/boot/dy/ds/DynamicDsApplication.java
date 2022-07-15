package com.ch.spring.boot.dy.ds;

import com.ch.spring.boot.dy.ds.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude= { DataSourceAutoConfiguration.class })
@Import({ DataSourceConfig.class })
public class DynamicDsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDsApplication.class, args);
	}

}
