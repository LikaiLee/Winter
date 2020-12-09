/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop;

import site.likailee.demo.aop.service.AopService;
import site.likailee.demo.service.PrintService;
import site.likailee.demo.service.ReadService;
import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.ioc.Qualifier;
import site.likailee.winter.annotation.springmvc.GetMapping;
import site.likailee.winter.annotation.springmvc.RequestParam;
import site.likailee.winter.annotation.springmvc.RestController;

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
