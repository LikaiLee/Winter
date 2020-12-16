/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.resolver;

import io.netty.handler.codec.http.HttpResponseStatus;
import site.likailee.winter.annotation.springmvc.RequestParam;
import site.likailee.winter.common.util.ObjectUtils;
import site.likailee.winter.core.springmvc.entity.MethodDetail;
import site.likailee.winter.exception.ResponseException;

import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * @author likailee.llk
 * @version RequestParamResolver.java 2020/11/30 Mon 4:04 PM likai
 */
public class RequestParamResolver implements ParameterResolver {
    @Override
    public Object resolve(MethodDetail methodDetail, Parameter arg) {
        RequestParam requestParam = arg.getDeclaredAnnotation(RequestParam.class);
        // 从 URL 中获取 方法所需要的参数
        String requestParameter = requestParam.value();
        String requestParameterVal = methodDetail.getQueryParamMap().get(requestParameter);
        // 没有特定参数
        if (Objects.isNull(requestParameterVal)) {
            // required && 无默认值
            if (requestParam.required() && requestParam.defaultValue().isEmpty()) {
                String errMsg = String.format("The specified parameter [%s] can not be null!", requestParameter);
                throw new ResponseException(errMsg, HttpResponseStatus.BAD_REQUEST);
            }
            requestParameterVal = requestParam.defaultValue();
        }
        // 将参数转为 方法需要的类型
        // TODO: 参数类型可能有基本类型，List, Map，Set 等
        return ObjectUtils.convertTo(requestParameterVal, arg.getType());
    }
}
