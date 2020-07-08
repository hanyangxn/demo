package com.example.demo2.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Users implements Serializable {
    private Integer uid;

    private String username;

    private String gender;

    private Integer age;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}