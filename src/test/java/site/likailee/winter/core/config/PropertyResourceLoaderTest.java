/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.likailee.winter.core.config.resource.property.PropertyResourceLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author likailee.llk
 * @version PropertyResourceLoaderTest.java 2020/12/09 Wed 2:53 PM likai
 */
@Slf4j
public class PropertyResourceLoaderTest {
    @Test
    void should_load_resource() throws IOException {
        Path path = Paths.get("/Users/likai/projects/winter/target/classes/application.properties");
        Map<String, String> config = new PropertyResourceLoader().loadResource(path);
        Assertions.assertEquals("winter", config.get("winter.name"));
    }
}
