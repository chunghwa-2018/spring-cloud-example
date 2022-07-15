package com.ch.spring.boot.dy.ds.teacher.service.impl;



import com.ch.spring.boot.dy.ds.annotation.DataSource;
import com.ch.spring.boot.dy.ds.constant.DataSourceConstant;
import com.ch.spring.boot.dy.ds.teacher.model.Teacher;
import com.ch.spring.boot.dy.ds.teacher.repository.TeacherRepository;
import com.ch.spring.boot.dy.ds.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>teacher service 接口实现类</p>
 *
 * @ClassName TeacherServiceImpl
 * @Description teacher service 接口实现类
 * @Author zhaohongliang
 * @Date 2022-07-15 16:44
 * @Version 1.0
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @DataSource(DataSourceConstant.DATASOURCE_SECONDARY)
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
