package com.hy.demo.jd;

@FunctionalInterface
public interface CustermerService<F,T> {
    T test(F f);
}
