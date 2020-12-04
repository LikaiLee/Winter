/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author likailee.llk
 * @version ProxyFactory.java 2020/12/04 Fri 11:12 AM likai
 */
public class ProxyFactory implements InvocationHandler {
    /**
     * 被代理对象
     */
    private Object target;
    /**
     * 拦截器
     */
    private Interceptor interceptor;

    public ProxyFactory(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    /**
     * 生成代理对象
     *
     * @param target
     * @param interceptor
     * @return
     */
    public static Object getProxy(Object target, Interceptor interceptor) {
        ProxyFactory proxyFactory = new ProxyFactory(target, interceptor);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyFactory);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation methodInvocation = new Invocation(target, method, args);
        return interceptor.intercept(methodInvocation);
    }
}
