/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.factory;

import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.annotation.springmvc.GetMapping;
import site.likailee.winter.core.annotation.springmvc.PostMapping;
import site.likailee.winter.core.annotation.springmvc.RestController;
import site.likailee.winter.core.core.factory.ClassFactory;
import site.likailee.winter.core.core.springmvc.entity.MethodDetail;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author likailee.llk
 * @version RouterFactory.java 2020/12/01 Tue 1:42 PM likai
 */
@Slf4j
public class RouteMethodMapper {
    public static final HttpMethod[] SUPPORTED_METHODS = {HttpMethod.GET, HttpMethod.POST};
    /**
     * HttpMethod => Map(模板化 URL -> 原始 URL)
     */
    private static final Map<HttpMethod, Map<String, String>> REQUEST_URL_MAP = new HashMap<>(2);
    /**
     * HttpMethod => Map(模板化 URL -> 控制器方法)
     */
    private static final Map<HttpMethod, Map<String, Method>> REQUEST_METHOD_MAP = new HashMap<>(2);

    static {
        for (HttpMethod httpMethod : SUPPORTED_METHODS) {
            REQUEST_URL_MAP.put(httpMethod, new HashMap<>(128));
            REQUEST_METHOD_MAP.put(httpMethod, new HashMap<>(128));
        }
    }

    /**
     * 加载路由
     */
    public static void loadRoutes() {
        Set<Class<?>> classes = ClassFactory.CLASSES.get(RestController.class);
        for (Class<?> clazz : classes) {
            // 解析控制器的 URL
            String baseUrl = clazz.getAnnotation(RestController.class).value();
            // 解析控制器下的所有方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                // GET Method
                if (method.isAnnotationPresent(GetMapping.class)) {
                    // 拼接 URL
                    String url = baseUrl + method.getAnnotation(GetMapping.class).value();
                    mapUrlToMethod(url, method, HttpMethod.GET);
                }
                // POST Method
                else if (method.isAnnotationPresent(PostMapping.class)) {
                    String url = baseUrl + method.getAnnotation(PostMapping.class).value();
                    mapUrlToMethod(url, method, HttpMethod.POST);
                }
            }
        }
    }

    /**
     * 根据请求路径和请求方法获取控制器的方法
     *
     * @param requestPath 请求路径
     * @param httpMethod  请求方法
     * @return
     */
    public static MethodDetail getMethodDetail(String requestPath, HttpMethod httpMethod) {
        MethodDetail methodDetail = new MethodDetail();
        boolean success = methodDetail.build(requestPath, REQUEST_METHOD_MAP.get(httpMethod), REQUEST_URL_MAP.get(httpMethod));
        return success ? methodDetail : null;
    }

    /**
     * 正则化原始 URL
     * 用于匹配 @PathVariable 的 URL
     *
     * @param url
     * @return
     */
    private static String formatUrl(String url) {
        // replace {xxx} placeholders with regular expressions matching Chinese, English letters and numbers, and underscores
        String originPattern = url.replaceAll("(\\{\\w+})", "[\\\\u4e00-\\\\u9fa5_a-zA-Z0-9]+");
        String pattern = "^" + originPattern + "/?$";
        return pattern.replaceAll("/+", "/");
    }

    /**
     * 将 URL 与 Method 建立映射
     *
     * @param url
     * @param method
     * @param httpMethod
     */
    private static void mapUrlToMethod(String url, Method method, HttpMethod httpMethod) {
        String formattedUrl = formatUrl(url);
        Map<String, Method> urlToMethod = REQUEST_METHOD_MAP.get(httpMethod);
        if (urlToMethod.containsKey(formattedUrl)) {
            throw new IllegalArgumentException(String.format("duplicate GET request handler for url: %s", url));
        }
        Map<String, String> formattedUrlToUrl = REQUEST_URL_MAP.get(httpMethod);
        // 存放的是正则化后的 URL 模式串
        urlToMethod.put(formattedUrl, method);
        formattedUrlToUrl.put(formattedUrl, url);
    }
}
