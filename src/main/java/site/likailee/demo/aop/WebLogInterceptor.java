/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop;


import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.aop.Interceptor;
import site.likailee.winter.core.aop.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * @author likailee.llk
 * @version WebLogInterceptor.java 2020/12/04 Fri 10:45 AM likai
 */
@Slf4j
public class WebLogInterceptor implements Interceptor {

    @Override
    public boolean supports(String beanName) {
        return "AopServiceImpl".equals(beanName);
    }

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        log.info("-----> before WebLogInterceptor <----- ");
        log.info("call method [{}#{}]", invocation.getTarget().getClass().getSimpleName(), invocation.getMethod().getName());
        Object result = invocation.proceed();
        log.info("-----> WebLogInterceptor after <----- ");
        return result;
    }
}
