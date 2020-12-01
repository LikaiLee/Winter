/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.handler;

import site.likailee.winter.core.ApplicationContext;
import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.core.entity.MethodDetail;
import site.likailee.winter.core.factory.MethodDetailFactory;
import site.likailee.winter.core.factory.ParameterResolverFactory;
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
        MethodDetail methodDetail = MethodDetailFactory.getMethodDetail(requestPath, HttpMethod.GET);
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
