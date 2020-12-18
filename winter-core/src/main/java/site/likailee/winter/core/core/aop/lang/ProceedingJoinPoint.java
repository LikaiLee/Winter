/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.lang;

import lombok.AllArgsConstructor;
import site.likailee.winter.core.common.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 适用于 @Around
 *
 * @author likailee.llk
 * @version ProceedingJoinPoint.java 2020/12/09 Wed 5:42 PM likai
 */
@AllArgsConstructor
public class ProceedingJoinPoint implements JoinPoint {

    private final Object adviceBean;
    private final Object target;
    private final Method method;
    private final Object[] args;

    /**
     * 执行被代理的原方法
     *
     * @return
     */
    public Object proceed() {
        return ReflectionUtils.executeMethod(target, method, args);
    }

    @Override
    public Object getAdviceBean() {
        return adviceBean;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public Object[] getArgs() {
        if (args == null) {
            return new Object[0];
        }
        return Arrays.copyOf(args, args.length);
    }
}
