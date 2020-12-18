/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.circular;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.annotation.ioc.Autowired;
import site.likailee.winter.core.annotation.ioc.Component;

/**
 * @author likailee.llk
 * @version AService.java 2020/12/03 Thu 8:39 PM likai
 */
@Component(name = "AService")
@Slf4j
public class AService {
    @Autowired
    private BService bService;

    public void test() {
        log.info("AAAA test()");
    }
}
