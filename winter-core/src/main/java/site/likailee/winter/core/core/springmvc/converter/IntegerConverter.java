/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version IntegerConverter.java 2020/12/16 Wed 8:27 PM likai
 */
public class IntegerConverter implements Converter{

    @Override
    public Object convert(String str) {
        return Integer.valueOf(str);
    }
}
