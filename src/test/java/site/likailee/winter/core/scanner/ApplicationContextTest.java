/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.scanner;

import org.junit.jupiter.api.Test;
import site.likailee.winter.WinterApplication;
import site.likailee.winter.core.ApplicationContext;

/**
 * @author likailee.llk
 * @version RouterTest.java 2020/11/27 Fri 3:27 PM likai
 */
public class ApplicationContextTest {
    @Test
    public void testRouter() {
        ApplicationContext context = new ApplicationContext();
        context.run(WinterApplication.class);
    }
}
