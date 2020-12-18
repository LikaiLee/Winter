/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.exception;

/**
 * 接口有多个实现类，没有指定实现类
 *
 * @author likailee.llk
 * @version NoUniqueBeanDefinitionException.java 2020/12/02 Wed 2:33 PM likai
 */
public class NoUniqueBeanDefinitionException extends RuntimeException {
    public NoUniqueBeanDefinitionException(String message) {
        super(message);
    }
}
