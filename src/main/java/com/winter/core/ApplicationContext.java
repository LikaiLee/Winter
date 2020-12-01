/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core;

import com.winter.core.annotation.GetMapping;
import com.winter.core.annotation.PostMapping;
import com.winter.core.annotation.RestController;
import com.winter.core.common.util.UrlUtils;
import com.winter.core.entity.MethodDetail;
import com.winter.core.scanner.AnnotationClassScanner;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author likailee.llk
 * @version ApplicationContext.java 2020/11/30 Mon 2:19 PM likai
 */
@Slf4j
public class ApplicationContext {
    private static final ApplicationContext APPLICATION_CONTEXT = new ApplicationContext();
    private static final Map<String, Method> GET_MAPPINGS = new HashMap<>();
    private static final Map<String, Method> POST_MAPPINGS = new HashMap<>();
    private static final Map<String, String> GET_URL_MAPPINGS = new HashMap<>();
    private static final Map<String, String> POST_URL_MAPPINGS = new HashMap<>();
    private static final Map<String, MethodDetail> GET_METHOD_MAPPINGS = new HashMap<>();
    private static final Map<String, MethodDetail> POST_METHOD_MAPPINGS = new HashMap<>();

    public ApplicationContext() {
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 扫描包内的所有控制器
     * 对控制器下的方法建立映射
     *
     * @param packageName
     */
    public void loadRoutes(String packageName) {
        // 获取所有带有 @RestController 的类
        Set<Class<?>> classes = AnnotationClassScanner.scan(packageName, RestController.class);
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
                    String formattedUrl = UrlUtils.formatUrl(url);
                    // 存放的是正则化后的 URL 模式串
                    GET_MAPPINGS.put(formattedUrl, method);
                    GET_URL_MAPPINGS.put(formattedUrl, url);
                }
                // POST Method
                else if (method.isAnnotationPresent(PostMapping.class)) {
                    String url = baseUrl + method.getAnnotation(PostMapping.class).value();
                    String formattedUrl = UrlUtils.formatUrl(url);
                    POST_MAPPINGS.put(formattedUrl, method);
                    POST_URL_MAPPINGS.put(formattedUrl, url);
                }
            }

            log.info("Get Mapping: {}", GET_URL_MAPPINGS);
            log.info("Post Mapping: {}", POST_URL_MAPPINGS);
        }
    }

    /**
     * 根据请求路径和请求方法获取控制器的方法
     *
     * @param requestPath 请求路径
     * @param httpMethod  请求方法
     * @return
     */
    public MethodDetail getMethodDetail(String requestPath, HttpMethod httpMethod) {
        if (HttpMethod.GET.equals(httpMethod)) {
            return handle(requestPath, GET_MAPPINGS, GET_URL_MAPPINGS, GET_METHOD_MAPPINGS);
        }
        if (HttpMethod.POST.equals(httpMethod)) {
            return handle(requestPath, POST_MAPPINGS, POST_URL_MAPPINGS, POST_METHOD_MAPPINGS);
        }
        return null;
    }

    private MethodDetail handle(String requestPath, Map<String, Method> methodMappings, Map<String, String> urlMappings, Map<String, MethodDetail> methodDetailMappings) {
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
                methodDetailMappings.put(patternUrl, methodDetail);
                return methodDetail;
            }
        }
        return null;
    }
}
