/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.exception.CannotInitializeConstructorException;

import java.util.Objects;
import java.util.Set;

/**
 * @author likailee.llk
 * @version JdkAopProxyBeanPostProcessor.java 2020/12/04 Fri 10:37 AM likai
 */
@Slf4j
public class JdkAopProxyBeanPostProcessor implements BeanPostProcessor {

    private static Set<Class<? extends Interceptor>> interceptors = null;
    private final String packageName;

    public JdkAopProxyBeanPostProcessor(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (Objects.isNull(interceptors)) {
            interceptors = ReflectionUtils.getImplClasses(packageName, Interceptor.class);
        }
        // 链式代理
        Object wrapperProxyBean = bean;
        for (Class<? extends Interceptor> interceptorCls : interceptors) {
            try {
                Interceptor interceptor = interceptorCls.newInstance();
                if (interceptor.supports(beanName)) {
                    wrapperProxyBean = ProxyFactory.getProxy(wrapperProxyBean, interceptor);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                throw new CannotInitializeConstructorException("can not initialize interceptor: " + interceptorCls.getSimpleName());
            }
        }
        return wrapperProxyBean;
    }
}
