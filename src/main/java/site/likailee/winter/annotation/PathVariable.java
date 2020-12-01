package site.likailee.winter.annotation;


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