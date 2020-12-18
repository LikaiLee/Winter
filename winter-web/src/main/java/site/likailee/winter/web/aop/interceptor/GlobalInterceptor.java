/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.aop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.core.aop.intercept.Interceptor;
import site.likailee.winter.core.core.aop.intercept.MethodInvocation;
import site.likailee.winter.web.service.PrintService;

/**
 * @author likailee.llk
 * @version GlobalInterceptor.java 2020/12/04 Fri 6:24 PM likai
 */
public class GlobalInterceptor extends Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalInterceptor.class);

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
        LOGGER.info("---> global before");
        LOGGER.info("call method [{}#{}]", invocation.getTarget().getClass().getSimpleName(), invocation.getMethod().getName());
        Object res = invocation.proceed();
        LOGGER.info("global after <---");
        return res;
    }
}
