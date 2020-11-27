package com.winter.core.annotation;

import java.lang.annotation.*;

/**
 * @author likai
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestController {
    String value() default "";
}
