package com.ch.spring.boot.ds.teacher.service;

import com.ch.spring.boot.ds.teacher.model.Teacher;

import java.util.List;

/**
 * <p>teacher service 接口</p>
 *
 * @ClassName TeacherService
 * @Description teacher service 接口
 * @Author zhaohongliang
 * @Date 2022-07-15 16:42
 * @Version 1.0
 */
public interface TeacherService {

    /**
     * 查询所有教师
     *
     * @return
     */
    List<Teacher> findAll();
}
