/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.factory;

import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.springmvc.GetMapping;
import site.likailee.winter.annotation.springmvc.PostMapping;
import site.likailee.winter.annotation.springmvc.RestController;
import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.core.springmvc.entity.MethodDetail;
import site.likailee.winter.core.factory.ClassFactory;

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
    public static final Map<String, Method> URL_TO_GET_REQUEST_METHOD = new HashMap<>();
    public static final Map<String, Method> URL_TO_POST_REQUEST_METHOD = new HashMap<>();
    public static final Map<String, String> GET_URL_MAP = new HashMap<>();
    public static final Map<String, String> POST_URL_MAP = new HashMap<>();

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
                    String formattedUrl = formatUrl(url);
                    if (URL_TO_GET_REQUEST_METHOD.containsKey(formattedUrl)) {
                        throw new IllegalArgumentException(String.format("duplicate GET request handler for url: %s", url));
                    }
                    // 存放的是正则化后的 URL 模式串
                    URL_TO_GET_REQUEST_METHOD.put(formattedUrl, method);
                    GET_URL_MAP.put(formattedUrl, url);
                }
                // POST Method
                else if (method.isAnnotationPresent(PostMapping.class)) {
                    String url = baseUrl + method.getAnnotation(PostMapping.class).value();
                    String formattedUrl = formatUrl(url);
                    if (URL_TO_POST_REQUEST_METHOD.containsKey(formattedUrl)) {
                        throw new IllegalArgumentException(String.format("duplicate POST request handler for url: %s", url));
                    }
                    URL_TO_POST_REQUEST_METHOD.put(formattedUrl, method);
                    POST_URL_MAP.put(formattedUrl, url);
                }
            }
        }
        log.info("Load GET mappings: {}", GET_URL_MAP.values());
        log.info("Load POST mappings: {}", POST_URL_MAP.values());
    }


    /**
     * 根据请求路径和请求方法获取控制器的方法
     *
     * @param requestPath 请求路径
     * @param httpMethod  请求方法
     * @return
     */
    public static MethodDetail getMethodDetail(String requestPath, HttpMethod httpMethod) {
        boolean success = false;
        MethodDetail methodDetail = new MethodDetail();
        if (HttpMethod.GET.equals(httpMethod)) {
            success = methodDetail.build(requestPath, RouteMethodMapper.URL_TO_GET_REQUEST_METHOD, RouteMethodMapper.GET_URL_MAP);
        }
        if (HttpMethod.POST.equals(httpMethod)) {
            success = methodDetail.build(requestPath, RouteMethodMapper.URL_TO_POST_REQUEST_METHOD, RouteMethodMapper.POST_URL_MAP);
        }
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
}
