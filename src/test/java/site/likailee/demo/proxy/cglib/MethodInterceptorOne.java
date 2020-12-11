/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author likailee.llk
 * @version MethodInterceptor.java 2020/12/04 Fri 8:23 PM likai
 */
@Slf4j
public class MethodInterceptorOne implements MethodInterceptor {
    private Object target;

    public MethodInterceptorOne(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (!"print".equals(method.getName())) {
            return method.invoke(target, args);
        }

        long time = System.currentTimeMillis();
        log.info("one before {}, {}", target, time);
        // Object res = methodProxy.invoke(target, args);
        Object res = method.invoke(target, args);
        log.info("one after, {}", time);
        return res;
    }
}
