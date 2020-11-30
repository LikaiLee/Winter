/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.entity;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Method;
import java.util.Map;

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

}
