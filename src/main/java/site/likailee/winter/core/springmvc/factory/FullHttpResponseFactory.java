/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.factory;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.exception.ErrorResponse;
import site.likailee.winter.serialize.impl.JacksonSerializer;

import java.lang.reflect.Method;

import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static site.likailee.winter.common.HttpConstants.*;

/**
 * @author likailee.llk
 * @version FullHttpResponseFactory.java 2020/12/11 Fri 4:02 PM likai
 */
public class FullHttpResponseFactory {
    private static final JacksonSerializer JSON_SERIALIZER = new JacksonSerializer();

    /**
     * 调用方法并返回 HTTP 结果
     *
     * @param bean
     * @param method
     * @param methodArgs
     * @return
     */
    public static FullHttpResponse getSuccessResponse(Object bean, Method method, Object[] methodArgs) {
        // 方法无返回值
        if (method.getReturnType() == void.class) {
            ReflectionUtils.executeMethodNoResult(bean, method, methodArgs);
            return buildSuccessResponse();
        }
        // 方法有返回值
        Object result = ReflectionUtils.executeMethod(bean, method, methodArgs);
        return buildSuccessResponse(result);
    }

    /**
     * 返回服务器异常结果
     *
     * @param url
     * @param ex
     * @param status
     * @return
     */
    public static FullHttpResponse getErrorResponse(String url, Exception ex, HttpResponseStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(status.code(), status.reasonPhrase(), ex.toString(), url);
        byte[] content = JSON_SERIALIZER.serialize(errorResponse);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, APPLICATION_JSON);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

    private static FullHttpResponse buildSuccessResponse() {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, APPLICATION_JSON);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

    private static FullHttpResponse buildSuccessResponse(Object obj) {
        byte[] content = JSON_SERIALIZER.serialize(obj);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, APPLICATION_JSON);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }
}
