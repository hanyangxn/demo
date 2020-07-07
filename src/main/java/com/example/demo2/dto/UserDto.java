package com.example.demo2.dto;

import com.example.demo2.result.TypeService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
    private  String name;
    private  Integer age;
    private TypeService type;

    public UserDto(String name, Integer age, TypeService type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public void setType(TypeService type) {
        this.type = type;
    }

    public UserDto() {
    }

    public UserDto(UserDto userDto) {
    }

    public TypeService getType() {
        return type;
    }

    public UserDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public  Integer getAge() {
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

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}
