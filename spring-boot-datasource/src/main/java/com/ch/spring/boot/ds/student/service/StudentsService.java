package com.ch.spring.boot.ds.student.service;

import com.ch.spring.boot.ds.student.model.Student;

import java.util.List;

/**
 * <p>student service 接口</p>
 *
 * @ClassName StudentsService
 * @Description student service 接口
 * @Author zhaohongliang
 * @Date 2022-07-15 13:51
 * @Version 1.0
 */
public interface StudentsService {

    /**
     * 查询所有学生
     *
     * @return
     */
    List<Student> findAll();
}
