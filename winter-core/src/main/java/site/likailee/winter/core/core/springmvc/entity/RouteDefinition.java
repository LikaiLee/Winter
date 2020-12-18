/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.entity;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 请求与方法的元数据
 *
 * @author likailee.llk
 * @version MethodDetail.java 2020/11/30 Mon 2:56 PM likai
 */

public class RouteDefinition {
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

    public RouteDefinition() {
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, String> getPathParamMap() {
        return pathParamMap;
    }

    public void setPathParamMap(Map<String, String> pathParamMap) {
        this.pathParamMap = pathParamMap;
    }

    public Map<String, String> getQueryParamMap() {
        return queryParamMap;
    }

    public void setQueryParamMap(Map<String, String> queryParamMap) {
        this.queryParamMap = queryParamMap;
    }

    public String getRequestBodyJsonStr() {
        return requestBodyJsonStr;
    }

    public void setRequestBodyJsonStr(String requestBodyJsonStr) {
        this.requestBodyJsonStr = requestBodyJsonStr;
    }

}
