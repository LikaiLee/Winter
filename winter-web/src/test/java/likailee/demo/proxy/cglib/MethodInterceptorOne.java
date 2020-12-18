/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.demo.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author likailee.llk
 * @version MethodInterceptor.java 2020/12/04 Fri 8:23 PM likai
 */
public class MethodInterceptorOne implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodInterceptorOne.class);
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
        LOGGER.info("one before {}, {}", target, time);
        // Object res = methodProxy.invoke(target, args);
        Object res = method.invoke(target, args);
        LOGGER.info("one after, {}", time);
        return res;
    }
}
