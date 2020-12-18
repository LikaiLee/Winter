/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.aop.interceptor;


import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.core.aop.intercept.Interceptor;
import site.likailee.winter.core.core.aop.intercept.MethodInvocation;

/**
 * @author likailee.llk
 * @version WebLogInterceptor.java 2020/12/04 Fri 10:45 AM likai
 */
@Slf4j
public class WebLogInterceptor extends Interceptor {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public boolean supports(Object bean) {
        // return bean instanceof PrintService || bean instanceof ReadService;
        return false;
        // return true;
    }

    @Override
    public Object intercept(MethodInvocation invocation) {
        log.info("-----> before WebLogInterceptor <----- ");
        log.info("call method [{}#{}]", invocation.getTarget().getClass().getSimpleName(), invocation.getMethod().getName());
        Object result = invocation.proceed();
        result += " ## this is from WebLogInterceptor";
        log.info("-----> WebLogInterceptor after <----- ");
        return result;
    }
}
