/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.springmvc.converter;

/**
 * @author likailee.llk
 * @version CharacterConverter.java 2020/12/16 Wed 9:05 PM likai
 */
public class CharacterConverter implements Converter {
    @Override
    public Object convert(String str) {
        return str.charAt(0);
    }
}
