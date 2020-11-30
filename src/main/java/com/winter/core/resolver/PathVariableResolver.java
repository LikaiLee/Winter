/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.resolver;

import com.winter.core.annotation.PathVariable;
import com.winter.core.common.util.ObjectUtils;
import com.winter.core.entity.MethodDetail;

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
            throw new IllegalArgumentException("The specified path variable [" + pathParam + "] can not be null!");
        }
        return ObjectUtils.convertTo(pathParamVal, arg.getType());
    }
}
