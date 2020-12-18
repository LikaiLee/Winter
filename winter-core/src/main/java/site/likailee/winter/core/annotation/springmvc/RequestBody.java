package site.likailee.winter.core.annotation.springmvc;

import java.lang.annotation.*;

/**
 * 方法参数注解，获取请求体参数
 *
 * @author likai
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestBody {
}
