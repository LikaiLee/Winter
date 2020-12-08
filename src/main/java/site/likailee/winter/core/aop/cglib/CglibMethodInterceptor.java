/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import site.likailee.winter.core.aop.Interceptor;
import site.likailee.winter.core.aop.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author likailee.llk
 * @version CglibMethodInterceptor.java 2020/12/04 Fri 4:33 PM likai
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    private final Object target;
    private final Interceptor interceptor;

    public CglibMethodInterceptor(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object getProxy(Object target, Interceptor interceptor) {
        Class<?> superClass = target.getClass();
        if (superClass.getName().contains("$$")) {
            superClass = superClass.getSuperclass();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(target.getClass().getClassLoader());
        enhancer.setSuperclass(superClass);
        enhancer.setCallback(new CglibMethodInterceptor(target, interceptor));
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
        MethodInvocation methodInvocation = new MethodInvocation(target, method, args);
        return interceptor.intercept(methodInvocation);
    }
}
