/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.scanner;

import site.likailee.winter.core.ApplicationContext;
import org.junit.Test;

/**
 * @author likailee.llk
 * @version RouterTest.java 2020/11/27 Fri 3:27 PM likai
 */
public class ApplicationContextTest {
    @Test
    public void testRouter() {
        ApplicationContext context = new ApplicationContext();
        context.run("site.likailee.demo");
    }
}
