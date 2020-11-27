/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author likailee.llk
 * @version UrlUtils.java 2020/11/27 Fri 4:13 PM likai
 */
public class UrlUtils {
    /**
     * 将 URL 中的参数转化为 键值对，
     * 如果参数有多个值，用 `,` 分隔，
     * e.g. a=1&b=2&b=3 => b = `2,3`。
     *
     * @param uriAttributes 原始的参数 Map
     * @return
     */
    public static Map<String, String> getQueryParams(Map<String, List<String>> uriAttributes) {
        Map<String, String> queryParams = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : uriAttributes.entrySet()) {
            String val = String.join(",", entry.getValue());
            queryParams.put(entry.getKey(), val);
        }
        return queryParams;
    }
}
