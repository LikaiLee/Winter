/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;

/**
 * @author likailee.llk
 * @version CglibProxyTest.java 2020/12/04 Fri 8:26 PM likai
 */
public class CglibProxyTest {
    @Test
    void should_get_cglib_proxy() {
        Target target = new Target();
        MethodInterceptorOne interceptorOne = new MethodInterceptorOne(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(interceptorOne);
        Target proxy1 = (Target) enhancer.create();
        // proxy1.print();

        MethodInterceptorOne interceptorTwo = new MethodInterceptorOne(proxy1);
        Enhancer enhancer2 = new Enhancer();
        enhancer2.setSuperclass(Target.class);
        enhancer2.setCallback(interceptorTwo);
        Target proxy2 = (Target) enhancer2.create();
        proxy2.print();
    }
}
