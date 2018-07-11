package com.wangkecheng.Model;

/**
 * Created by Kanson on 2018/7/12.
 */
public class Person {
    private String name;
    private Integer age;

    public Person()  {
        super();
    }
    public Person(String name,Integer gae) {
        super();
        this.name=name;
        this.age=gae;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}

