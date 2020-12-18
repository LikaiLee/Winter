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
 * @version CService.java 2020/12/03 Thu 8:45 PM likai
 */
@Component(name = "CService")
public class CService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CService.class);

    @Autowired
    private AService aService;

    public void test() {
        LOGGER.info("CCCC test()");
    }
}
