/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.cglib;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.aop.AbstractAopProxyBeanPostProcessor;
import site.likailee.winter.core.aop.Interceptor;

/**
 * @author likailee.llk
 * @version CglibAopProxyBeanPostProcessor.java 2020/12/04 Fri 4:33 PM likai
 */
@Slf4j
public class CglibAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {

    @Override
    protected Object doWrapBean(Object bean, Interceptor interceptor) {
        return CglibMethodInterceptor.getProxy(bean, interceptor);
    }

}