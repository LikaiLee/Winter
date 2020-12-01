/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package com.winter.demo.controller;

import com.winter.core.annotation.*;
import com.winter.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import sun.management.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author likailee.llk
 * @version UserController.java 2020/11/27 Fri 1:04 PM likai
 */
@RestController("/user")
@Slf4j
public class UserController {

    private static final Map<Integer, User> users = new HashMap<>();

    static {
        users.put(0, new User("name1", 10));
        users.put(1, new User("name2", 10));
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        return users.get(id);
    }

    @GetMapping
    public User get(@RequestParam("name") String name,
                    @RequestParam("age") Integer age) {
        return new User(name, age);
    }

    @PostMapping
    public List<User> add(@RequestBody User user) {
        users.put(users.size(), user);
        return new ArrayList<>(users.values());
    }
}
