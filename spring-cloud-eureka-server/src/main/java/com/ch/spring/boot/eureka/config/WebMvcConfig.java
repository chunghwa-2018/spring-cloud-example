package com.ch.spring.boot.eureka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>WebMvcConfig</p>
 *
 * @ClassName WebMvcConfig
 * @Description WebMvcConfig
 * @Author zhaohongliang
 * @Date 2022-07-28 22:23
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebMvcConfig {

    @Bean
    public WebMvcConfigurer testConfigurer() {
        return new WebMvcConfigurer() {


        };
    }

}
