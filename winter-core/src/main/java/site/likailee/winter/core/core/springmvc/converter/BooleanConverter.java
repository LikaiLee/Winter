/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version BooleanConverter.java 2020/12/16 Wed 8:57 PM likai
 */
public class BooleanConverter implements Converter {
    @Override
    public Object convert(String str) {
        return Boolean.valueOf(str);
    }
}
