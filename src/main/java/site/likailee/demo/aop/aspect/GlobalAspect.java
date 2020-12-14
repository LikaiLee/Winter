/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.aop.*;
import site.likailee.winter.core.aop.lang.JoinPoint;

/**
 * @author likailee.llk
 * @version GlobalAspect.java 2020/12/07 Mon 3:21 PM likai
 */
// @Aspect
@Order(1)
@Slf4j
public class GlobalAspect {
    @Pointcut("site.likailee.demo.*.*Controller*")
    public void pointcut() {
    }

    @Before
    public void before(JoinPoint joinPoint) {
        log.info("before global aspect on class {}", joinPoint.getTarget().getClass().getName());
    }

    @After
    public void after(Object result, JoinPoint joinPoint) {
        log.info("after global aspect");
    }
}
