/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.annotation.aop;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version After.java 2020/12/07 Mon 3:00 PM likai
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface After {
    String value() default "";
}
