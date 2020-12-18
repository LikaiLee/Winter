/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.processor;

import site.likailee.winter.core.core.aop.factory.InterceptorFactory;
import site.likailee.winter.core.core.aop.intercept.Interceptor;

import java.util.List;

/**
 * @author likailee.llk
 * @version AbstractAopProxyBeanPostProcessor.java 2020/12/07 Mon 2:36 PM likai
 */
public abstract class AbstractAopProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean) {
        Object wrapperProxyBean = bean;
        List<Interceptor> interceptors = InterceptorFactory.getInterceptors();
        // 链式代理
        for (Interceptor interceptor : interceptors) {
            if (interceptor.supports(bean)) {
                wrapperProxyBean = doWrapBean(wrapperProxyBean, interceptor);
            }
        }
        return wrapperProxyBean;
    }

    /**
     * 使用 interceptor 包装 bean
     * 由子类实现具体的代理方式
     *
     * @param bean
     * @param interceptor
     * @return
     */
    protected abstract Object doWrapBean(Object bean, Interceptor interceptor);
}
