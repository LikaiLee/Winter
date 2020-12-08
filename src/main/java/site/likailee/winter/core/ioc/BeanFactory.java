/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.aop.Aspect;
import site.likailee.winter.annotation.ioc.Component;
import site.likailee.winter.annotation.springmvc.RestController;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.common.util.WinterUtils;
import site.likailee.winter.core.factory.ClassFactory;
import site.likailee.winter.exception.DoGetBeanException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version BeanFactory.java 2020/12/01 Tue 2:29 PM likai
 */
@Slf4j
public class BeanFactory {
    /**
     * 存放所有 Bean 实例
     */
    public static final Map<String, Object> BEANS = new ConcurrentHashMap<>(128);
    /**
     * 缓存类和对应的 Bean 实例
     */
    private static Map<String, String[]> singletonBeanNamesTypeMap = new ConcurrentHashMap<>(128);

    /**
     * 实例化 Component，RestController
     */
    public static void loadBeans() {
        ClassFactory.CLASSES.get(Component.class).forEach(componentCls -> {
            String beanName = WinterUtils.getBeanName(componentCls);
            Object bean = ReflectionUtils.newInstance(componentCls);
            BEANS.put(beanName, bean);
        });
        ClassFactory.CLASSES.get(RestController.class).forEach(ctrlClass -> {
            Object bean = ReflectionUtils.newInstance(ctrlClass);
            BEANS.put(ctrlClass.getName(), bean);
        });
        log.info("Load [{}] beans: {}", BEANS.size(), BEANS.keySet());
    }

    /**
     * 根据类名获取同类或子类
     *
     * @param className
     * @return
     */
    public static Set<Object> getBeansForClassName(String className) {
        String[] beanNames = singletonBeanNamesTypeMap.getOrDefault(className, null);
        Set<Object> beansOfClassName = new HashSet<>();
        if (beanNames == null) {
            List<String> list = new ArrayList<>();
            for (Object bean : BEANS.values()) {
                // bean 类名 与 className 相同
                if (className.equals(bean.getClass().getName())) {
                    list.add(bean.getClass().getName());
                }
            }
            beanNames = list.toArray(new String[0]);
        }
        for (String beanName : beanNames) {
            beansOfClassName.add(BEANS.get(beanName));
        }
        return beansOfClassName;
    }

    /**
     * 根据类的类型获取 Bean 实例
     *
     * @param type class type
     * @param <T>
     * @return type 的同类或子类
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> res = new HashMap<>();
        for (String beanName : beanNames) {
            Object bean = BEANS.get(beanName);
            if (!type.isInstance(bean)) {
                throw new DoGetBeanException("bean " + bean.getClass().getName() + " can not cast to specified type " + type.getName());
            }
            res.put(beanName, type.cast(bean));
        }
        return res;
    }

    /**
     * 获取 type 的子类或同类
     *
     * @param type
     * @param <T>
     * @return
     */
    private static <T> String[] getBeanNamesForType(Class<T> type) {
        String className = type.getName();
        String[] beanNames = singletonBeanNamesTypeMap.getOrDefault(className, null);
        if (beanNames == null) {
            List<String> beanNameList = new ArrayList<>();
            for (Map.Entry<String, Object> beanEntry : BEANS.entrySet()) {
                // bean 是 type 的同类
                if (type.isAssignableFrom(beanEntry.getValue().getClass())) {
                    beanNameList.add(beanEntry.getKey());
                }
            }
            beanNames = beanNameList.toArray(new String[0]);
            singletonBeanNamesTypeMap.put(className, beanNames);
        }
        return beanNames;
    }

}
