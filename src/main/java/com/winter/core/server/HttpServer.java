/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.server;

import com.winter.core.common.SystemConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author likailee.llk
 * @version HttpServer.java 2020/11/26 Thu 3:32 PM likai
 */
@Slf4j
public class HttpServer {
    private static final int PORT = 8080;

    public void start() {
        // 用于处理客户端的 TCP 连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 负责每一条连接的具体读写数据的处理逻辑，真正负责 I/O 读写操作，交由对应的 Handler 处理。
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 启动引导类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 启用 Nagle 算法，解决糊涂窗口综合症
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 开启心跳机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 三次握手的请求队列长度
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast("decoder", new HttpRequestDecoder())
                                    .addLast("encoder", new HttpResponseEncoder())
                                    .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                    .addLast("handler", new HttpServerHandler());

                        }
                    });
            Channel channel = bootstrap.bind(PORT).sync().channel();
            log.info(SystemConstants.LOG_PORT_BANNER, PORT);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("exception occurs when server start: " + e);
        } finally {
            log.error("shut down bossGroup and workerGroup");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
