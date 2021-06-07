/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.common.util;

import site.likailee.winter.core.annotation.ioc.Component;

/**
 * @author likailee.llk
 * @version WinterUtils.java 2020/12/04 Fri 2:32 PM likai
 */
public class WinterUtils {
    /**
     * 若 {@link site.likailee.winter.core.annotation.ioc.Component} 有值则取值，否则取类名
     *
     * @param clazz
     * @return
     */
    public static String getBeanName(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Component.class)) {
            String componentName = clazz.getDeclaredAnnotation(Component.class).name();
            if (!"".equals(componentName)) {
                return componentName;
            }
        }
        return clazz.getName();
    }
}
