/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.aop.Aspect;
import site.likailee.winter.core.ioc.BeanFactory;

import java.util.Map;
import java.util.Set;

/**
 * @author likailee.llk
 * @version AbstractAopProxyBeanPostProcessor.java 2020/12/07 Mon 2:36 PM likai
 */
@Slf4j
public abstract class AbstractAopProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean) {
        Object wrapperProxyBean = bean;
        // 使用 Interceptor 链式代理
        Map<String, Interceptor> interceptorMap = BeanFactory.getBeansOfType(Interceptor.class);
        for (Map.Entry<String, Interceptor> entry : interceptorMap.entrySet()) {
            Interceptor interceptor = entry.getValue();
            if (interceptor.supports(bean)) {
                wrapperProxyBean = doWrapBean(wrapperProxyBean, interceptor);
            }
        }
        // 使用 @Aspect 代理
        Set<Object> adviceBeans = BeanFactory.getBeansForClassName(Aspect.class.getName());
        for (Object adviceBean : adviceBeans) {
            Interceptor aspectInterceptor = new InternallyAspectInterceptor(adviceBean);
            if (aspectInterceptor.supports(bean)) {
                wrapperProxyBean = doWrapBean(wrapperProxyBean, aspectInterceptor);
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
