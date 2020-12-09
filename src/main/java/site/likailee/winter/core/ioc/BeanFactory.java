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
}
