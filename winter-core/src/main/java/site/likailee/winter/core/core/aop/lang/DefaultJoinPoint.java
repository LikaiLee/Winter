/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.aop.lang;

import java.util.Arrays;

/**
 * 适用于 @Before, @After
 *
 * @author likailee.llk
 * @version JoinPointImpl.java 2020/12/08 Tue 2:20 PM likai
 */
public class DefaultJoinPoint implements JoinPoint {
    private final Object adviceBean;
    private final Object target;
    private final Object[] args;

    public DefaultJoinPoint(Object adviceBean, Object target, Object[] args) {
        this.adviceBean = adviceBean;
        this.target = target;
        this.args = args;
    }

    @Override
    public Object getAdviceBean() {
        return adviceBean;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public Object[] getArgs() {
        if (args == null) {
            return new Object[0];
        }
        return Arrays.copyOf(args, args.length);
    }
}
