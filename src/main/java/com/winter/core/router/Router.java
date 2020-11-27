/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.router;

import com.winter.core.annotation.GetMapping;
import com.winter.core.annotation.PostMapping;
import com.winter.core.annotation.RestController;
import com.winter.core.scanner.AnnotationClassScanner;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 映射 URL => Method
 *
 * @author likailee.llk
 * @version Router.java 2020/11/27 Fri 3:14 PM likai
 */
@Slf4j
public class Router {
    public static Map<String, Method> getMappings = new HashMap<>();
    public static Map<String, Method> postMappings = new HashMap<>();

    public void loadRoutes(String packageName) {
        // 获取所有带有 @RestController 的类
        Set<Class<?>> classes = AnnotationClassScanner.scan(packageName, RestController.class);
        for (Class<?> clazz : classes) {
            // 解析控制器的 URL
            // TODO: baseUrl 转换成 Controller 上对应的 RequestMapping, GetMapping, PostMapping, 而不应该是 RestController 的 value
            String baseUrl = clazz.getAnnotation(RestController.class).value();
            // 解析控制器下的所有方法
            Method[] methods = clazz.getMethods();
            mapMethods(baseUrl, methods);

            log.info("Get Mapping: {}", getMappings);
            log.info("Post Mapping: {}", postMappings);
        }
    }

    /**
     * 对控制器下的所有方法建立映射
     *
     * @param baseUrl Controller 上的 mapping 值
     * @param methods Controller 下的所有方法
     */
    private void mapMethods(String baseUrl, Method[] methods) {
        for (Method method : methods) {
            // GET Method
            if (method.isAnnotationPresent(GetMapping.class)) {
                String url = method.getAnnotation(GetMapping.class).value();
                getMappings.put(baseUrl + url, method);
            }
            // POST Method
            else if (method.isAnnotationPresent(PostMapping.class)) {
                String url = method.getAnnotation(PostMapping.class).value();
                postMappings.put(baseUrl + url, method);
            }
        }
    }
}
