/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.annotation.aop;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version Pointcut.java 2020/12/07 Mon 3:01 PM likai
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pointcut {
    String value() default "";
}
