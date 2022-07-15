package com.ch.spring.boot.dy.ds.student.controller;

import com.ch.spring.boot.dy.ds.student.model.Student;
import com.ch.spring.boot.dy.ds.student.service.StudentsService;
import com.ch.spring.boot.dy.ds.teacher.model.Teacher;
import com.ch.spring.boot.dy.ds.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有学生
     *
     * @return
     */
    @PostMapping(value = "/query")
    public Map<String, Object> queryAll() {
        List<Student> students = studentsService.findAll();
        List<Teacher> techears = teacherService.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("students", students);
        params.put("teachers", techears);
        return params;
    }
}
