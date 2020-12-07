/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop;

/**
 * 方法拦截器
 *
 * @author likailee.llk
 * @version Interceptor.java 2020/12/04 Fri 10:47 AM likai
 */
public abstract class Interceptor {
    /**
     * 获取优先级
     *
     * @return
     */
    public int getOrder() {
        return -1;
    }

    public boolean supports(Object bean) {
        return true;
    }

    /**
     * 控制代理对象的方法
     *
     * @param invocation 被代理对象的方法
     * @return 方法执行结果
     */
    public abstract Object intercept(MethodInvocation invocation);
}
