/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.factory.ClassFactory;
import site.likailee.winter.core.factory.RouterFactory;

/**
 * @author likailee.llk
 * @version ApplicationContext.java 2020/11/30 Mon 2:19 PM likai
 */
@Slf4j
public class ApplicationContext {
    private static final ApplicationContext APPLICATION_CONTEXT = new ApplicationContext();

    public ApplicationContext() {
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 入口方法
     *
     * @param packageName
     */
    public void run(String packageName) {
        // 加载注解类
        ClassFactory.loadClass(packageName);
        // 加载路由
        RouterFactory.loadRoutes();

    }
}
