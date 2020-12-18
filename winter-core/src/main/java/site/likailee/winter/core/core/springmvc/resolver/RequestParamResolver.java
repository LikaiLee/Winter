/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.resolver;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.annotation.springmvc.RequestParam;
import site.likailee.winter.core.core.factory.ConverterFactory;
import site.likailee.winter.core.core.springmvc.entity.MethodDetail;
import site.likailee.winter.core.exception.ResponseException;

import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Objects;

/**
 * @author likailee.llk
 * @version RequestParamResolver.java 2020/11/30 Mon 4:04 PM likai
 */
@Slf4j
public class RequestParamResolver implements ParameterResolver {
    @Override
    public Object resolve(MethodDetail methodDetail, Parameter arg) {
        RequestParam requestParam = arg.getDeclaredAnnotation(RequestParam.class);
        // 从 URL 中获取 方法所需要的参数
        String requestParameter = requestParam.value();
        String requestParameterVal = methodDetail.getQueryParamMap().get(requestParameter);
        // @RequestParam 没有值
        if (requestParameter.isEmpty()) {
            // 类型为 Map，则存放所有的 query string
            if (Map.class.isAssignableFrom(arg.getType())) {
                return methodDetail.getQueryParamMap();
            }
        }
        // query string 没有特定参数
        if (Objects.isNull(requestParameterVal)) {
            // required && 无默认值
            if (requestParam.required() && requestParam.defaultValue().isEmpty()) {
                String errMsg = String.format("The specified parameter [%s] can not be null!", requestParameter);
                throw new ResponseException(errMsg, HttpResponseStatus.BAD_REQUEST);
            }
            requestParameterVal = requestParam.defaultValue();
        }
        // 将参数转为 方法需要的类型
        return ConverterFactory.convertTo(requestParameterVal, arg.getType(), arg.getParameterizedType());
    }
}
