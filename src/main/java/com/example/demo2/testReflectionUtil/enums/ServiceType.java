package com.example.demo2.testReflectionUtil.enums;

public enum ServiceType {

    SERVICE1("service1"),
    SERVICE2("service2"),
    SERVICE3("service3");

    private String name;

    ServiceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
