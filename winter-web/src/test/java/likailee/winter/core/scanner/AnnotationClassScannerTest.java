/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.winter.core.scanner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.likailee.winter.core.annotation.springmvc.RestController;
import site.likailee.winter.core.common.util.ReflectionUtils;

import java.util.Set;

/**
 * @author likailee.llk
 * @version AnnotationClassScannerTest.java 2020/11/27 Fri 2:43 PM likai
 */
@Slf4j
public class AnnotationClassScannerTest {
    @Test
    public void test() {
        Set<Class<?>> classes = ReflectionUtils.scanAnnotatedClass(new String[]{"site.likailee.winter.web"}, RestController.class);
        Assertions.assertEquals(4, classes.size());
    }
}
