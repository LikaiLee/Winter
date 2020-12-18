/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.ioc.Component;

/**
 * @author likailee.llk
 * @version ReadService.java 2020/12/02 Wed 3:13 PM likai
 */
@Component(name = "ReadService")
public class ReadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadService.class);

    public String read() {
        LOGGER.info("reading");
        return "reading";
    }
}
