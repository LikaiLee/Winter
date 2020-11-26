/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.handler;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author likailee.llk
 * @version RequestHandler.java 2020/11/26 Thu 4:04 PM likai
 */
public interface RequestHandler {
    /**
     * 处理对应的 HTTP 请求
     *
     * @param fullHttpRequest
     * @return
     */
    Object handle(FullHttpRequest fullHttpRequest);
}
