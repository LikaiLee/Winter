/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.ioc.Qualifier;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.common.util.WinterUtils;
import site.likailee.winter.core.aop.BeanPostProcessor;
import site.likailee.winter.core.aop.Interceptor;
import site.likailee.winter.core.aop.InterceptorFactory;
import site.likailee.winter.core.aop.cglib.CglibAopProxyBeanPostProcessor;
import site.likailee.winter.core.aop.jdk.JdkAopProxyBeanPostProcessor;
import site.likailee.winter.exception.InterfaceNotImplementedException;
import site.likailee.winter.exception.NoUniqueBeanDefinitionException;

import java.lang.reflect.Field;
import java.util.List;
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
            // log.info("prepare bean field: {}.{}", beanInstance.getClass().getSimpleName(), field.getName());
            // 获取属性对应的类
            Class<?> fieldClass = field.getType();
            String beanFieldName = WinterUtils.getBeanName(fieldClass);
            Object beanFieldInstance = null;
            boolean newSingleton = true;
            if (SINGLETON_OBJECTS.containsKey(beanFieldName)) {
                beanFieldInstance = SINGLETON_OBJECTS.get(beanFieldName);
                newSingleton = false;
            }
            // 二级缓存不存在，需要创建
            if (beanFieldInstance == null) {
                // 如果是接口则获取其实现类
                if (fieldClass.isInterface()) {
                    @SuppressWarnings("unchecked")
                    Set<Class<?>> implClasses = ReflectionUtils.getImplClasses(packageName, (Class<Object>) fieldClass);
                    if (implClasses.size() == 0) {
                        throw new InterfaceNotImplementedException("interface " + fieldClass.getName() + " does not have implemented");
                    }
                    // 只有一个实现类
                    if (implClasses.size() == 1) {
                        Class<?> implClass = implClasses.iterator().next();
                        beanFieldName = WinterUtils.getBeanName(implClass);
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
                }
                SINGLETON_OBJECTS.put(beanFieldName, beanFieldInstance);
            }
            if (newSingleton) {
                prepareBean(beanFieldInstance, packageName);
            }

            // 进行 AOP 代理
            // TODO: 目前只能对属性对象中的方法进行代理
            BeanPostProcessor beanPostProcessor;
            // 获取所有拦截器
            List<Interceptor> interceptors = InterceptorFactory.getInterceptors();
            // 判断代理模式
            if (fieldClass.isInterface()) {
                beanPostProcessor = new JdkAopProxyBeanPostProcessor(interceptors);
            } else {
                beanPostProcessor = new CglibAopProxyBeanPostProcessor(interceptors);
            }
            beanFieldInstance = beanPostProcessor.postProcessAfterInitialization(beanFieldInstance);
            // 设置属性对应的实例
            ReflectionUtils.setField(beanInstance, field, beanFieldInstance);
        }
    }

}
