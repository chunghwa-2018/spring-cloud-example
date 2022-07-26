package com.ch.spring.boot.feign.service;

import com.ch.spring.boot.feign.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>学生 service</p>
 *
 * @ClassName StudentsService
 * @Description 学生 service
 * @Author zhaohongliang
 * @Date 2022-07-18 18:41
 * @Version 1.0
 */
@FeignClient("student-service")
public interface StudentsService {

    /**
     * hello
     *
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();

    /**
     * welcome
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/welcome")
    String weclome(@RequestParam("name") String name);

    /**
     * welcome
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/welcome/{id}")
    Student welcome(@PathVariable("id") Long id);

    /**
     * welcome1
     *
     * @param student
     * @return
     */
    @PostMapping(value = "/welcome1")
    Student welcome1(@RequestBody Student student);



}
