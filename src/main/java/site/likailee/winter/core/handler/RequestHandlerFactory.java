/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.handler;

import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author likailee.llk
 * @version RequestHandlerFactory.java 2020/11/27 Fri 12:04 PM likai
 */
public class RequestHandlerFactory {
    private static final Map<HttpMethod, RequestHandler> REQUEST_HANDLERS = new HashMap<>();

    static {
        REQUEST_HANDLERS.put(HttpMethod.GET, new GetRequestHandler());
        REQUEST_HANDLERS.put(HttpMethod.POST, new PostRequestHandler());
    }

    public static RequestHandler create(HttpMethod httpMethod) {
        return REQUEST_HANDLERS.get(httpMethod);
    }
}
