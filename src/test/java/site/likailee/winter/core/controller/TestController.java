/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.controller;

import site.likailee.winter.annotation.GetMapping;
import site.likailee.winter.annotation.PostMapping;
import site.likailee.winter.annotation.RestController;

/**
 * @author likailee.llk
 * @version TestController.java 2020/11/27 Fri 2:45 PM likai
 */
@RestController
@GetMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public void hello() {

    }

    @PostMapping("/world")
    public void world() {

    }
}
