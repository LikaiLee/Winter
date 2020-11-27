/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.handler;

import com.winter.core.annotation.RequestParam;
import com.winter.core.common.util.ObjectUtils;
import com.winter.core.common.util.UrlUtils;
import com.winter.core.common.util.ReflectionUtils;
import com.winter.core.router.Router;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 处理 GET 请求
 *
 * @author likailee.llk
 * @version GetRequestHandler.java 2020/11/26 Thu 4:28 PM likai
 */
@Slf4j
public class GetRequestHandler implements RequestHandler {
    @Override
    public Object handle(FullHttpRequest fullHttpRequest) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(fullHttpRequest.uri(), Charsets.toCharset(CharEncoding.UTF_8));
        // 根据 URL 从路由表中获取对应方法
        String url = queryStringDecoder.path();
        Method dispatchMethod = Router.getMappings.get(url);
        // 没有可以匹配该 URL 的方法
        if (dispatchMethod == null) {
            log.error("URL mapping failed!");
            return null;
        }
        List<Object> dispatchMethodArgs = new ArrayList<>();
        // 解析 URL 中的参数
        Map<String, List<String>> uriAttributes = queryStringDecoder.parameters();
        Map<String, String> queryParams = UrlUtils.getQueryParams(uriAttributes);
        // 获取方法中的参数
        Parameter[] methodArgs = dispatchMethod.getParameters();
        log.info("request url: {}, query params: {}", url, queryParams);
        log.info("method required params: {}", Arrays.toString(methodArgs));
        // 只解析方法中带有 @RequestParam 的参数
        for (Parameter arg : methodArgs) {
            RequestParam requestParam = arg.getAnnotation(RequestParam.class);
            // TODO: @RequestParam 不存在时，或存在但值为空时，直接用方法参数名取值
            if (requestParam == null) {
                continue;
            }
            // 从 URL 中获取 方法所需要的参数
            String requestParameter = requestParam.value();
            String requestParameterVal = queryParams.get(requestParameter);
            if (requestParameterVal == null) {
                throw new IllegalArgumentException("The specified parameter " + requestParameter + " can not be null!");
            }
            // log.info("param: {}, value: {}", requestParam, requestParameterVal);
            // 将参数转为 方法需要的类型
            Class<?> paramType = arg.getType();
            // TODO: 参数类型可能有基本类型，List, Map，Set 等
            Object paramVal = ObjectUtils.convertTo(requestParameterVal, paramType);
            dispatchMethodArgs.add(paramVal);
        }
        log.info("parsed method args: {}", dispatchMethodArgs);
        // 调用 URL 对应的方法
        return ReflectionUtils.executeMethod(dispatchMethod, dispatchMethodArgs.toArray());
    }
}
