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
 * @version BService.java 2020/12/03 Thu 8:39 PM likai
 */
@Component(name = "BService")
public class BService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BService.class);
    @Autowired
    private CService aService;

    public void test() {
        LOGGER.info("BBBB test()");
    }
}
