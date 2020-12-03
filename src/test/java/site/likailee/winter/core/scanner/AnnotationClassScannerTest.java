/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.likailee.winter.annotation.springmvc.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author likailee.llk
 * @version AnnotationClassScannerTest.java 2020/11/27 Fri 2:43 PM likai
 */
@Slf4j
public class AnnotationClassScannerTest {
    @Test
    public void test() {
        Set<Class<?>> classes = AnnotationClassScanner.scan("site.likailee.winter.core", RestController.class);
        Assertions.assertEquals(1, classes.size());
    }
}
