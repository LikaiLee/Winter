/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.handler;

import com.winter.core.ApplicationContext;
import com.winter.core.common.util.UrlUtils;
import com.winter.core.common.util.ReflectionUtils;
import com.winter.core.entity.MethodDetail;
import com.winter.core.resolver.ParameterResolverFactory;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
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
    public Object handle(FullHttpRequest fullHttpRequest) throws Exception {
        String requestUri = fullHttpRequest.uri();
        // 根据 URL 获取对应方法
        String requestPath = UrlUtils.getRequestPath(requestUri);
        ApplicationContext context = ApplicationContext.getApplicationContext();
        MethodDetail methodDetail = context.getMethodDetail(requestPath, HttpMethod.GET);
        // 没有可以匹配该 URL 的方法
        if (methodDetail == null) {
            log.error("Get request on URL [{}] mapping failed!", requestPath);
            throw new NoSuchMethodException("Get request on URL [" + requestPath + "] mapping failed!");
        }
        // 解析 URL 中的参数
        Map<String, String> queryParams = UrlUtils.getQueryParams(requestUri);
        methodDetail.setQueryParamMap(queryParams);
        Method dispatchMethod = methodDetail.getMethod();
        log.info("GET request on method [{}] with uri {}, methodDetail: {}", dispatchMethod.getName(), requestUri, methodDetail);
        // 获取方法中的参数
        Object[] methodArgs = ParameterResolverFactory.getParameters(dispatchMethod, methodDetail);
        // 调用 URL 对应的方法
        return ReflectionUtils.executeMethod(dispatchMethod, methodArgs);
    }
}
