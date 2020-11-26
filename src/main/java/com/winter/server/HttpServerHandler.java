/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.server;

import com.winter.handler.GetRequestHandler;
import com.winter.handler.PostRequestHandler;
import com.winter.handler.RequestHandler;
import com.winter.serialize.impl.JacksonSerializer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


import static com.winter.common.HttpConstants.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 处理 HTTP 请求
 *
 * @author likailee.llk
 * @version HttpServerHandler.java 2020/11/26 Thu 3:47 PM likai
 */
@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final Map<HttpMethod, RequestHandler> requestHandlers;

    public HttpServerHandler() {
        this.requestHandlers = new HashMap<>();
        this.requestHandlers.put(HttpMethod.GET, new GetRequestHandler());
        this.requestHandlers.put(HttpMethod.POST, new PostRequestHandler());
    }

    /**
     * 处理请求
     *
     * @param ctx
     * @param fullHttpRequest
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        // log.info("Handle HTTP request: {}", fullHttpRequest);
        String uri = fullHttpRequest.uri();
        if (FAVICON.equals(uri)) {
            return;
        }
        // 获取对应的请求处理器
        RequestHandler requestHandler = requestHandlers.get(fullHttpRequest.method());
        Object result = requestHandler.handle(fullHttpRequest);
        // 生成响应数据
        FullHttpResponse response = buildHttpResponse(result);
        boolean isKeepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
        if (isKeepAlive) {
            response.headers().set(CONNECTION, KEEP_ALIVE);
            ctx.writeAndFlush(response);
        } else {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 生成响应的 JSON 数据
     *
     * @param result
     * @return
     */
    private FullHttpResponse buildHttpResponse(Object result) {
        JacksonSerializer serializer = new JacksonSerializer();
        byte[] content = serializer.serialize(result);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, "application/json");
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

    /**
     * 请求处理完成
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    /**
     * 产生异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
