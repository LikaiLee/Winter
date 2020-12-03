package site.likailee.winter.annotation.springmvc;

import java.lang.annotation.*;

/**
 * @author likai
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestController {
    String value() default "";
}
