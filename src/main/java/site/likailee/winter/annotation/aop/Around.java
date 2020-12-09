/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.annotation.aop;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version Around.java 2020/12/09 Wed 5:36 PM likai
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Around {
    String value() default "";
}
