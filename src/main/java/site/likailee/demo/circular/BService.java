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
 * @version BService.java 2020/12/03 Thu 8:39 PM likai
 */
@Component(name = "BService")
@Slf4j
public class BService {
    @Autowired
    private CService aService;

    public void test() {
        log.info("BBBB test()");
    }
}
