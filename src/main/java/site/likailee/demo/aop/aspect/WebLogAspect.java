/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.aop.After;
import site.likailee.winter.annotation.aop.Aspect;
import site.likailee.winter.annotation.aop.Before;
import site.likailee.winter.annotation.aop.Pointcut;
import site.likailee.winter.core.aop.lang.JoinPoint;

/**
 * @author likailee.llk
 * @version WebLogAspect.java 2020/12/07 Mon 3:18 PM likai
 */
@Aspect
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
}
