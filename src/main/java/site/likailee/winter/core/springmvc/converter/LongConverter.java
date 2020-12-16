/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version LongConverter.java 2020/12/16 Wed 8:41 PM likai
 */
public class LongConverter implements Converter {
    @Override
    public Object convert(String str) {
        return Long.valueOf(str);
    }
}
