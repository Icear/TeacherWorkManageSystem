package com.annotation;

import com.identity.Identity;

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
