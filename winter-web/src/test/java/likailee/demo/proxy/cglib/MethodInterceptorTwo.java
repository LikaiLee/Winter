/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.demo.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author likailee.llk
 * @version MethodInterceptorTwo.java 2020/12/04 Fri 8:43 PM likai
 */
@Slf4j
public class MethodInterceptorTwo implements MethodInterceptor {
    private Object target;

    public MethodInterceptorTwo(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("two before");
        // Object res = methodProxy.invoke(target, args);
        Object res = method.invoke(target, args);
        log.info("two after");
        return res;
    }
}
