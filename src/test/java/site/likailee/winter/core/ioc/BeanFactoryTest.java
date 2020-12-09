/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import site.likailee.winter.core.factory.ClassFactory;


/**
 * @author likailee.llk
 * @version BeanFactoryTest.java 2020/12/08 Tue 10:29 AM likai
 */
@Slf4j
public class BeanFactoryTest {
    @BeforeAll
    static void before_all() {
        String packageName = "site.likailee.demo";
        ClassFactory.loadClass(packageName);
        BeanFactory.loadBeans();
    }
}
