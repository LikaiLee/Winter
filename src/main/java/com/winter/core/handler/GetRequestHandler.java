/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.util.List;
import java.util.Map;

/**
 * 处理 GET 请求
 *
 * @author likailee.llk
 * @version GetRequestHandler.java 2020/11/26 Thu 4:28 PM likai
 */
@Slf4j
public class GetRequestHandler implements RequestHandler {
    @Override
    public Object handle(FullHttpRequest fullHttpRequest) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(fullHttpRequest.uri(), Charsets.toCharset(CharEncoding.UTF_8));
        // 解析 URI 中的参数
        Map<String, List<String>> uriAttributes = queryStringDecoder.parameters();
        log.info(uriAttributes.toString());
        return null;
    }
}
