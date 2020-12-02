/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.exception;

/**
 * 接口没有实现类
 *
 * @author likailee.llk
 * @version InterfaceNotImplementedException.java 2020/12/02 Wed 2:22 PM likai
 */
public class InterfaceNotImplementedException extends RuntimeException {
    public InterfaceNotImplementedException(String message) {
        super(message);
    }
}
