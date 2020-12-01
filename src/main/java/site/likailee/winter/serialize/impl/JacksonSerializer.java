/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.serialize.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import site.likailee.winter.serialize.Serializer;

import java.io.IOException;

/**
 * Jackson 序列化
 *
 * @author likailee.llk
 * @version JacksonSerializer.java 2020/11/26 Thu 4:19 PM likai
 */
public class JacksonSerializer implements Serializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(Object o) {
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        T obj = null;
        try {
            obj = objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
