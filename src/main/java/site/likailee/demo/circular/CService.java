/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.circular;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.ioc.Component;

/**
 * @author likailee.llk
 * @version CService.java 2020/12/03 Thu 8:45 PM likai
 */
@Component(name = "CService")
@Slf4j
public class CService {
    @Autowired
    private AService aService;

    public void test() {
        log.info("CCCC test()");
    }
}
