/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version Converter.java 2020/12/16 Wed 8:24 PM likai
 */
public interface Converter {
    /**
     * 将字符串转为方法需要的参数类型
     *
     * @param str
     * @return
     */
    Object convert(String str);
}
