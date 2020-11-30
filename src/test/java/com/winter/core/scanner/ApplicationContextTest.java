/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.scanner;

import com.winter.core.ApplicationContext;
import org.junit.Test;

/**
 * @author likailee.llk
 * @version RouterTest.java 2020/11/27 Fri 3:27 PM likai
 */
public class ApplicationContextTest {
    @Test
    public void testRouter() {
        ApplicationContext context = new ApplicationContext();
        context.loadRoutes("com.winter.demo");
    }
}
