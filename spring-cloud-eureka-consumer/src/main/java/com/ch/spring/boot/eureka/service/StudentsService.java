package com.ch.spring.boot.eureka.service;

import com.ch.spring.boot.eureka.model.Student;

/**
 * <p>学生 service</p>
 *
 * @ClassName StudentsService
 * @Description 学生 service
 * @Author zhaohongliang
 * @Date 2022-07-18 18:41
 * @Version 1.0
 */
public interface StudentsService {

    /**
     * hello
     *
     * @return
     */
    String hello();

    /**
     * welcome
     *
     * @param name
     * @return
     */
    String welcome(String name);

    /**
     * welceom
     *
     * @param id
     * @return
     */
    Student welcome(Long id);

    /**
     * welcome1
     *
     * @param student
     * @return
     */
    Student welcome1(Student student);
}
