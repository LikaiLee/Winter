/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.factory;

import io.netty.handler.codec.http.HttpMethod;
import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.core.entity.MethodDetail;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author likailee.llk
 * @version MethodDetailFactory.java 2020/12/01 Tue 1:41 PM likai
 */
public class MethodDetailFactory {
    /**
     * 根据请求路径和请求方法获取控制器的方法
     *
     * @param requestPath 请求路径
     * @param httpMethod  请求方法
     * @return
     */
    public static MethodDetail getMethodDetail(String requestPath, HttpMethod httpMethod) {
        if (HttpMethod.GET.equals(httpMethod)) {
            return handle(requestPath, RouterFactory.GET_MAPPINGS, RouterFactory.GET_URL_MAPPINGS);
        }
        if (HttpMethod.POST.equals(httpMethod)) {
            return handle(requestPath, RouterFactory.POST_MAPPINGS, RouterFactory.POST_URL_MAPPINGS);
        }
        return null;
    }

    private static MethodDetail handle(String requestPath, Map<String, Method> methodMappings, Map<String, String> urlMappings) {
        for (Map.Entry<String, Method> entry : methodMappings.entrySet()) {
            String patternUrl = entry.getKey();
            Pattern pattern = Pattern.compile(patternUrl);
            boolean match = pattern.matcher(requestPath).find();
            if (match) {
                MethodDetail methodDetail = new MethodDetail();
                methodDetail.setMethod(entry.getValue());
                String url = urlMappings.get(patternUrl);
                Map<String, String> urlParamMap = UrlUtils.getPathParameterMappings(requestPath, url);
                methodDetail.setPathParamMap(urlParamMap);
                return methodDetail;
            }
        }
        return null;
    }
}
