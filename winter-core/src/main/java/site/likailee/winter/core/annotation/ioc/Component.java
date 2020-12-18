package site.likailee.winter.core.annotation.ioc;

import java.lang.annotation.*;

/**
 * @author likai
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String name() default "";
}
