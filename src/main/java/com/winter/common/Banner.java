/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.common;

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
public class Banner {
    public static void printBanner() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(SystemConstants.APPLICATION_BANNER_FILE);
        if (url != null) {
            try {
                Path path = Paths.get(url.toURI());
                Files.lines(path).forEach(System.out::println);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
