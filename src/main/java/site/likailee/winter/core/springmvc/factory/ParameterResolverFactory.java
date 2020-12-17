/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.factory;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;
import site.likailee.winter.annotation.springmvc.PathVariable;
import site.likailee.winter.annotation.springmvc.RequestBody;
import site.likailee.winter.annotation.springmvc.RequestParam;
import site.likailee.winter.common.HttpConstants;
import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.core.springmvc.entity.MethodDetail;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.springmvc.resolver.PathVariableResolver;
import site.likailee.winter.core.springmvc.resolver.RequestBodyResolver;
import site.likailee.winter.core.springmvc.resolver.RequestParamResolver;
import site.likailee.winter.exception.ResponseException;

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
    public static Object[] resolveMethodArgs(Method method, MethodDetail methodDetail) {
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

        String errMsg = String.format("Annotation for parameter [%s] is required for method [%s]", arg.getName(), methodDetail.getMethod().getName());
        throw new ResponseException(errMsg, HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 获取 request body 中的参数
     *
     * @param fullHttpRequest
     * @param methodDetail
     * @return
     */
    public static void resolveBodyParams(FullHttpRequest fullHttpRequest, MethodDetail methodDetail) {
        String contentType = UrlUtils.getContentType(fullHttpRequest);
        if (HttpConstants.APPLICATION_JSON.equals(contentType)) {
            // 解析 request body 中的数据
            String jsonStr = fullHttpRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            methodDetail.setRequestBodyJsonStr(jsonStr);
        }
    }
}
