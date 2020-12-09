/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.factory;

import site.likailee.winter.core.aop.intercept.BeanPostProcessor;
import site.likailee.winter.core.aop.intercept.JdkAopProxyBeanPostProcessor;
import site.likailee.winter.core.aop.intercept.CglibAopProxyBeanPostProcessor;

/**
 * @author likailee.llk
 * @version BeanPostProcessorFactory.java 2020/12/07 Mon 2:30 PM likai
 */
public class BeanPostProcessorFactory {
    public static BeanPostProcessor get(Class<?> beanClass) {
        if (beanClass.isInterface() || beanClass.getInterfaces().length > 0) {
            return new JdkAopProxyBeanPostProcessor();
        } else {
            return new CglibAopProxyBeanPostProcessor();
        }
    }
}
