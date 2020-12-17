/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version FloatConverter.java 2020/12/16 Wed 8:35 PM likai
 */
public class FloatConverter implements Converter {
    @Override
    public Object convert(String str) {
        return Float.valueOf(str);
    }
}
