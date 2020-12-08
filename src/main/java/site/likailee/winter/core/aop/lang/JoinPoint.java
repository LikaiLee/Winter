/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.lang;

/**
 * @author likailee.llk
 * @version JoinPoint.java 2020/12/08 Tue 2:19 PM likai
 */
public interface JoinPoint {
    /**
     * 获取切面类
     *
     * @return
     */
    Object getAdviceBean();

    /**
     * 获取被代理对象
     *
     * @return
     */
    Object getTarget();

    /**
     * 获取方法参数
     *
     * @return
     */
    Object[] getArgs();
}
