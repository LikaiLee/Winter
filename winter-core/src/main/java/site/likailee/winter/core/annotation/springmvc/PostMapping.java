/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.annotation.springmvc;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostMapping {
    String value() default "";
}
