package com.ch.spring.boot.eureka.controller;

import com.ch.spring.boot.eureka.model.Student;
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

    /**
     * STUDENT-SERVICE 服务
     */
    private static final String STUDENT_SERVICE = "STUDENT-SERVICE";

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
        ServiceInstance instance = loadBalancerClient.choose(STUDENT_SERVICE);
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
     * 消费者 get 请求
     * http get http://127.0.0.1:8085/welcome\?name\='赵四'
     *
     * @param name
     * @return
     */
    @GetMapping("/welcome")
    public String weclome(@RequestParam("name") String name) {
        logger.info("name:{}", name);
        ServiceInstance instance = loadBalancerClient.choose(STUDENT_SERVICE);
        String url = String.format("http://%s/welcome?name={1}", instance.getServiceId());
        return restTemplate.getForEntity(url, String.class, name).getBody();
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
        ServiceInstance instance = loadBalancerClient.choose(STUDENT_SERVICE);
        String url = String.format("http://%s/welcome/{id}", instance.getServiceId());
        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(url, Student.class, id);
        Student student = responseEntity.getBody();
        return student;
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
        ServiceInstance instance = loadBalancerClient.choose(STUDENT_SERVICE);
        String url = String.format("http://%s/welcome1", instance.getServiceId());
        student = restTemplate.postForObject(url, student, Student.class, "秃噜扣碎否");
        return student;
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
