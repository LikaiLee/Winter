/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop;

import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.springmvc.GetMapping;
import site.likailee.winter.annotation.springmvc.RestController;

/**
 * @author likailee.llk
 * @version AopController.java 2020/12/04 Fri 12:01 PM likai
 */
@RestController("/aop")
public class AopController {
    @Autowired
    private AopService aopService;

    @GetMapping
    public String testGetAop() {
        return aopService.get();
    }
}
