/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.aop.Interceptor;
import site.likailee.winter.core.aop.MethodInvocation;

/**
 * @author likailee.llk
 * @version CheckInterceptor.java 2020/12/04 Fri 12:06 PM likai
 */
@Slf4j
public class CheckInterceptor extends Interceptor {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public boolean supports(Object bean) {
        return false;
        // return bean instanceof PrintService;
    }

    @Override
    public Object intercept(MethodInvocation invocation) {
        log.info("-----> before CheckInterceptor");
        log.info("call method [{}#{}]", invocation.getTarget().getClass().getSimpleName(), invocation.getMethod().getName());
        Object result = invocation.proceed();
        log.info("CheckInterceptor after <----- ");
        return result;
    }
}
