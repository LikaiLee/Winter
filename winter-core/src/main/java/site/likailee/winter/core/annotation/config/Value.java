/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.annotation.config;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version Value.java 2020/12/11 Fri 2:51 PM likai
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    String value() default "";
}
