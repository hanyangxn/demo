package com.hy.demo.annotation;

import java.lang.annotation.*;

/**
 * @author xlqi
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamCheck {

    /**
     * 字段校验规则，格式：字段名+校验规则+冒号+错误信息，例如：id<10:ID必须少于10
     */
    String[] value();
}
