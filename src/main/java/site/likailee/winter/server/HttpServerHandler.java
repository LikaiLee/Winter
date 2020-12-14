/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.server;

import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.core.springmvc.factory.FullHttpResponseFactory;
import site.likailee.winter.core.springmvc.factory.RequestHandlerFactory;
import site.likailee.winter.core.springmvc.handler.RequestHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;


import static site.likailee.winter.common.HttpConstants.*;

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
        RequestHandler requestHandler = RequestHandlerFactory.get(fullHttpRequest.method());
        FullHttpResponse response;
        try {
            // 生成响应数据
            response = requestHandler.handle(fullHttpRequest);
        } catch (Exception e) {
            // log.error("internal server error occurs", e);
            String url = UrlUtils.getRequestPath(uri);
            // TODO: 返回不同错误类型
            response = FullHttpResponseFactory.getErrorResponse(url, e, HttpResponseStatus.INTERNAL_SERVER_ERROR);
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
