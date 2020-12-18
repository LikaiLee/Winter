/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.circular;

import site.likailee.winter.core.annotation.ioc.Autowired;
import site.likailee.winter.core.annotation.springmvc.GetMapping;
import site.likailee.winter.core.annotation.springmvc.RequestMapping;
import site.likailee.winter.core.annotation.springmvc.RestController;


/**
 * @author likailee.llk
 * @version TestController.java 2020/12/03 Thu 8:48 PM likai
 */
@RestController
@RequestMapping("/circular")
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
