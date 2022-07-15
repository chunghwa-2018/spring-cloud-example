package com.ch.spring.boot.dy.ds.student.service.impl;

import com.ch.spring.boot.dy.ds.annotation.DataSource;
import com.ch.spring.boot.dy.ds.constant.DataSourceConstant;
import com.ch.spring.boot.dy.ds.student.model.Student;
import com.ch.spring.boot.dy.ds.student.repository.StudentsRepository;
import com.ch.spring.boot.dy.ds.student.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>student service 接口实现类</p>
 *
 * @ClassName StudentsServiceImpl
 * @Description student service 接口实现类
 * @Author zhaohongliang
 * @Date 2022-07-15 13:52
 * @Version 1.0
 */
@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }
}
