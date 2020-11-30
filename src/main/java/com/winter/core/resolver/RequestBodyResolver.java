/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winter.core.entity.MethodDetail;

import java.lang.reflect.Parameter;

/**
 * @author likailee.llk
 * @version RequestBodyResolver.java 2020/11/30 Mon 4:23 PM likai
 */
public class RequestBodyResolver implements ParameterResolver {
    private final ObjectMapper objectMapper;

    public RequestBodyResolver() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Object resolve(MethodDetail methodDetail, Parameter arg) {
        try {
            // 将请求体转为 Java 对象
            return objectMapper.readValue(methodDetail.getRequestBodyJsonStr(), arg.getType());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("json parse error");
    }
}
