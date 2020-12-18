/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.factory;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.springmvc.GetMapping;
import site.likailee.winter.core.annotation.springmvc.PostMapping;
import site.likailee.winter.core.annotation.springmvc.RequestMapping;
import site.likailee.winter.core.annotation.springmvc.RestController;
import site.likailee.winter.core.common.util.UrlUtils;
import site.likailee.winter.core.core.factory.ClassFactory;
import site.likailee.winter.core.core.springmvc.entity.RouteDefinition;
import site.likailee.winter.core.core.springmvc.enums.RequestMethod;
import site.likailee.winter.core.exception.ResponseException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author likailee.llk
 * @version RouterFactory.java 2020/12/01 Tue 1:42 PM likai
 */
public class RouteMethodMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteMethodMapper.class);
    /**
     * Map(模板化 URL -> 原始 URL)
     */
    private static final Map<String, String> REQUEST_URL_MAP = new HashMap<>(64);
    /**
     * (模板化 URL -> 路由表 Map<请求方法，对应的处理方法>)
     * <p>
     * '^/user$' => {
     * GET => method1
     * POST => method2
     * PUT => method3
     * ...
     * }
     * </p>
     */
    private static final Map<String, Map<RequestMethod, Method>> URL_TO_ROUTE_MAP = new HashMap<>(64);


    /**
     * 加载路由
     */
    public static void loadRoutes() {
        Set<Class<?>> classes = ClassFactory.CLASSES.get(RestController.class);
        for (Class<?> clazz : classes) {
            // 解析控制器的 URL
            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                baseUrl += clazz.getAnnotation(RequestMapping.class).value();
            }
            // 解析控制器下的所有方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                // 有 @RequestMapping
                if (Objects.nonNull(requestMapping)) {
                    String url = baseUrl + requestMapping.value();
                    RequestMethod[] requestMethods = requestMapping.method();
                    mapUrlToMethodDefinition(url, method, requestMethods);
                }
                // 没有 @RequestMapping
                else {
                    // GET Method
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        // 拼接 URL
                        String url = baseUrl + method.getAnnotation(GetMapping.class).value();
                        mapUrlToMethodDefinition(url, method, RequestMethod.GET);
                    }
                    // POST Method
                    else if (method.isAnnotationPresent(PostMapping.class)) {
                        String url = baseUrl + method.getAnnotation(PostMapping.class).value();
                        mapUrlToMethodDefinition(url, method, RequestMethod.POST);
                    }
                }
            }
            LOGGER.info(URL_TO_ROUTE_MAP.toString());
        }
    }

    /**
     * 将 URL 与 Method 建立映射
     *
     * @param url
     * @param method
     * @param requestMethods
     */
    private static void mapUrlToMethodDefinition(String url, Method method, RequestMethod... requestMethods) {
        String formattedUrl = UrlUtils.formatUrl(url);
        REQUEST_URL_MAP.put(formattedUrl, url);
        // url => Map<请求方法，处理方法>
        Map<RequestMethod, Method> routeMap = URL_TO_ROUTE_MAP.getOrDefault(formattedUrl, new HashMap<>());
        for (RequestMethod requestMethod : requestMethods) {
            if (routeMap.containsKey(requestMethod)) {
                throw new IllegalArgumentException(String.format("duplicate request handler for url: %s with request method: %s", url, requestMethod));
            }
            routeMap.put(requestMethod, method);
        }
        URL_TO_ROUTE_MAP.put(formattedUrl, routeMap);
    }

    /**
     * 根据请求路径和请求方法获取控制器的方法
     *
     * @param requestPath   请求路径
     * @param requestMethod 请求方法
     * @return
     */
    public static RouteDefinition getMethodDefinition(String requestPath, RequestMethod requestMethod) {
        // 不带路径参数
        for (Map.Entry<String, String> entry : REQUEST_URL_MAP.entrySet()) {
            String rawUrl = entry.getValue();
            // request path 与原始 URL 匹配
            if (rawUrl.equals(requestPath)) {
                String patternUrl = entry.getKey();
                return buildRoute(requestPath, requestMethod, patternUrl);
            }
        }
        // 带有路径参数
        for (Map.Entry<String, Map<RequestMethod, Method>> entry : URL_TO_ROUTE_MAP.entrySet()) {
            String patternUrl = entry.getKey();
            Pattern pattern = Pattern.compile(patternUrl);
            boolean match = pattern.matcher(requestPath).find();
            // request path 与模式 URL 匹配，则找对应 HTTP Method 的方法
            if (match) {
                return buildRoute(requestPath, requestMethod, patternUrl);
            }
        }
        throw new ResponseException(String.format("request on URL [%s] mapping failed!", requestPath), HttpResponseStatus.NOT_FOUND);
    }

    private static RouteDefinition buildRoute(String requestPath, RequestMethod requestMethod, String patternUrl) {
        Map<RequestMethod, Method> routeMap = URL_TO_ROUTE_MAP.get(patternUrl);
        Method targetMethod = routeMap.get(requestMethod);
        // HTTP Method 不匹配
        if (Objects.isNull(targetMethod)) {
            throw new ResponseException("request method " + requestMethod + " is not compatible with target http method", HttpResponseStatus.BAD_REQUEST);
        }
        // 解析 @PathVariable 变量
        String rawUrl = REQUEST_URL_MAP.get(patternUrl);
        Map<String, String> urlParamMap = UrlUtils.getPathParameterMappings(requestPath, rawUrl);
        RouteDefinition rd = new RouteDefinition();
        // 设置需要调用的方法
        rd.setMethod(targetMethod);
        // 设置路径参数
        rd.setPathParamMap(urlParamMap);
        return rd;
    }
}
