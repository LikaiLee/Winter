package site.likailee.winter.annotation;

import java.lang.annotation.*;

/**
 * @author likai
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
