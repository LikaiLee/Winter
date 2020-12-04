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
 * @version CheckInterceptor.java 2020/12/04 Fri 12:06 PM likai
 */
@Slf4j
public class CheckInterceptor implements Interceptor {
    @Override
    public boolean supports(String beanName) {
        // return "AopServiceImpl".equals(beanName);
        return false;
    }

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        log.info("-----> before CheckInterceptor");
        Object result = invocation.proceed();
        log.info("CheckInterceptor after <----- ");
        return result;
    }
}
