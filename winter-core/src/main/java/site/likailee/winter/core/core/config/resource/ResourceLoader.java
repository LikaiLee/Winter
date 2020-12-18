/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.config.resource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

/**
 * @author likailee.llk
 * @version ResourceLoader.java 2020/12/09 Wed 2:28 PM likai
 */
public interface ResourceLoader {
    /**
     * 读取配置文件的内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    Map<String, String> loadResource(Path path) throws IOException;
}
