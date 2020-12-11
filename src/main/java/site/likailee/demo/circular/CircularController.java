/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.circular;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.springmvc.GetMapping;
import site.likailee.winter.annotation.springmvc.RestController;

/**
 * @author likailee.llk
 * @version TestController.java 2020/12/03 Thu 8:48 PM likai
 */
@RestController("/circular")
@Slf4j
public class CircularController {
    @Autowired
    private AService aService;
    @Autowired
    private BService bService;
    @Autowired
    private CService cService;

    @GetMapping
    public String test() {
        aService.test();
        bService.test();
        cService.test();
        return "circular controller";
    }
}
