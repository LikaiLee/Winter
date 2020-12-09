/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.boot.ComponentScan;
import site.likailee.winter.common.Banner;
import site.likailee.winter.core.aop.factory.InterceptorFactory;
import site.likailee.winter.core.boot.ApplicationRunner;
import site.likailee.winter.core.factory.ClassFactory;
import site.likailee.winter.core.springmvc.factory.RouteMethodMapper;
import site.likailee.winter.core.ioc.BeanFactory;
import site.likailee.winter.core.ioc.DependencyInjection;
import site.likailee.winter.server.HttpServer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

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
     * @param applicationClass
     */
    public void run(Class<?> applicationClass) {
        Banner.printBanner();
        // 获取包
        String[] packageNames = analyzePackageNames(applicationClass);
        // 加载注解类
        ClassFactory.loadClass(packageNames);
        // 加载路由
        RouteMethodMapper.loadRoutes();
        // 加载并实例化 Bean
        BeanFactory.loadBeans();
        // 加载拦截器
        InterceptorFactory.loadInterceptors(packageNames);
        // 为 Bean 注入依赖
        DependencyInjection.inject(packageNames);
        callRunners();
    }

    private void callRunners() {
        List<ApplicationRunner> runners = new ArrayList<>();
        runners.add(() -> {
            HttpServer server = new HttpServer();
            server.start();
        });
        for (Object runner : new LinkedHashSet<>(runners)) {
            ((ApplicationRunner) runner).run();
        }
    }

    /**
     * 获取 @ComponentScan 中的包名
     * 若不存在，则使用 Application 所在的包
     *
     * @param applicationClass
     * @return
     */
    private String[] analyzePackageNames(Class<?> applicationClass) {
        ComponentScan componentScan = applicationClass.getAnnotation(ComponentScan.class);
        return !Objects.isNull(componentScan) ? componentScan.value() : new String[]{applicationClass.getPackage().getName()};
    }
}
