/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.factory;

import site.likailee.winter.annotation.Component;
import site.likailee.winter.annotation.RestController;
import site.likailee.winter.core.scanner.AnnotationClassScanner;

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
        Set<Class<?>> restControllers = AnnotationClassScanner.scan(packageName, RestController.class);
        // 获取所有带有 @Component 的类
        Set<Class<?>> components = AnnotationClassScanner.scan(packageName, Component.class);

        CLASSES.put(RestController.class, restControllers);
        CLASSES.put(Component.class, components);
    }
}
