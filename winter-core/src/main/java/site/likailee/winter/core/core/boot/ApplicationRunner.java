/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.boot;

/**
 * @author likailee.llk
 * @version ApplicationRunner.java 2020/12/09 Wed 1:12 PM likai
 */
@FunctionalInterface
public interface ApplicationRunner {
    /**
     * 完成 IoC, AOP 后，执行其他方法
     */
    void run();
}
