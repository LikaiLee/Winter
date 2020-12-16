/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.intercept;

import site.likailee.winter.annotation.aop.Order;
import site.likailee.winter.annotation.aop.Pointcut;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.core.aop.lang.ProceedingJoinPoint;
import site.likailee.winter.core.aop.util.PatternMatchUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author likailee.llk
 * @version AroundAspectInterceptor.java 2020/12/09 Wed 7:26 PM likai
 */
public class AroundAspectInterceptor extends Interceptor {
    /**
     * 切面类
     */
    private Object adviceBean;
    /**
     * around 方法
     */
    private Method adviceMethod;

    /**
     * 切点表达式
     */
    private final Set<String> expressionUrls = new HashSet<>();

    public AroundAspectInterceptor(Object adviceBean, Method adviceMethod) {
        this.adviceBean = adviceBean;
        this.adviceMethod = adviceMethod;
        init();
    }

    private void init() {
        // 设置优先级
        Class<?> aspectCls = adviceBean.getClass();
        if (aspectCls.isAnnotationPresent(Order.class)) {
            Order order = aspectCls.getAnnotation(Order.class);
            this.setOrder(order.value());
        }
        // 获取 Aspect 的匹配表达式
        for (Method method : adviceBean.getClass().getMethods()) {
            Pointcut pointcut = method.getAnnotation(Pointcut.class);
            if (Objects.nonNull(pointcut)) {
                expressionUrls.add(pointcut.value());
            }
        }
    }

    @Override
    public boolean supports(Object bean) {
        if (expressionUrls.isEmpty() || Objects.isNull(adviceMethod)) {
            return false;
        }
        for (String expression : expressionUrls) {
            // 表达式与 bean 类名匹配，可进行代理
            if (PatternMatchUtils.simpleMatch(expression, bean.getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object intercept(MethodInvocation invocation) {
        ProceedingJoinPoint proceedingJoinPoint = new ProceedingJoinPoint(adviceBean, invocation.getTarget(), invocation.getMethod(), invocation.getArgs());
        // 让 around 方法控制原方法的执行
        return ReflectionUtils.executeMethod(adviceBean, adviceMethod, proceedingJoinPoint);
    }
}
