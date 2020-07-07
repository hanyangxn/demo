package com.example.demo2.dto;

public enum TypeService {
    TEST("test"),
    TEST2("test2");
    private String name;

    TypeService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}