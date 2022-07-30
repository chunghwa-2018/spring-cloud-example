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

    /**
     * 
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 增加了 spring security 的 eureka-server 需要注意 csrf 问题，否则无法正常使用
        // TODO 第一种方式 禁用 csrf
        // http.csrf().disable();
        // TODO 第二种方式 忽略所有 /eureka/** 请求
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);

    }

    /**
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {


        /**
         * 在增加了 spring security 的 eureka server 的项目实现优雅停服务
         *
         * 方式一：重写 protected void configure(HttpSecurity http) 方法，放行 /actuator/** 资源
         *         http.authorizeRequests().antMatchers("/actuator/**").permitAll().anyRequest().authenticated();
         *        然后设置 http.csrf().ignoringAntMatchers("/actuator/**")，并取消 super.configure(http) 继承，否则影响 /actuator/** 访问
         * 方式二：重写 public void configure(WebSecurity web) 方法，不拦截 /actuator/** 请求
         *        web.ignoring().antMatchers("/actuator/**")
         */
        web.ignoring().antMatchers("/actuator/**");
    }
}
