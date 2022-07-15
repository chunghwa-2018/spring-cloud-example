package com.ch.spring.boot.dy.ds.teacher.controller;

import com.ch.spring.boot.dy.ds.teacher.model.Teacher;
import com.ch.spring.boot.dy.ds.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>教师 controller</p>
 *
 * @ClassName 教师 controller
 * @Description teacher 控制器
 * @Author zhaohongliang
 * @Date 2022-07-15 16:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有教师
     *
     * @return
     */
    @GetMapping(value = "/query")
    public List<Teacher> queryAll() {
        return teacherService.findAll();
    }
}
