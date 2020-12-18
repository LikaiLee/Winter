/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.winter.core.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.likailee.winter.core.core.config.resource.yaml.YamlResourceLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author likailee.llk
 * @version YamlResourceLoaderTest.java 2020/12/09 Wed 3:09 PM likai
 */
@Slf4j
public class YamlResourceLoaderTest {
    @Test
    void should_get_yaml_resource() throws IOException {
        Path path = Paths.get("/Users/likai/projects/winter/winter-web/target/classes/application.yaml");
        Map<String, String> config = new YamlResourceLoader().loadResource(path);
        Assertions.assertEquals("likailee.cn@gmail.com", config.get("winter.email"));
    }
}
