/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.factory.ConverterFactory;
import site.likailee.winter.exception.ResponseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author likailee.llk
 * @version CollectionConverter.java 2020/12/17 Thu 3:04 PM likai
 */
@Slf4j
public class CollectionConverter {
    /**
     * 生成方法需要的容器数据
     *
     * @param str
     * @param targetType
     * @param genericType
     * @return
     */
    public static Object convert(String str, Class<?> targetType, Type genericType) {
        Constructor<?> constructor = null;
        try {
            // List
            if (List.class.isAssignableFrom(targetType)) {
                if (targetType.isInterface()) {
                    constructor = ArrayList.class.getConstructor(Collection.class);
                } else {
                    constructor = targetType.getConstructor(Collection.class);
                }
            }
            // Set
            if (Set.class.isAssignableFrom(targetType)) {
                if (targetType.isInterface()) {
                    constructor = HashSet.class.getConstructor(Collection.class);
                } else {
                    constructor = targetType.getConstructor(Collection.class);
                }
            }
            if (Objects.isNull(constructor)) {
                return null;
            }
            constructor.setAccessible(true);
            // 获取 List 泛型
            List<Object> data = getCollectionData(str, genericType);
            return constructor.newInstance(data);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            String errMsg = String.format("fail to convert [%s] to target type [%s] with generic type [%s]", str, targetType.getName(), genericType.getTypeName());
            throw new ResponseException(errMsg, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 将字符串内的数据转为容器需要的数据列表
     *
     * @param str
     * @param genericType
     * @return
     */
    private static List<Object> getCollectionData(String str, Type genericType) {
        // 获取 List 泛型
        Class<?> actualType = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        // 将参数转为 List 需要的泛型
        String[] rawParams = str.split(",");
        List<Object> data = new ArrayList<>();
        for (String p : rawParams) {
            Object target = ConverterFactory.convertTo(p, actualType, actualType);
            data.add(target);
        }
        return data;
    }
}
