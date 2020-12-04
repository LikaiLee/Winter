/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.ioc.Component;
import site.likailee.winter.annotation.springmvc.RestController;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.common.util.WinterUtils;
import site.likailee.winter.core.factory.ClassFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version BeanFactory.java 2020/12/01 Tue 2:29 PM likai
 */
@Slf4j
public class BeanFactory {
    /**
     * 存放所有 Bean 实例
     */
    public static final Map<String, Object> BEANS = new ConcurrentHashMap<>(128);

    /**
     * 实例化 Component，RestController
     */
    public static void loadBeans() {
        ClassFactory.CLASSES.forEach((annotation, classes) -> {
            // @Component 使用 name 或 类名标识
            if (annotation == Component.class) {
                for (Class<?> clazz : classes) {
                    String beanName = WinterUtils.getBeanName(clazz);
                    Object bean = ReflectionUtils.newInstance(clazz);
                    BEANS.put(beanName, bean);
                }
            }
            // @RestController 使用类名标识
            if (annotation == RestController.class) {
                for (Class<?> clazz : classes) {
                    Object bean = ReflectionUtils.newInstance(clazz);
                    BEANS.put(clazz.getName(), bean);
                }
            }
        });
        log.info("beans: {}", BEANS.keySet());
    }
}
