/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version ByteConverter.java 2020/12/16 Wed 9:04 PM likai
 */
public class ByteConverter implements Converter {
    @Override
    public Object convert(String str) {
        return Byte.valueOf(str);
    }
}
