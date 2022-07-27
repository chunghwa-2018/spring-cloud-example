package com.ch.spring.boot.eureka.controller;

import com.ch.spring.boot.eureka.model.Student;
import com.ch.spring.boot.eureka.service.StudentsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private StudentsService studentsService;

    /**
     * 消费者 get 请求
     * http post http://127.0.0.1:8085/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {

        // 开始时间
        Long beginTime = System.currentTimeMillis();
        logger.info("beginTime:{}", beginTime);

        // 调用 student-service
        String str = studentsService.hello();

        // 结束时间
        Long endTime = System.currentTimeMillis();
        logger.info("endTime:{}", endTime);
        logger.info("Speed time:" + (endTime - beginTime));

        return str;
    }

    /**
     * 消费者 get 请求
     * http get http://127.0.0.1:8085/welcome\?name\='赵四'
     *
     * @param name
     * @return
     */
    @GetMapping("/welcome")
    public String weclome(@RequestParam("name") String name) {
        logger.info("name:{}", name);

        if (StringUtils.isEmpty(name)) {
            return "name is empty!";
        }

        return studentsService.welcome(name);
    }

    /**
     * 消费者 get 请求
     * http get http://127.0.0.1:8085/welcome/1234
     *
     * @param id
     * @return
     */
    @GetMapping("/welcome/{id}")
    public Student welcome(@PathVariable("id") Long id) {
        logger.info("id:{}", id);

        if (null == id) {
            return new Student(0L, "未知", "0000-00-00", 0);
        }

        return studentsService.welcome(id);
    }

    /**
     * 消费者 post 请求
     * http post http://127.0.0.1:8085/welcome1 token:567890 id:=55500000 name='122221' birth='2019-07-08' sex:=1
     *
     * @param token
     * @param student
     * @return
     */
    @PostMapping("/welcome1")
    public Student welcome1(@RequestHeader("token") String token, @RequestBody Student student) {
        logger.info("token:{}", token);
        logger.info("student:{}", student);

        if (StringUtils.isEmpty(token)) {
            return null;
        }

        if (null == student) {
            return new Student(0L, "未知", "0000-00-00", 0);
        }

        return studentsService.welcome1(student);
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
