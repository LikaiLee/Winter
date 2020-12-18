/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web;


import site.likailee.winter.core.annotation.boot.ComponentScan;
import site.likailee.winter.core.annotation.boot.SpringBootApplication;
import site.likailee.winter.core.core.ApplicationContext;

/**
 * Winter is named because it is written in winter.
 *
 * @author likailee.llk
 * @version WinterApplication.java 2020/11/25 Wed 4:20 PM likai
 */
@SpringBootApplication
@ComponentScan({"site.likailee.winter.web", "site.likailee.winter.web.aop"})
public class WinterApplication {
    public static void main(String[] args) {
        WinterApplication.run(WinterApplication.class, args);
    }

    private static void run(Class<WinterApplication> applicationClass, String... args) {
        ApplicationContext context = ApplicationContext.getApplicationContext();
        context.run(applicationClass);
    }
}
