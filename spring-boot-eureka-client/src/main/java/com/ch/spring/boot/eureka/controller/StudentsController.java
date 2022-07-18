package com.ch.spring.boot.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

//    @Autowired
//    private DiscoveryClient client;

    /**
     * hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        logger.info("看看是否负载均衡");
        return "hello,world!";
    }

    /**
     * welcome
     *
     * @param name
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(@RequestParam("name") String name) {
        logger.info("name:{}", name);
        return "welcome," + name;
    }
}
