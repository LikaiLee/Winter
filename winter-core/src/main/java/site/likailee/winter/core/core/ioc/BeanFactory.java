/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.ioc.Component;
import site.likailee.winter.core.annotation.springmvc.RestController;
import site.likailee.winter.core.common.util.ReflectionUtils;
import site.likailee.winter.core.common.util.WinterUtils;
import site.likailee.winter.core.core.aop.factory.AopProxyPostProcessorFactory;
import site.likailee.winter.core.core.aop.processor.BeanPostProcessor;
import site.likailee.winter.core.core.config.ConfigurationFactory;
import site.likailee.winter.core.core.config.ConfigurationManager;
import site.likailee.winter.core.core.factory.ClassFactory;
import site.likailee.winter.core.exception.DoGetBeanException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version BeanFactory.java 2020/12/01 Tue 2:29 PM likai
 */
public class BeanFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanFactory.class);
    /**
     * 存放所有 Bean 实例
     */
    public static final Map<String, Object> BEANS = new ConcurrentHashMap<>(128);

    /**
     * 实例化 Component，RestController
     */
    public static void loadBeans() {
        // @Component
        ClassFactory.getComponents().forEach(componentCls -> {
            String beanName = WinterUtils.getBeanName(componentCls);
            Object bean = ReflectionUtils.newInstance(componentCls);
            BEANS.put(beanName, bean);
        });
        // @RestController
        ClassFactory.getRestControllers().forEach(ctrlClass -> {
            String beanName = WinterUtils.getBeanName(ctrlClass);
            Object bean = ReflectionUtils.newInstance(ctrlClass);
            BEANS.put(beanName, bean);
        });
        // ConfigurationManager
        BEANS.put(ConfigurationManager.class.getName(), new ConfigurationManager(ConfigurationFactory.getConfig()));
        LOGGER.info("Load [{}] beans: {}", BEANS.size(), BEANS.keySet());
    }

    /**
     * 根据类的类型获取 Bean
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBeanForType(Class<T> type) {
        List<Object> beans = new ArrayList<>();
        BEANS.values().forEach(bean -> {
            if (type.isAssignableFrom(bean.getClass())) {
                beans.add(bean);
            }
        });
        if (beans.isEmpty()) {
            throw new DoGetBeanException("can not get bean of type " + type.getName());
        }
        return type.cast(beans.get(0));
    }

    /**
     * 为 {@code @RestController 和 @Component} 进行 AOP 代理
     */
    public static void applyBeanPostProcessors() {
        BEANS.replaceAll((beanName, beanInstance) -> {
            BeanPostProcessor beanPostProcessor = AopProxyPostProcessorFactory.get(beanInstance.getClass());
            return beanPostProcessor.postProcessAfterInitialization(beanInstance);
        });
    }
}
