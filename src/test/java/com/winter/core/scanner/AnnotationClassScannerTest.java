/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.scanner;

import com.winter.core.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author likailee.llk
 * @version AnnotationClassScannerTest.java 2020/11/27 Fri 2:43 PM likai
 */
@Slf4j
public class AnnotationClassScannerTest {
    @Test
    public void test() {
        Set<Class<?>> classes = AnnotationClassScanner.scan("com.winter.core", RestController.class);
        Assert.assertEquals(1, classes.size());
    }
}
