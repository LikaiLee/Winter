/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.intercept;

/**
 * 方法拦截器
 *
 * @author likailee.llk
 * @version Interceptor.java 2020/12/04 Fri 10:47 AM likai
 */
public abstract class Interceptor {
    private int order = -1;

    public boolean supports(Object bean) {
        return false;
    }

    /**
     * 控制代理对象的方法
     *
     * @param invocation 被代理对象的方法
     * @return 方法执行结果
     */
    public abstract Object intercept(MethodInvocation invocation);

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
