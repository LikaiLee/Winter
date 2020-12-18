/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.annotation.springmvc;

import site.likailee.winter.core.core.springmvc.enums.RequestMethod;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version RequestMapping.java 2020/12/18 Fri 2:36 PM likai
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value() default "";

    RequestMethod[] method() default {};
}
