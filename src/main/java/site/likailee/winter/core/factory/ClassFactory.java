/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.factory;

import site.likailee.winter.annotation.aop.Aspect;
import site.likailee.winter.annotation.ioc.Component;
import site.likailee.winter.annotation.springmvc.RestController;
import site.likailee.winter.common.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version ClassFactory.java 2020/12/01 Tue 2:19 PM likai
 */
public class ClassFactory {
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> CLASSES = new ConcurrentHashMap<>();

    public static void loadClass(String packageName) {
        // 获取所有带有 @RestController 的类
        Set<Class<?>> restControllers = ReflectionUtils.scan(packageName, RestController.class);
        // 获取所有带有 @Component 的类
        Set<Class<?>> components = ReflectionUtils.scan(packageName, Component.class);
        // 获取所有带有 @Aspect 的类
        Set<Class<?>> aspects = ReflectionUtils.scan(packageName, Aspect.class);

        CLASSES.put(RestController.class, restControllers);
        CLASSES.put(Component.class, components);
        CLASSES.put(Aspect.class, aspects);
    }
}
