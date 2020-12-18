/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.aop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.core.aop.intercept.Interceptor;
import site.likailee.winter.core.core.aop.intercept.MethodInvocation;

/**
 * @author likailee.llk
 * @version CheckInterceptor.java 2020/12/04 Fri 12:06 PM likai
 */
public class CheckInterceptor extends Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckInterceptor.class);

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean supports(Object bean) {
        return false;
        // return bean instanceof PrintService;
    }

    @Override
    public Object intercept(MethodInvocation invocation) {
        LOGGER.info("-----> before CheckInterceptor");
        LOGGER.info("call method [{}#{}]", invocation.getTarget().getClass().getSimpleName(), invocation.getMethod().getName());
        Object result = invocation.proceed();
        LOGGER.info("CheckInterceptor after <----- ");
        return result;
    }
}
