/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.jdk;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.aop.AbstractAopProxyBeanPostProcessor;
import site.likailee.winter.core.aop.Interceptor;

/**
 * @author likailee.llk
 * @version JdkAopProxyBeanPostProcessor.java 2020/12/04 Fri 10:37 AM likai
 */
@Slf4j
public class JdkAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {

    @Override
    protected Object doWrapBean(Object bean, Interceptor interceptor) {
        return JdkInvocationHandler.getProxy(bean, interceptor);
    }
}
