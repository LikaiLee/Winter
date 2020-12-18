/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.aop.aspect;


import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.annotation.aop.*;
import site.likailee.winter.core.core.aop.lang.JoinPoint;
import site.likailee.winter.core.core.aop.lang.ProceedingJoinPoint;

/**
 * @author likailee.llk
 * @version WebLogAspect.java 2020/12/07 Mon 3:18 PM likai
 */
// @Aspect
@Order(2)
@Slf4j
public class WebLogAspect {
    @Pointcut("site.likailee.demo.*.*Service*")
    public void pointcut() {
    }

    @Before
    public void before(JoinPoint joinPoint) {
        log.info("before web log aspect");
    }

    @After
    public void after(Object result, JoinPoint joinPoint) {
        log.info("after web log aspect");
    }

    @Around
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("around begin");
        Object result = proceedingJoinPoint.proceed();
        log.info("around end");
        return result;
    }
}
