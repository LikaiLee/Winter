/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import site.likailee.winter.core.springmvc.entity.MethodDetail;
import site.likailee.winter.exception.ResponseException;

import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * @author likailee.llk
 * @version RequestBodyResolver.java 2020/11/30 Mon 4:23 PM likai
 */
public class RequestBodyResolver implements ParameterResolver {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Object resolve(MethodDetail methodDetail, Parameter arg) {
        try {
            if (Objects.isNull(methodDetail.getRequestBodyJsonStr())) {
                throw new ResponseException(String.format("can not get parameter [%s]", arg.getName()), HttpResponseStatus.BAD_REQUEST);
            }
            // 将请求体转为 Java 对象
            return OBJECT_MAPPER.readValue(methodDetail.getRequestBodyJsonStr(), arg.getType());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("json parse error");
    }
}
