/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.factory;

import site.likailee.winter.core.annotation.aop.Aspect;
import site.likailee.winter.core.annotation.ioc.Component;
import site.likailee.winter.core.annotation.springmvc.RestController;
import site.likailee.winter.core.common.util.ReflectionUtils;

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

    public static void loadClass(String[] packageNames) {
        // 获取所有带有 @RestController 的类
        Set<Class<?>> restControllers = ReflectionUtils.scanAnnotatedClass(packageNames, RestController.class);
        // 获取所有带有 @Component 的类
        Set<Class<?>> components = ReflectionUtils.scanAnnotatedClass(packageNames, Component.class);
        // 获取所有带有 @Aspect 的类
        Set<Class<?>> aspects = ReflectionUtils.scanAnnotatedClass(packageNames, Aspect.class);

        CLASSES.put(RestController.class, restControllers);
        CLASSES.put(Component.class, components);
        CLASSES.put(Aspect.class, aspects);
    }
}
