/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.config;

import site.likailee.winter.core.config.resource.ResourceLoader;
import site.likailee.winter.core.config.resource.property.PropertyResourceLoader;
import site.likailee.winter.core.config.resource.yaml.YamlResourceLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author likailee.llk
 * @version ConfigurationManager.java 2020/12/09 Wed 2:04 PM likai
 */
public class ConfigurationManager implements Configuration {

    private static final String PROPERTIES_FILE_EXTENSION = ".properties";

    private static final String YAML_FILE_EXTENSION = ".yaml";

    private static final String YML_FILE_EXTENSION = ".yml";

    private Configuration configuration;

    public ConfigurationManager(Configuration configuration) {
        // 继承自 AbstractConfiguration 的配置
        this.configuration = configuration;
    }

    @Override
    public void loadResources(List<Path> resourcePaths) {
        resourcePaths.forEach(path -> {
            try {
                String fileName = path.getFileName().toString();
                // 加载每个配置资源的内容
                // application.properties
                if (fileName.endsWith(PROPERTIES_FILE_EXTENSION)) {
                    ResourceLoader resourceLoader = new PropertyResourceLoader();
                    configuration.putAll(resourceLoader.loadResource(path));
                }
                // application.yaml, application.yml
                else if (fileName.endsWith(YAML_FILE_EXTENSION) || fileName.endsWith(YML_FILE_EXTENSION)) {
                    ResourceLoader resourceLoader = new YamlResourceLoader();
                    configuration.putAll(resourceLoader.loadResource(path));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getInt(String id) {
        return configuration.getInt(id);
    }

    @Override
    public String getString(String id) {
        return configuration.getString(id);
    }

    @Override
    public Boolean getBoolean(String id) {
        return configuration.getBoolean(id);
    }
}
