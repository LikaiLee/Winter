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
    public static final Map<String, Method> GET_MAPPINGS = new HashMap<>();
    public static final Map<String, Method> POST_MAPPINGS = new HashMap<>();
    public static final Map<String, String> GET_URL_MAPPINGS = new HashMap<>();
    public static final Map<String, String> POST_URL_MAPPINGS = new HashMap<>();

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
        }
        log.info("Load GET mappings: {}", GET_URL_MAPPINGS.values());
        log.info("Load POST mappings: {}", POST_URL_MAPPINGS.values());
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
            success = methodDetail.build(requestPath, RouteMethodMapper.GET_MAPPINGS, RouteMethodMapper.GET_URL_MAPPINGS);
        }
        if (HttpMethod.POST.equals(httpMethod)) {
            success = methodDetail.build(requestPath, RouteMethodMapper.POST_MAPPINGS, RouteMethodMapper.POST_URL_MAPPINGS);
        }
        return success ? methodDetail : null;
    }
}
