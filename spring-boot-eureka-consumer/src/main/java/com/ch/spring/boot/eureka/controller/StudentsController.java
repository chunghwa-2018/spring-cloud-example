package com.ch.spring.boot.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.POST;
import java.util.Map;


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

    /**
     * 消费者 get 请求
     * http post http://127.0.0.1:8085/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {

        // TODO 第一种方式
        ServiceInstance instance = loadBalancerClient.choose("STUDENT-SERVICE");
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
     * 消费者 post 请求
     * http post http://127.0.0.1:8085/welcome name='andy'
     *
     * @param map
     * @return
     */
    @PostMapping("/welcome")
    public String weclome(@RequestBody Map<String, Object> map) {
        ServiceInstance instance = loadBalancerClient.choose("STUDENT-SERVICE");
        String url = String.format("http://%s/welcome?name={1}", instance.getServiceId());
        return restTemplate.getForEntity(url, String.class,map.get("name")).getBody();
    }

    /**
     * index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
       return "hello";
    }
}
