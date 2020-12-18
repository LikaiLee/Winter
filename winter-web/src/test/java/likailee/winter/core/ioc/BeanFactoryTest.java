/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import site.likailee.winter.core.core.factory.ClassFactory;
import site.likailee.winter.core.core.ioc.BeanFactory;


/**
 * @author likailee.llk
 * @version BeanFactoryTest.java 2020/12/08 Tue 10:29 AM likai
 */
@Slf4j
public class BeanFactoryTest {
    @BeforeAll
    static void before_all() {
        String[] packageNames = {"site.likailee.demo"};
        ClassFactory.loadClass(packageNames);
        BeanFactory.loadBeans();
    }
}
