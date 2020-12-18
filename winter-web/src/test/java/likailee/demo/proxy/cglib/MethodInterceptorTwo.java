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
 * @version MethodInterceptorTwo.java 2020/12/04 Fri 8:43 PM likai
 */
public class MethodInterceptorTwo implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodInterceptorTwo.class);
    private Object target;

    public MethodInterceptorTwo(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        LOGGER.info("two before");
        // Object res = methodProxy.invoke(target, args);
        Object res = method.invoke(target, args);
        LOGGER.info("two after");
        return res;
    }
}
