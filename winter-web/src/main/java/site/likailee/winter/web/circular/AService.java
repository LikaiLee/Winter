/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.circular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.ioc.Autowired;
import site.likailee.winter.core.annotation.ioc.Component;

/**
 * @author likailee.llk
 * @version AService.java 2020/12/03 Thu 8:39 PM likai
 */
@Component(name = "AService")
public class AService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AService.class);

    @Autowired
    private BService bService;

    public void test() {
        LOGGER.info("AAAA test()");
    }
}
