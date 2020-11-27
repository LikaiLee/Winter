/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.scanner;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author likailee.llk
 * @version AnnotationClassScanner.java 2020/11/27 Fri 1:15 PM likai
 */
@Slf4j
public class AnnotationClassScanner {
    public static Set<Class<?>> scan(String packageName, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packageName, new TypeAnnotationsScanner());
        Set<Class<?>> annotatedClass = reflections.getTypesAnnotatedWith(annotation, true);
        log.info("Number of class annotated with {}: {}", annotation.getName(), annotatedClass.size());
        return annotatedClass;
    }
}
