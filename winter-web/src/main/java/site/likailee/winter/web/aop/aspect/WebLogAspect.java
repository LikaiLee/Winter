/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.aop.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.aop.*;
import site.likailee.winter.core.core.aop.lang.JoinPoint;
import site.likailee.winter.core.core.aop.lang.ProceedingJoinPoint;

/**
 * @author likailee.llk
 * @version WebLogAspect.java 2020/12/07 Mon 3:18 PM likai
 */
// @Aspect
@Order(2)
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);
    @Pointcut("site.likailee.demo.*.*Service*")
    public void pointcut() {
    }

    @Before
    public void before(JoinPoint joinPoint) {
        LOGGER.info("before web log aspect");
    }

    @After
    public void after(Object result, JoinPoint joinPoint) {
        LOGGER.info("after web log aspect");
    }

    @Around
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        LOGGER.info("around begin");
        Object result = proceedingJoinPoint.proceed();
        LOGGER.info("around end");
        return result;
    }
}
