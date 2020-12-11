/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import site.likailee.winter.common.util.UrlUtils;

import java.util.Map;

/**
 * @author likailee.llk
 * @version UrlUtilsTest.java 2020/11/30 Mon 3:18 PM likai
 */
@Slf4j
public class UrlUtilsTest {

    @Test
    public void test() {
        Map<String, String> res = UrlUtils.getQueryParams("/user?name=myname&age=111");
        log.info(res.toString());
    }
}
