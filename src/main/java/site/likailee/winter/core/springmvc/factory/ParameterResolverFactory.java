/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.factory;

import site.likailee.winter.annotation.springmvc.PathVariable;
import site.likailee.winter.annotation.springmvc.RequestBody;
import site.likailee.winter.annotation.springmvc.RequestParam;
import site.likailee.winter.core.entity.MethodDetail;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.resolver.PathVariableResolver;
import site.likailee.winter.core.resolver.RequestBodyResolver;
import site.likailee.winter.core.resolver.RequestParamResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author likailee.llk
 * @version ParameterResolverFactory.java 2020/11/30 Mon 3:54 PM likai
 */
@Slf4j
public class ParameterResolverFactory {
    private static final RequestParamResolver REQUEST_PARAM_RESOLVER = new RequestParamResolver();
    private static final RequestBodyResolver REQUEST_BODY_RESOLVER = new RequestBodyResolver();
    private static final PathVariableResolver PATH_VARIABLE_RESOLVER = new PathVariableResolver();

    /**
     * 从请求中获取方法参数
     *
     * @param method
     * @param methodDetail
     * @return
     */
    public static Object[] getParameters(Method method, MethodDetail methodDetail) {
        Parameter[] methodArgs = method.getParameters();
        List<Object> args = new ArrayList<>();
        for (Parameter arg : methodArgs) {
            Object param = getParameterByAnnotation(methodDetail, arg);
            args.add(param);
        }
        return args.toArray();
    }

    /**
     * 将参数值转为方法参数所需要的类型
     *
     * @param methodDetail 方法元数据
     * @param arg          方法参数
     * @return
     */
    private static Object getParameterByAnnotation(MethodDetail methodDetail, Parameter arg) {
        if (arg.isAnnotationPresent(RequestParam.class)) {
            return REQUEST_PARAM_RESOLVER.resolve(methodDetail, arg);
        }
        if (arg.isAnnotationPresent(RequestBody.class)) {
            return REQUEST_BODY_RESOLVER.resolve(methodDetail, arg);
        }
        if (arg.isAnnotationPresent(PathVariable.class)) {
            return PATH_VARIABLE_RESOLVER.resolve(methodDetail, arg);
        }

        // TODO: 有注解有值，直接用注解值，注解无值或无注解，直接用方法参数名取值
        // 没有注解
        throw new IllegalArgumentException("Annotation for parameter [" + arg.getName() + "] is required for method " + methodDetail.getMethod().getName());
    }
}
