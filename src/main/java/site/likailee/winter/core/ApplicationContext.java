/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.aop.factory.InterceptorFactory;
import site.likailee.winter.core.factory.ClassFactory;
import site.likailee.winter.core.springmvc.factory.RouteMethodMapper;
import site.likailee.winter.core.ioc.BeanFactory;
import site.likailee.winter.core.ioc.DependencyInjection;

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
        RouteMethodMapper.loadRoutes();
        // 加载并实例化 Bean
        BeanFactory.loadBeans();
        // 加载拦截器
        InterceptorFactory.loadInterceptors(packageName);
        // 为 Bean 注入依赖
        DependencyInjection.inject(packageName);
    }
}
