/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winter.core.annotation.RequestBody;
import com.winter.core.common.HttpConstants;
import com.winter.core.common.util.HttpRequestUtils;
import com.winter.core.common.util.ReflectionUtils;
import com.winter.core.router.Router;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理 POST 请求
 *
 * @author likailee.llk
 * @version PostRequestHandler.java 2020/11/26 Thu 4:28 PM likai
 */
@Slf4j
public class PostRequestHandler implements RequestHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object handle(FullHttpRequest fullHttpRequest) throws Exception {
        String contentTypeStr = fullHttpRequest.headers().get(HttpConstants.CONTENT_TYPE);
        // 获取 Content-Type
        String[] contentTypes = contentTypeStr.split(";");
        String contentType = contentTypes[0];
        if (!HttpConstants.APPLICATION_JSON.equals(contentType)) {
            throw new IllegalAccessException("Post method only accept " + HttpConstants.APPLICATION_JSON);
        }
        String requestUri = fullHttpRequest.uri();
        // 根据 URL 从路由表中获取对应方法
        String requestPath = HttpRequestUtils.getRequestPath(requestUri);
        Method dispatchMethod = Router.postMappings.get(requestPath);
        // 没有可以匹配该 URL 的方法
        if (dispatchMethod == null) {
            log.error("Post URL [{}] mapping failed!", requestPath);
            throw new NoSuchMethodException("Post URL [" + requestPath + "] mapping failed!");
        }
        // 解析 request body 中的数据
        String requestBodyJSONStr = fullHttpRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
        // 获取方法中的参数
        Parameter[] methodArgs = dispatchMethod.getParameters();
        List<Object> dispatchMethodArgs = new ArrayList<>();
        for (Parameter arg : methodArgs) {
            RequestBody requestBody = arg.getAnnotation(RequestBody.class);
            if (requestBody == null) {
                throw new IllegalArgumentException("@RequestBody for parameter [" + arg.getName() + "] is required for method " + dispatchMethod.getName());
            }
            Object param = objectMapper.readValue(requestBodyJSONStr, arg.getType());
            dispatchMethodArgs.add(param);
        }
        return ReflectionUtils.executeMethod(dispatchMethod, dispatchMethodArgs.toArray());
    }

}
