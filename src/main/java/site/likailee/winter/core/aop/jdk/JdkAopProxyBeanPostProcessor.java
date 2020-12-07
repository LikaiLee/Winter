/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.jdk;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.core.aop.BeanPostProcessor;
import site.likailee.winter.core.aop.Interceptor;
import site.likailee.winter.exception.CannotInitializeConstructorException;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author likailee.llk
 * @version JdkAopProxyBeanPostProcessor.java 2020/12/04 Fri 10:37 AM likai
 */
@Slf4j
public class JdkAopProxyBeanPostProcessor implements BeanPostProcessor {
    private List<Interceptor> interceptors;

    public JdkAopProxyBeanPostProcessor(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean) {
        // 链式代理
        Object wrapperProxyBean = bean;
        for (Interceptor interceptor : interceptors) {
            if (interceptor.supports(bean)) {
                // log.info("jdk proxy: {}", bean.getClass().getSimpleName());
                wrapperProxyBean = JdkInvocationHandler.getProxy(wrapperProxyBean, interceptor);
            }
        }
        return wrapperProxyBean;
    }
}
