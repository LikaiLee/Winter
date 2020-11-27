/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.scanner;

import com.winter.core.router.Router;
import org.junit.Test;

/**
 * @author likailee.llk
 * @version RouterTest.java 2020/11/27 Fri 3:27 PM likai
 */
public class RouterTest {
    @Test
    public void testRouter() {
        Router router = new Router();
        router.loadRoutes("com.winter.core.controller");
    }
}
