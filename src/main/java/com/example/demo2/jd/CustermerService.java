package com.example.demo2.jd;

@FunctionalInterface
public interface CustermerService<F,T> {
    T test(F f);
}
