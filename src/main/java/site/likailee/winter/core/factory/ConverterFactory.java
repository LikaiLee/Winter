/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.factory;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.springmvc.converter.Converter;
import site.likailee.winter.core.springmvc.converter.ParameterConverterFactory;
import site.likailee.winter.exception.ResponseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author likailee.llk
 * @version ConverterFactory.java 2020/11/27 Fri 5:06 PM likai
 */
@Slf4j
public class ConverterFactory {
    /**
     * 将字符串转为特定类型
     *
     * @param str        需要转换的字符串
     * @param targetType 目标类型
     * @return 转换后的对象
     */
    public static Object convertTo(String str, Class<?> targetType) {
        if (targetType == String.class) {
            return str;
        }
        Converter converter = ParameterConverterFactory.getConverter(targetType);
        if (!Objects.isNull(converter)) {
            return converter.convert(str);
        }
        try {
            Constructor<?> constructor = targetType.getConstructor(String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(str);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            String errMsg = String.format("can not convert [%s] to target method type [%s]", str, targetType);
            throw new ResponseException(errMsg, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
