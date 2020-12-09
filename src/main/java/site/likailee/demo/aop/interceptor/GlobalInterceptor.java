/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop.interceptor;

import lombok.extern.slf4j.Slf4j;
import site.likailee.demo.service.PrintService;
import site.likailee.winter.core.aop.intercept.Interceptor;
import site.likailee.winter.core.aop.intercept.MethodInvocation;

/**
 * @author likailee.llk
 * @version GlobalInterceptor.java 2020/12/04 Fri 6:24 PM likai
 */
@Slf4j
public class GlobalInterceptor extends Interceptor {
    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public boolean supports(Object bean) {
        return bean instanceof PrintService;
        // return true;
    }

    @Override
    public Object intercept(MethodInvocation invocation) {
        log.info("---> global before");
        log.info("call method [{}#{}]", invocation.getTarget().getClass().getSimpleName(), invocation.getMethod().getName());
        Object res = invocation.proceed();
        log.info("global after <---");
        return res;
    }
}
