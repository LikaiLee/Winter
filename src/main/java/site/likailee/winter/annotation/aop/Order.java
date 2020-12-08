/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.annotation.aop;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version Order.java 2020/12/08 Tue 4:38 PM likai
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    int value() default -1;
}
