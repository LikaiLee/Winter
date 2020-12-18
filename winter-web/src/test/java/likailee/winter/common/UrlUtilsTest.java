/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.winter.common;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.common.util.UrlUtils;

import java.util.Map;

/**
 * @author likailee.llk
 * @version UrlUtilsTest.java 2020/11/30 Mon 3:18 PM likai
 */
public class UrlUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlUtilsTest.class);

    @Test
    public void test() {
        Map<String, String> res = UrlUtils.getQueryParams("/user?name=myname&age=111");
        LOGGER.info(res.toString());
    }
}
