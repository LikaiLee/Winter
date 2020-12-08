/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.factory;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.aop.Aspect;
import site.likailee.winter.annotation.aop.Order;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.core.aop.intercept.Interceptor;
import site.likailee.winter.core.aop.intercept.InternallyAspectInterceptor;
import site.likailee.winter.core.factory.ClassFactory;
import site.likailee.winter.exception.CannotInitializeConstructorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author likailee.llk
 * @version InterceptorFactory.java 2020/12/04 Fri 4:24 PM likai
 */
@Slf4j
public class InterceptorFactory {
    private static List<Interceptor> interceptors = new ArrayList<>();

    public static void loadInterceptors(String packageName) {
        // 实例化 Interceptor
        Set<Class<? extends Interceptor>> subClasses = ReflectionUtils.getImplClasses(packageName, Interceptor.class);
        for (Class<? extends Interceptor> subClass : subClasses) {
            try {
                interceptors.add(subClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new CannotInitializeConstructorException("not init constructor , the interceptor name :" + subClass.getSimpleName());
            }
        }
        // 实例化 Aspect
        ClassFactory.CLASSES.get(Aspect.class).forEach(aspectCls -> {
            // 将 Aspect 封装成 Interceptor
            Object aspect = ReflectionUtils.newInstance(aspectCls);
            Interceptor interceptor = new InternallyAspectInterceptor(aspect);
            if (aspectCls.isAnnotationPresent(Order.class)) {
                Order order = aspectCls.getAnnotation(Order.class);
                interceptor.setOrder(order.value());
            }
            interceptors.add(interceptor);
        });
        // 根据优先级排序
        interceptors = interceptors.stream().sorted(Comparator.comparing(Interceptor::getOrder)).collect(Collectors.toList());
        log.info("[{}] interceptors: {}", interceptors.size(), interceptors);
    }

    public static List<Interceptor> getInterceptors() {
        return interceptors;
    }
}
