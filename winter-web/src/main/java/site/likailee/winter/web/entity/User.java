/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author likailee.llk
 * @version User.java 2020/11/27 Fri 1:04 PM likai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private int age;
}
