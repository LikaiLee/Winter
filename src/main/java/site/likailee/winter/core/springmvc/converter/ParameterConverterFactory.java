/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author likailee.llk
 * @version ParameterConverterFactory.java 2020/12/16 Wed 8:23 PM likai
 */
public class ParameterConverterFactory {
    private static final Map<Class<?>, Converter> TYPE_CONVERTER = new HashMap<>();

    static {
        // byte
        TYPE_CONVERTER.put(byte.class, new ByteConverter());
        TYPE_CONVERTER.put(Byte.class, new ByteConverter());
        // short
        TYPE_CONVERTER.put(short.class, new ShortConverter());
        TYPE_CONVERTER.put(Short.class, new ShortConverter());
        // char
        TYPE_CONVERTER.put(char.class, new CharacterConverter());
        TYPE_CONVERTER.put(Character.class, new CharacterConverter());
        // int
        TYPE_CONVERTER.put(int.class, new IntegerConverter());
        TYPE_CONVERTER.put(Integer.class, new IntegerConverter());
        // long
        TYPE_CONVERTER.put(long.class, new LongConverter());
        TYPE_CONVERTER.put(Long.class, new LongConverter());
        // float
        TYPE_CONVERTER.put(float.class, new FloatConverter());
        TYPE_CONVERTER.put(Float.class, new FloatConverter());
        // double
        TYPE_CONVERTER.put(double.class, new DoubleConverter());
        TYPE_CONVERTER.put(Double.class, new DoubleConverter());
        // boolean
        TYPE_CONVERTER.put(boolean.class, new BooleanConverter());
        TYPE_CONVERTER.put(Boolean.class, new BooleanConverter());
    }

    public static Converter getConverter(Class<?> type) {
        return TYPE_CONVERTER.get(type);
    }
}
