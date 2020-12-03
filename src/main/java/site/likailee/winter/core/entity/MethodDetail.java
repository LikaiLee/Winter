/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.entity;

import lombok.Data;
import lombok.ToString;
import site.likailee.winter.common.util.UrlUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 请求与方法的元数据
 *
 * @author likailee.llk
 * @version MethodDetail.java 2020/11/30 Mon 2:56 PM likai
 */
@Data
@ToString
public class MethodDetail {
    /**
     * 实际应该调用的方法
     */
    private Method method;
    /**
     * 地址参数
     */
    private Map<String, String> pathParamMap;
    /**
     * URL Query 参数
     */
    private Map<String, String> queryParamMap;
    /**
     * 请求方法体的 JSON 字符串
     */
    private String requestBodyJsonStr;

    /**
     * 根据 请求路径 寻找对应的方法
     *
     * @param requestPath
     * @param methodMappings
     * @param urlMappings
     */
    public boolean build(String requestPath, Map<String, Method> methodMappings, Map<String, String> urlMappings) {
        for (Map.Entry<String, Method> entry : methodMappings.entrySet()) {
            String patternUrl = entry.getKey();
            Pattern pattern = Pattern.compile(patternUrl);
            boolean match = pattern.matcher(requestPath).find();
            if (match) {
                this.setMethod(entry.getValue());
                String url = urlMappings.get(patternUrl);
                Map<String, String> urlParamMap = UrlUtils.getPathParameterMappings(requestPath, url);
                this.setPathParamMap(urlParamMap);
                return true;
            }
        }
        return false;
    }
}
