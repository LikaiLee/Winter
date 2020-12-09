/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.config.resource.property;

import site.likailee.winter.core.config.resource.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author likailee.llk
 * @version PropertyResourceLoader.java 2020/12/09 Wed 2:29 PM likai
 */
public class PropertyResourceLoader implements ResourceLoader {
    @Override
    public Map<String, String> loadResource(Path path) throws IOException {
        Properties properties = new Properties();
        try (Reader reader = new InputStreamReader(Files.newInputStream(path))) {
            properties.load(reader);
        }
        Map<String, String> resource = new HashMap<>(properties.size());
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            resource.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return resource;
    }
}
