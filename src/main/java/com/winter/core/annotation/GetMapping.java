package com.winter.core.annotation;

import java.lang.annotation.*;

/**
 * @author likai
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMapping {
    String value() default "";
}
