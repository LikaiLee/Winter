/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.aop;

import site.likailee.winter.core.annotation.ioc.Autowired;
import site.likailee.winter.core.annotation.ioc.Qualifier;
import site.likailee.winter.core.annotation.springmvc.GetMapping;
import site.likailee.winter.core.annotation.springmvc.RequestParam;
import site.likailee.winter.core.annotation.springmvc.RestController;
import site.likailee.winter.web.aop.service.AopService;
import site.likailee.winter.web.service.PrintService;
import site.likailee.winter.web.service.ReadService;

/**
 * @author likailee.llk
 * @version AopController.java 2020/12/04 Fri 12:01 PM likai
 */
@RestController("/aop")
public class AopController {
    @Autowired
    private AopService aopService;

    @Autowired
    private ReadService readService;

    @Autowired
    @Qualifier("bizPrintServiceImpl")
    private PrintService bizPrintService;

    @GetMapping("/test_jdk_qualifier")
    public String testJdkQualifier(@RequestParam("name") String name) {
        return bizPrintService.print(name + " test_jdk_qualifier");
    }

    @GetMapping("/test_cglib")
    public String testCglib() {
        return readService.read();
    }
}
