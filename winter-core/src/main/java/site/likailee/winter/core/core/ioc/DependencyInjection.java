/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.ioc;

/**
 * @author likailee.llk
 * @version DependencyInjection.java 2020/12/01 Tue 3:04 PM likai
 */
public class DependencyInjection {

    /**
     * 为 BEANS 内的 Bean 注入属性
     *
     * @param packageNames
     */
    public static void inject(String[] packageNames) {
        AutowiredBeanInitializer autowiredBeanInitializer = new AutowiredBeanInitializer(packageNames);
        BeanFactory.BEANS.values().forEach(autowiredBeanInitializer::initialize);
    }
}
