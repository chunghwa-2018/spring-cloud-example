package com.ch.spring.boot.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 浏览器F12，console出现 Uncaught: TypeError: e.indexOf is not a function.错误
 * 原因是 spring-cloud-netflix-hystrix-dashboard.jar/templates.hystrix/monitor.ftlh 使用的jquery版本错误
 * 修改 monitor.ftlh 为如下
 * $(window).load(function() {
 */
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {



	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}

}
