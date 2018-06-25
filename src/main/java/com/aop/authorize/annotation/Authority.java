package com.aop.authorize.annotation;

import com.aop.authorize.identity.Identity;

import java.lang.annotation.*;

/**
 * 权限控制注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Authorities.class)
public @interface Authority {
    Identity role() default Identity.Guest;
}
