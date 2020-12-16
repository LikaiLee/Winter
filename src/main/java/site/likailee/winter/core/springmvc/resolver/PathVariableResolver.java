/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.resolver;

import io.netty.handler.codec.http.HttpResponseStatus;
import site.likailee.winter.annotation.springmvc.PathVariable;
import site.likailee.winter.common.util.ObjectUtils;
import site.likailee.winter.core.springmvc.entity.MethodDetail;
import site.likailee.winter.exception.ResponseException;

import java.lang.reflect.Parameter;

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
        if (pathParamVal == null) {
            String errMsg = String.format("The specified path variable [%s] can not be null!", pathParam);
            throw new ResponseException(errMsg, HttpResponseStatus.BAD_REQUEST);
        }
        return ObjectUtils.convertTo(pathParamVal, arg.getType());
    }
}
