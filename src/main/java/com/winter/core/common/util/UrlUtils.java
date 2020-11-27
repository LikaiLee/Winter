/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.common.util;

import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author likailee.llk
 * @version UrlUtils.java 2020/11/27 Fri 4:13 PM likai
 */
public class UrlUtils {
    /**
     * 将 URL 中的参数转化为 键值对，
     * 如果参数有多个值，用 `,` 分隔，
     * e.g. a=1&b=2&b=3 => b = `2,3`。
     *
     * @param requestUri 原始请求 URI
     * @return
     */
    public static Map<String, String> getQueryParams(String requestUri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(requestUri, Charsets.toCharset(CharEncoding.UTF_8));
        Map<String, List<String>> uriAttributes = queryStringDecoder.parameters();
        Map<String, String> queryParams = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : uriAttributes.entrySet()) {
            String val = String.join(",", entry.getValue());
            queryParams.put(entry.getKey(), val);
        }
        return queryParams;
    }

    /**
     * 获取请求路径
     *
     * @param requestUri 原始请求 URI
     * @return
     */
    public static String getRequestPath(String requestUri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(requestUri, Charsets.toCharset(CharEncoding.UTF_8));
        return queryStringDecoder.path();
    }
}