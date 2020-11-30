/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.demo.controller;

import com.winter.core.annotation.*;
import com.winter.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author likailee.llk
 * @version UserController.java 2020/11/27 Fri 1:04 PM likai
 */
@RestController("/user")
@Slf4j
public class UserController {

    private static final Map<String, User> users = new HashMap<>();

    @GetMapping("/get/{name}")
    public User get(@PathVariable("name") String name,
                    @RequestParam("age") Integer age) {
        return new User(name, age);
    }

    @PostMapping("/add")
    public Map<String, User> add(@RequestBody User user) {
        users.put(user.getName(), user);
        return users;
    }
}
