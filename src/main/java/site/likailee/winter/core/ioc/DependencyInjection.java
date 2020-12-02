/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import site.likailee.winter.annotation.Autowired;
import site.likailee.winter.annotation.Component;
import site.likailee.winter.annotation.Qualifier;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.exception.InterfaceNotImplementedException;
import site.likailee.winter.exception.NoUniqueBeanDefinitionException;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @author likailee.llk
 * @version DependencyInjection.java 2020/12/01 Tue 3:04 PM likai
 */
@Slf4j
public class DependencyInjection {
    /**
     * 为 BEANS 内的 Bean 注入属性
     *
     * @param packageName
     */
    public static void inject(String packageName) {
        Map<String, Object> beans = BeanFactory.BEANS;
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            // 遍历所有属性，为 @Autowired 的属性注入依赖
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                // 获取属性对应的类
                Class<?> fieldClass = field.getType();
                String beanName = getBeanName(fieldClass);
                // 如果是接口获取实现类
                if (fieldClass.isInterface()) {
                    Set<Class<?>> implClasses = getImplClasses(packageName, fieldClass);
                    if (implClasses.size() == 0) {
                        throw new InterfaceNotImplementedException("interface " + fieldClass.getName() + " does not have implemented");
                    }
                    // 只有一个实现类
                    if (implClasses.size() == 1) {
                        Class<?> implClass = implClasses.iterator().next();
                        beanName = getBeanName(implClass);
                    }
                    // 有多个实现类
                    else {
                        Qualifier qualifier = field.getDeclaredAnnotation(Qualifier.class);
                        if (qualifier == null) {
                            throw new NoUniqueBeanDefinitionException("interface " + fieldClass.getName() + " has more than one implementation");
                        }
                        beanName = qualifier.value();
                    }
                }
                Object fieldInstance = beans.get(beanName);
                if (fieldInstance == null) {
                    throw new NoUniqueBeanDefinitionException("can not inject field " + field.getName());
                }
                // 设置属性对应的实例
                ReflectionUtils.setField(entry.getValue(), field, fieldInstance);
            }
        }
    }

    private static String getBeanName(Class<?> fieldClass) {
        if (fieldClass.isAnnotationPresent(Component.class)) {
            String componentName = fieldClass.getDeclaredAnnotation(Component.class).name();
            if (!"".equals(componentName)) {
                return componentName;
            }
        }
        return fieldClass.getName();
    }

    private static Set<Class<?>> getImplClasses(String packageName, Class<?> interfaceClass) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getSubTypesOf((Class<Object>) interfaceClass);
    }
}
