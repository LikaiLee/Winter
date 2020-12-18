/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.factory;

import site.likailee.winter.core.core.aop.processor.BeanPostProcessor;
import site.likailee.winter.core.core.aop.processor.CglibAopProxyBeanPostProcessor;
import site.likailee.winter.core.core.aop.processor.JdkAopProxyBeanPostProcessor;

/**
 * @author likailee.llk
 * @version AopProxyPostProcessorFactory.java 2020/12/07 Mon 2:30 PM likai
 */
public class AopProxyPostProcessorFactory {
    public static BeanPostProcessor get(Class<?> beanClass) {
        if (beanClass.isInterface() || beanClass.getInterfaces().length > 0) {
            return new JdkAopProxyBeanPostProcessor();
        } else {
            return new CglibAopProxyBeanPostProcessor();
        }
    }
}
