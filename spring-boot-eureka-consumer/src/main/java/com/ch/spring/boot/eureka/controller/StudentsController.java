package com.ch.spring.boot.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * <p>学生 controller </p>
 *
 * @ClassName StudentsController
 * @Description 学生 controller
 * @Author zhaohongliang
 * @Date 2022-07-16 20:45
 * @Version 1.0
 */
@RestController
public class StudentsController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/consumer")
    public String consumer() {
        ServiceInstance instance = loadBalancerClient.choose("STUDENT-SERVICE");
        // String url = String.format("http://%s:%s/hello", instance.getHost(), instance.getPort());
        String url = String.format("http://%s/hello", instance.getServiceId());
        // String url = "http://STUDENT-SERVICE/hello";
        logger.info("host:{}", instance.getHost());
        logger.info("port:{}",instance.getPort());
        logger.info("url:{}", url);
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    @RequestMapping("/index")
    public String index() {
       return "hello";
    }
}
