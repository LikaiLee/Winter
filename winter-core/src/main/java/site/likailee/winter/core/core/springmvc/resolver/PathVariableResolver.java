/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.resolver;

import io.netty.handler.codec.http.HttpResponseStatus;
import site.likailee.winter.core.annotation.springmvc.PathVariable;
import site.likailee.winter.core.core.factory.ConverterFactory;
import site.likailee.winter.core.core.springmvc.entity.MethodDetail;
import site.likailee.winter.core.exception.ResponseException;

import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * @author likailee.llk
 * @version PathVariableResolver.java 2020/11/30 Mon 8:12 PM likai
 */
public class PathVariableResolver implements ParameterResolver {
    @Override
    public Object resolve(MethodDetail methodDetail, Parameter arg) {
        PathVariable pathVariable = arg.getAnnotation(PathVariable.class);
        String pathParam = pathVariable.value();
        // 获取地址参数
        String pathParamVal = methodDetail.getPathParamMap().get(pathParam);
        if (Objects.isNull(pathParamVal)) {
            String errMsg = String.format("The specified path variable [%s] can not be null!", pathParam);
            throw new ResponseException(errMsg, HttpResponseStatus.BAD_REQUEST);
        }
        return ConverterFactory.convertTo(pathParamVal, arg.getType(), arg.getParameterizedType());
    }
}
