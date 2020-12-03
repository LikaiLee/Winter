package site.likailee.winter.annotation.ioc;

import java.lang.annotation.*;

/**
 * 获取特定的 Bean
 * @author likai
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Qualifier {
    String value() default "";
}
