/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.processor;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.aop.intercept.Interceptor;
import site.likailee.winter.core.aop.proxy.JdkAspectProxy;

/**
 * @author likailee.llk
 * @version JdkAopProxyBeanPostProcessor.java 2020/12/04 Fri 10:37 AM likai
 */
@Slf4j
public class JdkAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {

    @Override
    protected Object doWrapBean(Object bean, Interceptor interceptor) {
        return JdkAspectProxy.getProxy(bean, interceptor);
    }
}
