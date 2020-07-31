package com.example.demo2.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class Person implements Serializable {
    private Integer id;

    private String perName;

    private String perPass;

    private static final long serialVersionUID = 1L;
}