/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.annotation.boot;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version ComponentScan.java 2020/12/09 Wed 1:00 PM likai
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    String[] value() default {};
}
