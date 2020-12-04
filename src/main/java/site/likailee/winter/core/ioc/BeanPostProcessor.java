/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

/**
 * @author likailee.llk
 * @version BeanPostProcessor.java 2020/12/04 Fri 10:37 AM likai
 */
public interface BeanPostProcessor {
    /**
     * Bean 后置处理，进行 AOP 代理
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
