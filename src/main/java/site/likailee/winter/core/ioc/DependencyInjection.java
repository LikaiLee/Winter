/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.ioc.Component;
import site.likailee.winter.annotation.ioc.Qualifier;
import site.likailee.winter.common.util.ObjectUtils;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.core.aop.JdkAopProxyBeanPostProcessor;
import site.likailee.winter.exception.InterfaceNotImplementedException;
import site.likailee.winter.exception.NoUniqueBeanDefinitionException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version DependencyInjection.java 2020/12/01 Tue 3:04 PM likai
 */
@Slf4j
public class DependencyInjection {
    /**
     * 二级缓存
     */
    private static final Map<String, Object> SINGLETON_OBJECTS = new ConcurrentHashMap<>();

    /**
     * 为 BEANS 内的 Bean 注入属性
     *
     * @param packageName
     */
    public static void inject(String packageName) {
        BeanFactory.BEANS.values().forEach(bean -> prepareBean(bean, packageName));
    }

    private static void prepareBean(Object beanInstance, String packageName) {
        // beanInstance 已经实例化，但还未注入依赖
        Field[] fields = beanInstance.getClass().getDeclaredFields();
        // 遍历所有属性，为 @Autowired 的属性注入依赖
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            // 获取属性对应的类
            Class<?> fieldClass = field.getType();
            String beanFieldName = getBeanName(fieldClass);
            Object beanFieldInstance = null;
            boolean newSingleton = true;
            if (SINGLETON_OBJECTS.containsKey(beanFieldName)) {
                beanFieldInstance = SINGLETON_OBJECTS.get(beanFieldName);
                newSingleton = false;
            }
            // 二级缓存不存在，需要创建
            if (beanFieldInstance == null) {
                // 如果是接口获取实现类
                if (fieldClass.isInterface()) {
                    @SuppressWarnings("unchecked")
                    Set<Class<?>> implClasses = ReflectionUtils.getImplClasses(packageName, (Class<Object>) fieldClass);
                    if (implClasses.size() == 0) {
                        throw new InterfaceNotImplementedException("interface " + fieldClass.getName() + " does not have implemented");
                    }
                    // 只有一个实现类
                    if (implClasses.size() == 1) {
                        Class<?> implClass = implClasses.iterator().next();
                        beanFieldName = getBeanName(implClass);
                    }
                    // 有多个实现类
                    else {
                        Qualifier qualifier = field.getDeclaredAnnotation(Qualifier.class);
                        if (qualifier == null) {
                            throw new NoUniqueBeanDefinitionException("interface " + fieldClass.getName() + " has more than one implementation");
                        }
                        beanFieldName = qualifier.value();
                    }
                }
                beanFieldInstance = BeanFactory.BEANS.get(beanFieldName);

                if (beanFieldInstance == null) {
                    throw new NoUniqueBeanDefinitionException("can not inject bean " + beanInstance.getClass().getSimpleName() + " field " + field.getName());
                } else {
                    SINGLETON_OBJECTS.put(beanFieldName, beanFieldInstance);
                }
            }
            if (newSingleton) {
                prepareBean(beanFieldInstance, packageName);
            }

            // 进行 AOP 代理
            BeanPostProcessor beanPostProcessor = new JdkAopProxyBeanPostProcessor(packageName);
            beanFieldInstance = beanPostProcessor.postProcessAfterInitialization(beanFieldInstance, beanFieldName);
            // 设置属性对应的实例
            log.info("about to set field [{}.{}] = {}", beanInstance.getClass().getSimpleName(), field.getName(), beanFieldInstance.getClass().getSimpleName());
            ReflectionUtils.setField(beanInstance, field, beanFieldInstance);
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

}
