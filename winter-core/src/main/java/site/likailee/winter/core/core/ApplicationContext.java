/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core;

import site.likailee.winter.core.annotation.boot.ComponentScan;
import site.likailee.winter.core.common.Banner;
import site.likailee.winter.core.core.aop.factory.InterceptorFactory;
import site.likailee.winter.core.core.boot.ApplicationRunner;
import site.likailee.winter.core.core.config.Configuration;
import site.likailee.winter.core.core.config.ConfigurationManager;
import site.likailee.winter.core.core.factory.ClassFactory;
import site.likailee.winter.core.core.ioc.BeanFactory;
import site.likailee.winter.core.core.ioc.DependencyInjection;
import site.likailee.winter.core.core.springmvc.factory.RouteMethodMapper;
import site.likailee.winter.core.server.HttpServer;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author likailee.llk
 * @version ApplicationContext.java 2020/11/30 Mon 2:19 PM likai
 */
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
        Banner.print();
        // 获取包
        String[] packageNames = getPackageNames(applicationClass);
        // 加载注解类
        ClassFactory.loadClass(packageNames);
        // 加载路由
        RouteMethodMapper.loadRoutes();
        // 加载并实例化 Bean
        BeanFactory.loadBeans();
        // 加载配置资源
        loadResources(applicationClass);
        // 加载拦截器
        InterceptorFactory.loadInterceptors(packageNames);
        // 为 Bean 注入依赖
        DependencyInjection.inject(packageNames);
        // AOP 代理
        BeanFactory.applyBeanPostProcessors();
        callRunners();
    }

    private void loadResources(Class<?> applicationClass) {
        ClassLoader classLoader = applicationClass.getClassLoader();
        List<Path> filePaths = new ArrayList<>();
        for (String configName : Configuration.DEFAULT_CONFIG_NAMES) {
            URL url = classLoader.getResource(configName);
            if (Objects.isNull(url)) {
                continue;
            }
            try {
                filePaths.add(Paths.get(url.toURI()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        ConfigurationManager configurationManager = BeanFactory.getBeanForType(ConfigurationManager.class);
        configurationManager.loadResources(filePaths);
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
    private String[] getPackageNames(Class<?> applicationClass) {
        ComponentScan componentScan = applicationClass.getAnnotation(ComponentScan.class);
        return !Objects.isNull(componentScan) ? componentScan.value() : new String[]{applicationClass.getPackage().getName()};
    }
}
