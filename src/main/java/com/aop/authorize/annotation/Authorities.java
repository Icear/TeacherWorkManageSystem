package com.aop.authorize.annotation;


import java.lang.annotation.*;

/**
 * 使Authority支持重复注解
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorities {
    Authority[] value();
}
