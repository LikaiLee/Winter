/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.common.util;

import com.winter.core.common.HttpConstants;
import io.netty.handler.codec.http.FullHttpRequest;
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
     * 获取请求路径
     *
     * @param requestUri 原始请求 URI
     * @return
     */
    public static String getRequestPath(String requestUri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(requestUri, Charsets.toCharset(CharEncoding.UTF_8));
        return queryStringDecoder.path();
    }

    /**
     * 获取请求 Content-Type
     *
     * @param fullHttpRequest
     * @return
     */
    public static String getContentType(FullHttpRequest fullHttpRequest) {
        String contentTypeStr = fullHttpRequest.headers().get(HttpConstants.CONTENT_TYPE);
        // 获取 Content-Type
        String[] contentTypes = contentTypeStr.split(";");
        return contentTypes[0];
    }

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
     * 正则化原始 URL
     * 用于匹配 @PathVariable 的 URL
     *
     * @param url
     * @return
     */
    public static String formatUrl(String url) {
        // replace {xxx} placeholders with regular expressions matching Chinese, English letters and numbers, and underscores
        String originPattern = url.replaceAll("(\\{\\w+})", "[\\\\u4e00-\\\\u9fa5_a-zA-Z0-9]+");
        String pattern = "^" + originPattern + "/?$";
        return pattern.replaceAll("/+", "/");
    }

    /**
     * 获取 @PathVariable 的 URL 参数
     *
     * @param requestPath
     * @param url
     * @return
     */
    public static Map<String, String> getPathParameterMappings(String requestPath, String url) {
        String[] requestParams = requestPath.split("/");
        String[] urlParams = url.split("/");
        Map<String, String> urlParameterMappings = new HashMap<>();
        for (int i = 1; i < urlParams.length; i++) {
            if (!urlParams[i].contains("{") && !urlParams[i].contains("}")) {
                continue;
            }
            urlParameterMappings.put(urlParams[i].replace("{", "").replace("}", ""), requestParams[i]);
        }
        return urlParameterMappings;
    }
}
