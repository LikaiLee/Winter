/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop;

import java.lang.reflect.InvocationTargetException;

/**
 * 方法拦截器
 *
 * @author likailee.llk
 * @version Interceptor.java 2020/12/04 Fri 10:47 AM likai
 */
public interface Interceptor {
    boolean supports(String beanName);

    /**
     * 控制代理对象的方法
     *
     * @param invocation 被代理对象的元数据
     * @return 方法执行结果
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
}
