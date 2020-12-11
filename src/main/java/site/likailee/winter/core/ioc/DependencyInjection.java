/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author likailee.llk
 * @version DependencyInjection.java 2020/12/01 Tue 3:04 PM likai
 */
@Slf4j
public class DependencyInjection {

    /**
     * 为 BEANS 内的 Bean 注入属性
     *
     * @param packageNames
     */
    public static void inject(String[] packageNames) {
        BeanFactory.BEANS.values().forEach(bean -> prepareBean(bean, packageNames));
    }

    private static void prepareBean(Object beanInstance, String[] packageNames) {
        AutowiredBeanProcessor autowiredBeanProcessor = new AutowiredBeanProcessor(packageNames);
        autowiredBeanProcessor.initialize(beanInstance);
    }
}
