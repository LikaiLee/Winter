/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.demo.controller;

import com.winter.core.annotation.*;
import com.winter.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author likailee.llk
 * @version UserController.java 2020/11/27 Fri 1:04 PM likai
 */
@RestController("/user")
@Slf4j
public class UserController {

    @GetMapping("/get")
    public User get(@RequestParam("name") String name,
                    @RequestParam("age") Integer age) {
        return new User(name, age);
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        return new User(user.getName(), user.getAge());
    }
}
