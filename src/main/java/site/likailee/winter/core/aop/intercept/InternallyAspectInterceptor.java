/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.intercept;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.aop.After;
import site.likailee.winter.annotation.aop.Before;
import site.likailee.winter.annotation.aop.Pointcut;
import site.likailee.winter.common.util.ReflectionUtils;
import site.likailee.winter.core.aop.lang.JoinPoint;
import site.likailee.winter.core.aop.lang.JoinPointImpl;
import site.likailee.winter.core.aop.util.PatternMatchUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author likailee.llk
 * @version InternallyAspectInterceptor.java 2020/12/08 Tue 1:58 PM likai
 */
@Slf4j
public class InternallyAspectInterceptor extends Interceptor {
    /**
     * 切面类
     */
    private Object adviceBean;
    /**
     * 切点表达式
     */
    private final Set<String> expressionUrls = new HashSet<>();

    /**
     * before
     */
    private final List<Method> beforeMethods = new ArrayList<>();
    /**
     * after
     */
    private final List<Method> afterMethods = new ArrayList<>();

    public InternallyAspectInterceptor(Object adviceBean) {
        this.adviceBean = adviceBean;
        init();
    }

    /**
     * 获取切点，before, after 方法
     */
    private void init() {
        for (Method method : adviceBean.getClass().getMethods()) {
            Pointcut pointcut = method.getAnnotation(Pointcut.class);
            if (!Objects.isNull(pointcut)) {
                expressionUrls.add(pointcut.value());
            }
            Before before = method.getAnnotation(Before.class);
            if (!Objects.isNull(before)) {
                beforeMethods.add(method);
            }
            After after = method.getAnnotation(After.class);
            if (!Objects.isNull(after)) {
                afterMethods.add(method);
            }
        }
    }

    @Override
    public boolean supports(Object bean) {
        if (beforeMethods.isEmpty() && afterMethods.isEmpty()) {
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
        JoinPoint joinPoint = new JoinPointImpl(adviceBean, invocation.getTarget(), invocation.getArgs());
        // 执行 before 方法
        beforeMethods.forEach(method -> ReflectionUtils.executeMethodNoResult(adviceBean, method, joinPoint));
        Object result = invocation.proceed();
        // 执行 after 方法
        afterMethods.forEach(method -> ReflectionUtils.executeMethodNoResult(adviceBean, method, result, joinPoint));
        return result;
    }
}
