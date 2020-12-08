/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.lang;

import java.util.Arrays;

/**
 * @author likailee.llk
 * @version JoinPointImpl.java 2020/12/08 Tue 2:20 PM likai
 */
public class JoinPointImpl implements JoinPoint {
    private Object adviceBean;
    private Object target;
    private Object[] args;

    public JoinPointImpl(Object adviceBean, Object target, Object[] args) {
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
