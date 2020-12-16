/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version ShortConverter.java 2020/12/16 Wed 9:03 PM likai
 */
public class ShortConverter implements Converter{
    @Override
    public Object convert(String str) {
        return Short.valueOf(str);
    }
}
