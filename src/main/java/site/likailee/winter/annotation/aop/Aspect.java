/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.annotation.aop;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version Aspect.java 2020/12/07 Mon 3:00 PM likai
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    String value() default "";
}
