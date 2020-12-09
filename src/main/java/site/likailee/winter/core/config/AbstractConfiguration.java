/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version AbstractConfiguration.java 2020/12/09 Wed 2:22 PM likai
 */
public abstract class AbstractConfiguration implements Configuration {
    /**
     * 存储配置信息
     */
    private static Map<String, String> config = new ConcurrentHashMap<>(64);

    @Override
    public int getInt(String id) {
        String str = getString(id);
        return Integer.parseInt(str);
    }

    @Override
    public String getString(String id) {
        return config.get(id);
    }

    @Override
    public Boolean getBoolean(String id) {
        String str = getString(id);
        return Boolean.parseBoolean(str);
    }

    @Override
    public void put(String id, String content) {
        config.put(id, content);
    }

    @Override
    public void putAll(Map<String, String> map) {
        config.putAll(map);
    }
}
