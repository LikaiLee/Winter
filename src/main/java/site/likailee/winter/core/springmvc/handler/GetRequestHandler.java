/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.handler;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.common.util.WinterUtils;
import site.likailee.winter.core.springmvc.entity.MethodDetail;
import site.likailee.winter.core.ioc.BeanFactory;
import site.likailee.winter.core.springmvc.factory.FullHttpResponseFactory;
import site.likailee.winter.core.springmvc.factory.ParameterResolverFactory;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.springmvc.factory.RouteMethodMapper;
import site.likailee.winter.exception.ResponseException;

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
    public FullHttpResponse handle(FullHttpRequest fullHttpRequest) throws Exception {
        String requestUri = fullHttpRequest.uri();
        // 根据 URL 获取对应方法，解析地址栏参数
        String requestPath = UrlUtils.getRequestPath(requestUri);
        MethodDetail methodDetail = RouteMethodMapper.getMethodDetail(requestPath, HttpMethod.GET);
        // 没有可以匹配该 URL 的方法
        if (methodDetail == null) {
            throw new ResponseException(String.format("GET request on URL [%s] mapping failed!", requestPath), HttpResponseStatus.NOT_FOUND);
        }
        // 解析 URL 中的参数
        Map<String, String> queryParams = UrlUtils.getQueryParams(requestUri);
        methodDetail.setQueryParamMap(queryParams);
        Method dispatchMethod = methodDetail.getMethod();
        log.info("GET request on method [{}#{}] with uri {}", dispatchMethod.getDeclaringClass().getSimpleName(), dispatchMethod.getName(), requestUri);
        // 解析 Body 参数
        ParameterResolverFactory.resolveBodyParams(fullHttpRequest, methodDetail);
        // 获取方法中的参数
        Object[] methodArgs = ParameterResolverFactory.resolveMethodArgs(dispatchMethod, methodDetail);
        // 调用 URL 对应的方法
        String beanName = WinterUtils.getBeanName(methodDetail.getMethod().getDeclaringClass());
        Object bean = BeanFactory.BEANS.get(beanName);
        return FullHttpResponseFactory.getSuccessResponse(bean, dispatchMethod, methodArgs);
    }
}
