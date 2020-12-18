/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import site.likailee.winter.core.core.springmvc.entity.RouteDefinition;
import site.likailee.winter.core.exception.ResponseException;

import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * @author likailee.llk
 * @version RequestBodyResolver.java 2020/11/30 Mon 4:23 PM likai
 */
public class RequestBodyResolver implements ParameterResolver {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Object resolve(RouteDefinition routeDefinition, Parameter arg) {
        try {
            if (Objects.isNull(routeDefinition.getRequestBodyJsonStr())) {
                String errMsg = String.format("can not get parameter [%s]", arg.getName());
                throw new ResponseException(errMsg, HttpResponseStatus.BAD_REQUEST);
            }
            // 将请求体转为 Java 对象
            return OBJECT_MAPPER.readValue(routeDefinition.getRequestBodyJsonStr(), arg.getType());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("json parse error");
    }
}
