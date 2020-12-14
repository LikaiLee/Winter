/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo;

import site.likailee.winter.annotation.boot.ComponentScan;
import site.likailee.winter.annotation.boot.SpringBootApplication;
import site.likailee.winter.core.ApplicationContext;

/**
 * Winter is named because it is written in winter.
 *
 * @author likailee.llk
 * @version WinterApplication.java 2020/11/25 Wed 4:20 PM likai
 */
@SpringBootApplication
@ComponentScan({"site.likailee.demo", "site.likailee.demo.aop"})
public class WinterApplication {
    public static void main(String[] args) {
        WinterApplication.run(WinterApplication.class, args);
    }

    private static void run(Class<WinterApplication> applicationClass, String... args) {
        ApplicationContext context = ApplicationContext.getApplicationContext();
        context.run(applicationClass);
    }
}
