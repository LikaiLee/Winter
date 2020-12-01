/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core;

import site.likailee.winter.annotation.Component;
import site.likailee.winter.annotation.RestController;
import site.likailee.winter.core.scanner.AnnotationClassScanner;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version ApplicationContext.java 2020/11/30 Mon 2:19 PM likai
 */
@Slf4j
public class ApplicationContext {
    private static final ApplicationContext APPLICATION_CONTEXT = new ApplicationContext();
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> CLASSES = new ConcurrentHashMap<>();

    public ApplicationContext() {
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 扫描包内的所有控制器
     * 对控制器下的方法建立映射
     *
     * @param packageName
     */
    public void loadClasses(String packageName) {
        // 获取所有带有 @RestController 的类
        Set<Class<?>> restControllers = AnnotationClassScanner.scan(packageName, RestController.class);
        // 获取所有带有 @Component 的类
        Set<Class<?>> components = AnnotationClassScanner.scan(packageName, Component.class);

        CLASSES.put(RestController.class, restControllers);
        CLASSES.put(Component.class, components);
    }
}
