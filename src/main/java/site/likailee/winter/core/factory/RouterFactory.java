/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.factory;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.GetMapping;
import site.likailee.winter.annotation.PostMapping;
import site.likailee.winter.annotation.RestController;
import site.likailee.winter.common.util.UrlUtils;
import site.likailee.winter.core.ApplicationContext;
import site.likailee.winter.core.entity.MethodDetail;
import site.likailee.winter.core.scanner.AnnotationClassScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author likailee.llk
 * @version RouterFactory.java 2020/12/01 Tue 1:42 PM likai
 */
@Slf4j
public class RouterFactory {
    public static final Map<String, Method> GET_MAPPINGS = new HashMap<>();
    public static final Map<String, Method> POST_MAPPINGS = new HashMap<>();
    public static final Map<String, String> GET_URL_MAPPINGS = new HashMap<>();
    public static final Map<String, String> POST_URL_MAPPINGS = new HashMap<>();

    static {
        loadRoutes();
    }

    /**
     * 加载路由
     */
    private static void loadRoutes() {
        Set<Class<?>> classes = ApplicationContext.CLASSES.get(RestController.class);

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
}
