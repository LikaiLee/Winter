/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.core.controller;

import com.winter.core.annotation.GetMapping;
import com.winter.core.annotation.PostMapping;
import com.winter.core.annotation.RestController;

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
