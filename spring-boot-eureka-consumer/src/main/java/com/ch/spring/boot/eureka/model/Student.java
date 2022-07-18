package com.ch.spring.boot.eureka.model;

import java.io.Serializable;

/**
 * <p>student </p>
 *
 * @ClassName Student
 * @Description student
 * @Author zhaohongliang
 * @Date 2022-07-15 13:54
 * @Version 1.0
 */
public class Student implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private String birth;

    /**
     * 性别
     */
    private Integer sex;

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
     * 获取 姓名
     *
     * @return name 姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 生日
     *
     * @return birth 生日
     */
    public String getBirth() {
        return this.birth;
    }

    /**
     * 设置 生日
     *
     * @param birth 生日
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }

    /**
     * 获取 性别
     *
     * @return sex 性别
     */
    public Integer getSex() {
        return this.sex;
    }

    /**
     * 设置 性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 无参构造器
     */
    public Student() {
    }

    /**
     * 带参构造器
     *
     * @param id
     * @param name
     * @param birth
     * @param sex
     */
    public Student(Long id, String name, String birth, Integer sex) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.sex = sex;
    }
}
