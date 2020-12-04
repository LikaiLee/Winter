/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop;

import site.likailee.winter.common.util.ReflectionUtils;
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
public class InterceptorFactory {
    private static List<Interceptor> interceptors = new ArrayList<>();

    public static void loadInterceptors(String packageName) {
        // 获取拦截器
        Set<Class<? extends Interceptor>> subClasses = ReflectionUtils.getImplClasses(packageName, Interceptor.class);
        for (Class<? extends Interceptor> subClass : subClasses) {
            try {
                interceptors.add(subClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new CannotInitializeConstructorException("not init constructor , the interceptor name :" + subClass.getSimpleName());
            }
        }
        interceptors = interceptors.stream().sorted(Comparator.comparing(Interceptor::getOrder)).collect(Collectors.toList());
    }

    public static List<Interceptor> getInterceptors() {
        return interceptors;
    }
}
