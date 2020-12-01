/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.serialize;

/**
 * @author likailee.llk
 * @version Serializer.java 2020/11/26 Thu 4:17 PM likai
 */
public interface Serializer {
    /**
     * 对象转字节数组
     *
     * @param o
     * @return
     */
    byte[] serialize(Object o);

    /**
     * 字节数组转对象
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
