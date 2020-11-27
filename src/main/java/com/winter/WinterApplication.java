/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter;

import com.winter.core.common.Banner;
import com.winter.core.router.Router;
import com.winter.core.server.HttpServer;

/**
 * Winter is named because it is written in winter.
 *
 * @author likailee.llk
 * @version WinterApplication.java 2020/11/25 Wed 4:20 PM likai
 */
public class WinterApplication {
    public static void main(String[] args) {
        Banner.printBanner();
        Router router = new Router();
        router.loadRoutes("com.winter.demo");
        HttpServer server = new HttpServer();
        server.start();
    }
}
