/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import site.likailee.winter.annotation.aop.Aspect;
import site.likailee.winter.core.aop.intercept.Interceptor;
import site.likailee.winter.core.factory.ClassFactory;

import java.util.Map;
import java.util.Set;

/**
 * @author likailee.llk
 * @version BeanFactoryTest.java 2020/12/08 Tue 10:29 AM likai
 */
@Slf4j
public class BeanFactoryTest {
    @BeforeAll
    static void before_all() {
        String packageName = "site.likailee.demo";
        ClassFactory.loadClass(packageName);
        BeanFactory.loadBeans();
    }

    @Test
    void should_get_beans_for_type() {
        Map<String, Interceptor> interceptorMap = BeanFactory.getBeansOfType(Interceptor.class);
        Assertions.assertEquals(0, interceptorMap.size());
    }

    @Test
    void should_get_beans_for_class_name() {
        Set<Object> aspects = BeanFactory.getBeansForClassName(Aspect.class.getName());
        log.info(aspects.toString());
    }
}
