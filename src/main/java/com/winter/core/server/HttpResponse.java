/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.server;

import com.winter.core.serialize.impl.JacksonSerializer;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;

import static com.winter.core.common.HttpConstants.*;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author likailee.llk
 * @version HttpResponse.java 2020/11/27 Fri 9:04 PM likai
 */
public class HttpResponse {
    private static final JacksonSerializer SERIALIZER = new JacksonSerializer();

    /**
     * 返回正常响应结果
     *
     * @param result
     * @return
     */
    public static FullHttpResponse ok(Object result) {

        byte[] content = SERIALIZER.serialize(result);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, APPLICATION_JSON);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

    /**
     * 服务器异常
     *
     * @return
     */
    public static FullHttpResponse internalServerError() {
        byte[] content = SERIALIZER.serialize(INTERNAL_SERVER_ERROR.reasonPhrase());
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, APPLICATION_TEXT);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }
}
