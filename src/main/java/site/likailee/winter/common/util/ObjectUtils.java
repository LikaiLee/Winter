/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author likailee.llk
 * @version ObjectUtils.java 2020/11/27 Fri 5:06 PM likai
 */
@Slf4j
public class ObjectUtils {
    /**
     * 将字符串转为特定类型
     *
     * @param str        需要转换的字符串
     * @param targetType 目标类型
     * @return 转换后的对象
     */
    public static Object convertTo(String str, Class<?> targetType) {
        try {
            Constructor<?> constructor = targetType.getConstructor(String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(str);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            log.error("can not convert [{}] to target method type [{}]", str, targetType, e);
        }
        throw new IllegalArgumentException("can not convert [" + str + "] to target method type [" + targetType + "]");
    }
}
