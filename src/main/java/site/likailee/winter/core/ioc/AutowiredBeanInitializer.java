/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import site.likailee.winter.annotation.config.Value;
import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.ioc.Qualifier;
import site.likailee.winter.common.util.ObjectUtils;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.common.util.WinterUtils;
import site.likailee.winter.core.aop.factory.AopProxyPostProcessorFactory;
import site.likailee.winter.core.aop.processor.BeanPostProcessor;
import site.likailee.winter.core.config.ConfigurationManager;
import site.likailee.winter.exception.InterfaceNotImplementedException;
import site.likailee.winter.exception.NoUniqueBeanDefinitionException;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version AutowiredBeanProcessor.java 2020/12/11 Fri 12:52 PM likai
 */
public class AutowiredBeanInitializer {
    private final String[] packageNames;

    /**
     * 二级缓存，存放初始化后的 Bean
     */
    private static final Map<String, Object> SINGLETON_OBJECTS = new ConcurrentHashMap<>(64);

    public AutowiredBeanInitializer(String[] packageNames) {
        this.packageNames = packageNames;
    }

    /**
     * 初始化 Bean，进行依赖注入
     *
     * @param beanInstance 需要初始化的 Bean
     */
    public void initialize(Object beanInstance) {
        // beanInstance 已经实例化，但还未注入依赖
        Field[] fields = beanInstance.getClass().getDeclaredFields();
        // 遍历所有属性，为 @Autowired / @Value 的属性注入依赖
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object beanFieldInstance = withAutowiredAnnotation(beanInstance, field);
                // 设置属性对应的实例
                ReflectionUtils.setField(beanInstance, field, beanFieldInstance);
            }
            if (field.isAnnotationPresent(Value.class)) {
                String configKey = field.getAnnotation(Value.class).value();
                Object targetObj = withValueAnnotation(field, configKey);
                ReflectionUtils.setField(beanInstance, field, targetObj);
            }
        }
    }

    /**
     * 获取 {@code @Value} 的属性值
     *
     * @param field
     * @param configKey
     * @return
     */
    private Object withValueAnnotation(Field field, String configKey) {
        ConfigurationManager configurationManager = BeanFactory.getBeanForType(ConfigurationManager.class);
        String value = configurationManager.getString(configKey);
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("can not get target configuration value for property " + configKey);
        }
        return ObjectUtils.convertTo(value, field.getType());
    }

    /**
     * 获取 {@code @Autowired} 的依赖
     *
     * @param beanInstance
     * @param field
     * @return
     */
    private Object withAutowiredAnnotation(Object beanInstance, Field field) {
        // 获取属性对应的类
        Class<?> fieldClass = field.getType();
        String beanFieldName = WinterUtils.getBeanName(fieldClass);
        // 尝试从二级缓存中获取 Bean
        Object beanFieldInstance = SINGLETON_OBJECTS.getOrDefault(beanFieldName, null);
        // 二级缓存不存在
        // 说明 Bean 还未初始化，需要初始化后放入二级缓存
        if (Objects.isNull(beanFieldInstance)) {
            // 如果是接口则获取其实现类
            if (fieldClass.isInterface()) {
                beanFieldName = getImplBeanName(field);
            }
            beanFieldInstance = BeanFactory.BEANS.get(beanFieldName);
            // BeanFactory 找不到 Bean
            if (beanFieldInstance == null) {
                throw new NoUniqueBeanDefinitionException("can not inject bean " + beanInstance.getClass().getSimpleName() + " field " + field.getName());
            }
            // 放入二级缓存
            SINGLETON_OBJECTS.put(beanFieldName, beanFieldInstance);
            // 初始化
            initialize(beanFieldInstance);
        }

        // 对依赖进行 AOP 代理
        // TODO: 目前只能对属性对象进行代理
        BeanPostProcessor beanPostProcessor = AopProxyPostProcessorFactory.get(fieldClass);
        beanFieldInstance = beanPostProcessor.postProcessAfterInitialization(beanFieldInstance);
        return beanFieldInstance;
    }

    /**
     * 当 {@code field} 为接口，尝试获取其实现类
     *
     * <p> @Autowired <br>
     * TestInterface interfaceImpl</p>
     *
     * @param field
     * @return
     */
    private String getImplBeanName(Field field) {
        String beanFieldName;
        // field 对应的类
        Class<?> fieldClass = field.getType();
        @SuppressWarnings("unchecked")
        Set<Class<?>> implClasses = ReflectionUtils.getImplClasses(packageNames, (Class<Object>) fieldClass);
        // 没有实现类
        if (implClasses.size() == 0) {
            throw new InterfaceNotImplementedException("interface " + fieldClass.getName() + " does not have implemented");
        }
        // 有一个实现类
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
        return beanFieldName;
    }
}
