/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version DoubleConverter.java 2020/12/16 Wed 8:41 PM likai
 */
public class DoubleConverter implements Converter{
    @Override
    public Object convert(String str) {
        return Double.valueOf(str);
    }
}
