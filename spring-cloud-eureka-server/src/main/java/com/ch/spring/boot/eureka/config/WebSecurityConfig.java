package com.ch.spring.boot.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>WebSecurityConfig</p>
 *
 * @ClassName WebSecurityConfig
 * @Description WebSecurityConfig
 * @Author zhaohongliang
 * @Date 2022-07-28 22:38
 * @Version 1.0
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cannot execute request on any known server
        // TODO 第一种方式 禁用 csrf
        // http.csrf().disable();
        // TODO 第二种方式 忽略所有 /eureka/** 请求
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/actuator/**");
    }
}
