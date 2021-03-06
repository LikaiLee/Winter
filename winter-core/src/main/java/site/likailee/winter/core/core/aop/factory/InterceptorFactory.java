/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.aop.Around;
import site.likailee.winter.core.annotation.aop.Aspect;
import site.likailee.winter.core.annotation.aop.Order;
import site.likailee.winter.core.common.util.ReflectionUtils;
import site.likailee.winter.core.core.aop.intercept.AroundAspectInterceptor;
import site.likailee.winter.core.core.aop.intercept.Interceptor;
import site.likailee.winter.core.core.aop.intercept.InternallyAspectInterceptor;
import site.likailee.winter.core.core.factory.ClassFactory;
import site.likailee.winter.core.exception.CannotInitializeConstructorException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author likailee.llk
 * @version InterceptorFactory.java 2020/12/04 Fri 4:24 PM likai
 */
public class InterceptorFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorFactory.class);

    private static List<Interceptor> interceptors = new ArrayList<>();

    /**
     * 注册拦截器
     *
     * @param packageNames
     */
    public static void loadInterceptors(String[] packageNames) {
        Set<Class<? extends Interceptor>> interceptorClasses = ReflectionUtils.getImplClasses(packageNames, Interceptor.class);
        // 实例化 Interceptor
        for (Class<? extends Interceptor> interceptorCls : interceptorClasses) {
            try {
                interceptors.add(interceptorCls.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new CannotInitializeConstructorException("can not init constructor, the interceptor name: " + interceptorCls.getSimpleName());
            }
        }
        // 实例化 Aspect
        Set<Class<?>> aspects = ClassFactory.getAspects();
        aspects.forEach(aspectCls -> {
            // 将 Aspect 封装成 Interceptor
            Object aspect = ReflectionUtils.newInstance(aspectCls);
            // @Before & @After
            Interceptor interceptor = new InternallyAspectInterceptor(aspect);
            if (aspectCls.isAnnotationPresent(Order.class)) {
                Order order = aspectCls.getAnnotation(Order.class);
                interceptor.setOrder(order.value());
            }
            interceptors.add(interceptor);
            // @Around
            List<Interceptor> aroundInterceptors = getAroundInterceptors(aspect, aspectCls);
            interceptors.addAll(aroundInterceptors);
        });
        // 根据优先级排序
        interceptors = interceptors.stream().sorted(Comparator.comparing(Interceptor::getOrder)).collect(Collectors.toList());
        LOGGER.info("Load [{}] interceptors: {}", interceptors.size(), interceptors);
    }

    private static List<Interceptor> getAroundInterceptors(Object aspect, Class<?> aspectCls) {
        List<Interceptor> interceptors = new ArrayList<>();
        for (Method method : aspectCls.getMethods()) {
            if (method.isAnnotationPresent(Around.class)) {
                // 为每个 @Around 建立拦截器
                Interceptor interceptor = new AroundAspectInterceptor(aspect, method);
                interceptors.add(interceptor);
            }
        }
        return interceptors;
    }

    public static List<Interceptor> getInterceptors() {
        return interceptors;
    }
}
