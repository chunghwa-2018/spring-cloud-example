package com.ch.spring.boot.ds.student.controller;

import com.ch.spring.boot.ds.student.model.Student;
import com.ch.spring.boot.ds.student.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>学生 controller</p>
 *
 * @ClassName StudentsController
 * @Description 学生 controller
 * @Author zhaohongliang
 * @Date 2022-07-15 13:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/student")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    /**
     * 查询所有学生
     *
     * @return
     */
    @PostMapping(value = "/query")
    @ResponseBody
    public List<Student> queryAll() {
        List<Student> list = studentsService.findAll();
        return list;
    }
}
