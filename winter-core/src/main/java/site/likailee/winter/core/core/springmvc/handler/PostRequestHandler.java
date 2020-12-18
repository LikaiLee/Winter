/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.common.util.UrlUtils;
import site.likailee.winter.core.common.util.WinterUtils;
import site.likailee.winter.core.core.ioc.BeanFactory;
import site.likailee.winter.core.core.springmvc.entity.RouteDefinition;
import site.likailee.winter.core.core.springmvc.enums.RequestMethod;
import site.likailee.winter.core.core.springmvc.factory.FullHttpResponseFactory;
import site.likailee.winter.core.core.springmvc.factory.ParameterResolverFactory;
import site.likailee.winter.core.core.springmvc.factory.RouteMethodMapper;
import site.likailee.winter.core.exception.ResponseException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * 处理 POST 请求
 *
 * @author likailee.llk
 * @version PostRequestHandler.java 2020/11/26 Thu 4:28 PM likai
 */
public class PostRequestHandler implements RequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostRequestHandler.class);

    @Override
    public FullHttpResponse handle(FullHttpRequest fullHttpRequest) throws Exception {
        String requestUri = fullHttpRequest.uri();
        // 根据 URL 获取对应方法，解析地址栏参数
        String requestPath = UrlUtils.getRequestPath(requestUri);
        RouteDefinition routeDefinition = RouteMethodMapper.getMethodDefinition(requestPath, RequestMethod.POST);
        // 解析 URL 中的参数
        Map<String, String> queryParams = UrlUtils.getQueryParams(requestUri);
        routeDefinition.setQueryParamMap(queryParams);
        Method dispatchMethod = routeDefinition.getMethod();
        LOGGER.info("Post request on method [{}#{}] with uri {}", dispatchMethod.getDeclaringClass().getSimpleName(), dispatchMethod.getName(), requestUri);
        // 解析 Body 参数
        ParameterResolverFactory.resolveBodyParams(fullHttpRequest, routeDefinition);
        // 获取方法中的参数
        Object[] methodArgs = ParameterResolverFactory.resolveMethodArgs(dispatchMethod, routeDefinition);
        // 调用 URL 对应的方法
        String beanName = WinterUtils.getBeanName(routeDefinition.getMethod().getDeclaringClass());
        Object bean = BeanFactory.BEANS.get(beanName);
        return FullHttpResponseFactory.getSuccessResponse(bean, dispatchMethod, methodArgs);
    }

}
