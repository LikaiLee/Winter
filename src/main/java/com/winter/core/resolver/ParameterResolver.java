/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.resolver;

import com.winter.core.entity.MethodDetail;

import java.lang.reflect.Parameter;

/**
 * @author likailee.llk
 * @version ParameterResolver.java 2020/11/30 Mon 3:54 PM likai
 */
public interface ParameterResolver {
    /**
     * 解析方法的参数
     *
     * @param methodDetail
     * @param parameter
     * @return
     */
    Object resolve(MethodDetail methodDetail, Parameter parameter);
}
