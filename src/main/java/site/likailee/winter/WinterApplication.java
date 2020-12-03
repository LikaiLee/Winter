/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter;

import site.likailee.winter.core.ApplicationContext;
import site.likailee.winter.common.Banner;
import site.likailee.winter.server.HttpServer;

/**
 * Winter is named because it is written in winter.
 *
 * @author likailee.llk
 * @version WinterApplication.java 2020/11/25 Wed 4:20 PM likai
 */
public class WinterApplication {
    public static void main(String[] args) {
        Banner.printBanner();
        ApplicationContext context = ApplicationContext.getApplicationContext();
        context.run("site.likailee.demo.circular");
        HttpServer server = new HttpServer();
        server.start();
    }
}
