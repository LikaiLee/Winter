/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.handler;

import site.likailee.winter.common.HttpConstants;
import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.common.util.WinterUtils;
import site.likailee.winter.core.springmvc.entity.MethodDetail;
import site.likailee.winter.core.ioc.BeanFactory;
import site.likailee.winter.core.springmvc.factory.ParameterResolverFactory;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;
import site.likailee.winter.core.springmvc.factory.RouteMethodMapper;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 处理 POST 请求
 *
 * @author likailee.llk
 * @version PostRequestHandler.java 2020/11/26 Thu 4:28 PM likai
 */
@Slf4j
public class PostRequestHandler implements RequestHandler {

    @Override
    public Object handle(FullHttpRequest fullHttpRequest) throws Exception {
        String contentType = UrlUtils.getContentType(fullHttpRequest);
        if (!HttpConstants.APPLICATION_JSON.equals(contentType)) {
            throw new IllegalAccessException("Post method only accept " + HttpConstants.APPLICATION_JSON);
        }
        String requestUri = fullHttpRequest.uri();
        // 根据 URL 从路由表中获取对应方法
        String requestPath = UrlUtils.getRequestPath(requestUri);
        MethodDetail methodDetail = RouteMethodMapper.getMethodDetail(requestPath, HttpMethod.POST);
        // 没有可以匹配该 URL 的方法
        if (methodDetail == null) {
            log.error("Post request on URL [{}] mapping failed!", requestPath);
            throw new NoSuchMethodException("Post request on URL [" + requestPath + "] mapping failed!");
        }
        // 解析 URL 中的参数
        Map<String, String> queryParams = UrlUtils.getQueryParams(requestUri);
        methodDetail.setQueryParamMap(queryParams);
        Method dispatchMethod = methodDetail.getMethod();
        log.info("Post request on method [{}] with uri {}, methodDetail: {}", dispatchMethod.getName(), requestUri, methodDetail);
        // 解析 request body 中的数据
        String jsonStr = fullHttpRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
        methodDetail.setRequestBodyJsonStr(jsonStr);
        // 获取方法中的参数
        Object[] methodArgs = ParameterResolverFactory.getParameters(dispatchMethod, methodDetail);
        // 调用 URL 对应的方法
        String beanName = WinterUtils.getBeanName(methodDetail.getMethod().getDeclaringClass());
        Object bean = BeanFactory.BEANS.get(beanName);
        return ReflectionUtils.executeMethod(bean, dispatchMethod, methodArgs);
    }

}
