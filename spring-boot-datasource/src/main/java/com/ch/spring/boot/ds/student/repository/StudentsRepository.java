package com.ch.spring.boot.ds.student.repository;


import com.ch.spring.boot.ds.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>student repository</p>
 *
 * @ClassName StudentsRepository
 * @Description student repository
 * @Author zhaohongliang
 * @Date 2022-07-15 14:29
 * @Version 1.0
 */
public interface StudentsRepository extends JpaRepository<Student, Long> {

//    @Query(value = "select * from student", nativeQuery = true)
//    List<Student> findAll();

    /**
     * 通过名字查询
     *
     * @return
     */
    List<Student> findByName(String name);

}
