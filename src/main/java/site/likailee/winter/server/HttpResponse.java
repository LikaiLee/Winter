/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.server;

import site.likailee.winter.exception.ErrorResponse;
import site.likailee.winter.serialize.impl.JacksonSerializer;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;

import static site.likailee.winter.common.HttpConstants.*;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author likailee.llk
 * @version HttpResponse.java 2020/11/27 Fri 9:04 PM likai
 */
public class HttpResponse {
    private static final JacksonSerializer JSON_SERIALIZER;

    static {
        JSON_SERIALIZER = new JacksonSerializer();
    }

    /**
     * 返回正常响应结果
     *
     * @param result
     * @return
     */
    public static FullHttpResponse ok(Object result) {

        byte[] content = JSON_SERIALIZER.serialize(result);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, APPLICATION_JSON);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

    /**
     * 服务器异常
     *
     * @param uri
     * @param ex
     * @return
     */
    public static FullHttpResponse internalServerError(String uri, Exception ex) {
        // TODO: 返回不同的错误类型
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR.code(), INTERNAL_SERVER_ERROR.reasonPhrase(), ex.toString(), uri);
        byte[] content = JSON_SERIALIZER.serialize(errorResponse);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, APPLICATION_JSON);
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }
}
