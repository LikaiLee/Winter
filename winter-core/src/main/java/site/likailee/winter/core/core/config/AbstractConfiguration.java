/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.config;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version AbstractConfiguration.java 2020/12/09 Wed 2:22 PM likai
 */
public abstract class AbstractConfiguration implements Configuration {
    /**
     * 存储配置信息
     */
    private static final Map<String, String> CONFIG = new ConcurrentHashMap<>(64);

    @Override
    public int getInt(String id) {
        String port = getString(id);
        return Objects.isNull(port) ? Integer.MIN_VALUE : Integer.parseInt(port);
    }

    @Override
    public String getString(String id) {
        return CONFIG.get(id);
    }

    @Override
    public Boolean getBoolean(String id) {
        String str = getString(id);
        return Boolean.parseBoolean(str);
    }

    @Override
    public void put(String id, String content) {
        CONFIG.put(id, content);
    }

    @Override
    public void putAll(Map<String, String> map) {
        CONFIG.putAll(map);
    }
}
