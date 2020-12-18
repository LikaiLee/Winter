/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.aop.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.aop.After;
import site.likailee.winter.core.annotation.aop.Before;
import site.likailee.winter.core.annotation.aop.Order;
import site.likailee.winter.core.annotation.aop.Pointcut;
import site.likailee.winter.core.core.aop.lang.JoinPoint;

/**
 * @author likailee.llk
 * @version GlobalAspect.java 2020/12/07 Mon 3:21 PM likai
 */
// @Aspect
@Order(1)
public class GlobalAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAspect.class);

    @Pointcut("site.likailee.demo.*.*Controller*")
    public void pointcut() {
    }

    @Before
    public void before(JoinPoint joinPoint) {
        LOGGER.info("before global aspect on class {}", joinPoint.getTarget().getClass().getName());
    }

    @After
    public void after(Object result, JoinPoint joinPoint) {
        LOGGER.info("after global aspect");
    }
}
