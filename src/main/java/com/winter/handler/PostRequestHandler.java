/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import static com.winter.common.HttpConstants.APPLICATION_JSON;
import static com.winter.common.HttpConstants.CONTENT_TYPE;


/**
 * 处理 POST 请求
 *
 * @author likailee.llk
 * @version PostRequestHandler.java 2020/11/26 Thu 4:28 PM likai
 */
@Slf4j
public class PostRequestHandler implements RequestHandler {
    @Override
    public Object handle(FullHttpRequest fullHttpRequest) {
        String contentTypeStr = fullHttpRequest.headers().get(CONTENT_TYPE);
        String[] contentTypes = contentTypeStr.split(";");
        String contentType = contentTypes[0];
        if (APPLICATION_JSON.equals(contentType)) {
            // 解析 request body 中的数据
            String jsonStr = fullHttpRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            log.info(jsonStr);
        }
        return null;
    }
}
