/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop;

import site.likailee.winter.core.aop.jdk.JdkAopProxyBeanPostProcessor;
import site.likailee.winter.core.aop.cglib.CglibAopProxyBeanPostProcessor;

/**
 * @author likailee.llk
 * @version BeanPostProcessorFactory.java 2020/12/07 Mon 2:30 PM likai
 */
public class BeanPostProcessorFactory {
    public static BeanPostProcessor getBeanPostProcessor(Object bean) {
        if (bean.getClass().getInterfaces().length > 0) {
            return new JdkAopProxyBeanPostProcessor();
        } else {
            return new CglibAopProxyBeanPostProcessor();
        }
    }
}
