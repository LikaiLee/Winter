/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.common;

import com.winter.core.common.util.UrlUtils;
import org.junit.Test;

import java.util.Map;

/**
 * @author likailee.llk
 * @version UrlUtilsTest.java 2020/11/30 Mon 3:18 PM likai
 */
public class UrlUtilsTest {
    @Test
    public void test() {
        Map<String, String> res = UrlUtils.getPathParameterMappings("/user/1/100", "/user/{id}/{age}");
        System.out.println(res);
    }
}
