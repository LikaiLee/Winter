/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.intercept;

import site.likailee.winter.core.common.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 被代理对象的元数据
 *
 * @author likailee.llk
 * @version Invocation.java 2020/12/04 Fri 10:48 AM likai
 */
public class MethodInvocation {
    /**
     * 被代理对象
     */
    private final Object target;
    /**
     * 被代理方法
     */
    private final Method method;
    /**
     * 方法参数
     */
    private final Object[] args;

    public MethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    /**
     * 调用原方法
     *
     * @return 方法执行结果
     */
    public Object proceed() {
        return ReflectionUtils.executeMethod(target, method, args);
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}
