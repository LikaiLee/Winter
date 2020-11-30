/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author likailee.llk
 * @version ReflectionUtils.java 2020/11/27 Fri 5:10 PM likai
 */
@Slf4j
public class ReflectionUtils {
    public static Object executeMethod(Method method, Object... args) {
        Object result = null;
        try {
            // 生成 方法对应的类
            Object targetObject = method.getDeclaringClass().newInstance();
            // 调用方法
            result = method.invoke(targetObject, args);
            // log.info("invoke target method [{}] successfully, result: {}", method.getName(), result);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            log.error("error occurs while invoke method [{}]", method.getName(), e);
        }
        return result;
    }
}
