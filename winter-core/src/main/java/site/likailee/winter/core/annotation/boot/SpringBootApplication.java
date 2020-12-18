/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.annotation.boot;

import java.lang.annotation.*;

/**
 * @author likailee.llk
 * @version SpringBootApplication.java 2020/12/09 Wed 12:59 PM likai
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ComponentScan
public @interface SpringBootApplication {
}
