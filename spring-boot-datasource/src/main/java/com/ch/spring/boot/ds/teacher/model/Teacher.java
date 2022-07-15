package com.ch.spring.boot.ds.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>student</p>
 *
 * @ClassName Teacher
 * @Description student
 * @Author zhaohongliang
 * @Date 2022-07-15 16:34
 * @Version 1.0
 */
@Entity
@Table(name = "TEACHER")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Teacher {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 教师姓名
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 获取 id
     *
     * @return id id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置 id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 教师姓名
     *
     * @return name 教师姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 教师姓名
     *
     * @param name 教师姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 无参构造器
     */
    public Teacher() {
    }

    /**
     * 带参构造器
     *
     * @param id
     * @param name
     */
    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
