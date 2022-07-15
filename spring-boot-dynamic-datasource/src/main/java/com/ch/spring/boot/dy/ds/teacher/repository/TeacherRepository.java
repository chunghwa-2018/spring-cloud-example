package com.ch.spring.boot.dy.ds.teacher.repository;


import com.ch.spring.boot.dy.ds.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>teacher repository</p>
 *
 * @ClassName TeacherRepository
 * @Description teacher repository
 * @Author zhaohongliang
 * @Date 2022-07-15 16:45
 * @Version 1.0
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
}
