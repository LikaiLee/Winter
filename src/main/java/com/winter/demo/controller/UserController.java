/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.demo.controller;

import com.winter.core.annotation.GetMapping;
import com.winter.core.annotation.PostMapping;
import com.winter.core.annotation.RestController;
import com.winter.demo.entity.User;

/**
 * @author likailee.llk
 * @version UserController.java 2020/11/27 Fri 1:04 PM likai
 */
@RestController
@GetMapping("/user")
public class UserController {

    @GetMapping("/get")
    public User get() {
        return new User("name", 10);
    }

    @PostMapping("/add")
    public void add() {

    }
}
