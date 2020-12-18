/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.processor;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.core.aop.intercept.Interceptor;
import site.likailee.winter.core.core.aop.proxy.CglibAspectProxy;

/**
 * @author likailee.llk
 * @version CglibAopProxyBeanPostProcessor.java 2020/12/04 Fri 4:33 PM likai
 */
@Slf4j
public class CglibAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {

    @Override
    protected Object doWrapBean(Object bean, Interceptor interceptor) {
        return CglibAspectProxy.getProxy(bean, interceptor);
    }

}