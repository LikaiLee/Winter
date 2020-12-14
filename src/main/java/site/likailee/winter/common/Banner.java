/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.common;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author likailee.llk
 * @version Banner.java 2020/11/27 Fri 12:34 PM likai
 */
@Slf4j
public class Banner {

    public static void print() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(SystemConstants.CUSTOM_BANNER_FILE);
        if (url == null) {
            url = Thread.currentThread().getContextClassLoader().getResource(SystemConstants.DEFAULT_BANNER_FILE);
        }
        try {
            assert url != null;
            Path path = Paths.get(url.toURI());
            Files.lines(path).forEach(System.out::println);
        } catch (URISyntaxException | IOException e) {
            log.info("read banner error", e);
        }
    }
}
