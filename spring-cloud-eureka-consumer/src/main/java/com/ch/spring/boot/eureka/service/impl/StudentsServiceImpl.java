package com.ch.spring.boot.eureka.service.impl;

import com.ch.spring.boot.eureka.constant.StudentServiceConstant;
import com.ch.spring.boot.eureka.service.StudentsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>学生 service 接口实现类</p>
 *
 * @ClassName StudentsServiceImpl
 * @Description 学生 service 接口实现类
 * @Author zhaohongliang
 * @Date 2022-07-18 18:42
 * @Version 1.0
 */
@Service
public class StudentsServiceImpl implements StudentsService {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StudentsServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * hello
     *
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "studentFallback")
    public String hello() {

        // TODO 第一种方式
        ServiceInstance instance = loadBalancerClient.choose(StudentServiceConstant.STUDENT_SERVICE);
        // String url = String.format("http://%s:%s/hello", instance.getHost(), instance.getPort());
        String url = String.format("http://%s/hello", instance.getServiceId());

        // TODO 第二种方式
        // String url = "http://STUDENT-SERVICE/hello";

        logger.info("instanceId:{}", instance.getInstanceId());
        logger.info("serviceId:{}", instance.getServiceId());
        logger.info("host:{}", instance.getHost());
        logger.info("port:{}", instance.getPort());
        logger.info("uri:{}", instance.getUri());
        logger.info("url:{}", url);

        return restTemplate.getForEntity(url, String.class).getBody();
    }

    /**
     * 熔断请求的错误返回方法
     *
     * @return
     */
    public String studentFallback() {
        return "error";
    }
}
