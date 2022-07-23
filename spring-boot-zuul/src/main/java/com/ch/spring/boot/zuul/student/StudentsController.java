package com.ch.spring.boot.zuul.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
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

    /**
     * STUDENT-SERVICE 服务
     */
    private static final String STUDENT_SERVICE = "STUDENT-SERVICE";

    /**
     * 消费者 get 请求
     * http post http://127.0.0.1:8089/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello, zuul!";
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
