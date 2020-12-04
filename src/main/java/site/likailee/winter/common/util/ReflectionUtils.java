/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.common.util;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import site.likailee.winter.annotation.ioc.Component;
import site.likailee.winter.annotation.springmvc.RestController;
import site.likailee.winter.core.ioc.BeanFactory;
import site.likailee.winter.exception.CanNotInvokeTargetMethodException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author likailee.llk
 * @version ReflectionUtils.java 2020/11/27 Fri 5:10 PM likai
 */
@Slf4j
public class ReflectionUtils {
    /**
     * 执行方法
     *
     * @param method
     * @param args
     * @return
     */
    public static Object executeMethod(Object targetObject, Method method, Object... args) {
        Object result = null;
        try {
            // 调用方法
            result = method.invoke(targetObject, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("error occurs while invoke method [{}]", method.getName(), e);
            throw new CanNotInvokeTargetMethodException(e.toString());
        }
        return result;
    }

    /**
     * 创建类实例
     *
     * @param clazz
     * @return
     */
    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("new instance [{}] failed", clazz, e);
        }
        return null;
    }

    /**
     * 设置 Bean 属性
     *
     * @param bean
     * @param field
     * @param fieldInstance
     */
    public static void setField(Object bean, Field field, Object fieldInstance) {
        field.setAccessible(true);
        try {
            field.set(bean, fieldInstance);
        } catch (IllegalAccessException e) {
            log.error("set bean [{}] field [{}] failed", bean, field, e);
        }
    }

    /**
     * 获取接口的实现类
     *
     * @param packageName
     * @param interfaceClass
     * @return
     */
    public static <T> Set<Class<? extends T>> getImplClasses(String packageName, Class<T> interfaceClass) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getSubTypesOf(interfaceClass);
    }
}
