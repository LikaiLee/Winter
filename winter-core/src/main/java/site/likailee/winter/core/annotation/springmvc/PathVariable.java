package site.likailee.winter.core.annotation.springmvc;


import java.lang.annotation.*;

/**
 * @author likai
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PathVariable {
    String value() default "";
}
