package com.ch.spring.boot.eureka.controller;

import com.ch.spring.boot.eureka.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 生产者 get 请求
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        logger.info("看看是否负载均衡");
        return "hello,world!";
    }

    /**
     * 生产者 get 请求
     *
     * @param name
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(@RequestParam("name") String name) {
        logger.info("name:{}", name);
        return "welcome," + name;
    }

    /**
     * 生产者 get 请求
     *
     * @param id
     * @return
     */
    @GetMapping("/welcome/{id}")
    public Student welcome(@PathVariable("id") Long id) {
        logger.info("id:{}", id);
        Student student = new Student(id, "赵四", "", 1);
        return student;
    }

    /**
     * 生产者 post 请求
     *
     * @param student
     * @return
     */
    @PostMapping("/welcome1")
    public Student welcome(@RequestBody Student student) {
        logger.info("student:{}", student.toString());
        student = new Student(student.getId(), student.getName(), student.getBirth(), 2);
        return student;
    }
}
