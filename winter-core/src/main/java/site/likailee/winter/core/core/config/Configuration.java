/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.config;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * @author likailee.llk
 * @version Configuration.java 2020/12/09 Wed 1:49 PM likai
 */
public interface Configuration {
    /**
     * 配置文件名
     */
    String[] DEFAULT_CONFIG_NAMES = {"application.properties", "application.yaml"};

    /**
     * 获取 int
     *
     * @param id
     * @return
     */
    int getInt(String id);

    /**
     * 获取 String
     *
     * @param id
     * @return
     */
    String getString(String id);

    /**
     * 获取 Boolean
     *
     * @param id
     * @return
     */
    Boolean getBoolean(String id);

    /**
     * 添加单个配置
     *
     * @param id
     * @param content
     */
    default void put(String id, String content) {
    }

    /**
     * 添加多个配置
     *
     * @param map
     */
    default void putAll(Map<String, String> map) {
    }

    /**
     * 加载配置资源
     *
     * @param resourcePaths
     */
    default void loadResources(List<Path> resourcePaths) {
    }
}
