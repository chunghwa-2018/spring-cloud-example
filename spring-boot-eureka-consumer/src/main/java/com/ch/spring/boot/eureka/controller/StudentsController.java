package com.ch.spring.boot.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/student")
public class StudentsController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);

    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String consumer() {
        logger.info("------");
        String url = "http://STUDENT-SERVICE/student/eureka";
        String str = restTemplate.getForEntity(url, String.class).getBody();
        return str;
    }

    @GetMapping("/index")
    public String index() {
       return "hello";
    }
}
