/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.server;

import com.winter.core.factory.RequestHandlerFactory;
import com.winter.core.handler.RequestHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;


import static com.winter.core.common.HttpConstants.*;

/**
 * 处理 HTTP 请求
 *
 * @author likailee.llk
 * @version HttpServerHandler.java 2020/11/26 Thu 3:47 PM likai
 */
@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

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
        RequestHandler requestHandler = RequestHandlerFactory.create(fullHttpRequest.method());
        Object result;
        FullHttpResponse response;
        try {
            // 生成响应数据
            result = requestHandler.handle(fullHttpRequest);
            response = HttpResponse.ok(result);
        } catch (Exception e) {
            log.error("internal server error occurs", e);
            response = HttpResponse.internalServerError(fullHttpRequest.uri());
        }
        boolean isKeepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
        if (isKeepAlive) {
            response.headers().set(CONNECTION, KEEP_ALIVE);
            ctx.writeAndFlush(response);
        } else {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 请求处理完成
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
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
        cause.printStackTrace();
        ctx.close();
    }
}
