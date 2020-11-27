/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.annotation;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    String value() default "";
}
