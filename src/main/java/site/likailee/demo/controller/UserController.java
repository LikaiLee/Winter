/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.controller;

import site.likailee.demo.service.PrintService;
import site.likailee.demo.service.ReadService;
import site.likailee.demo.service.UserService;
import site.likailee.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.ioc.Autowired;
import site.likailee.winter.annotation.ioc.Qualifier;
import site.likailee.winter.annotation.springmvc.*;
import site.likailee.winter.core.config.ConfigurationManager;

import java.util.List;

/**
 * @author likailee.llk
 * @version UserController.java 2020/11/27 Fri 1:04 PM likai
 */
@RestController("/user")
@Slf4j
public class UserController {

    @Autowired
    private ReadService readService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("bizPrintServiceImpl")
    private PrintService bizPrintServiceImpl;

    @Autowired
    @Qualifier("sysPrintServiceImpl")
    private PrintService sysPrintServiceImpl;

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @GetMapping
    public User get(@RequestParam(value = "name", required = true, defaultValue = "default name") String name,
                    @RequestParam(value = "age", defaultValue = "0") Integer age) {
        return new User(name, age);
    }

    @PostMapping
    public List<User> add(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/biz_print")
    public String bizPrint(@RequestParam("msg") String msg) {
        return bizPrintServiceImpl.print(msg);
    }

    @GetMapping("/sys_print")
    public String sysPrint(@RequestParam("msg") String msg) {
        return sysPrintServiceImpl.print(msg);
    }

    @GetMapping("/void")
    public void voidResult() {
        log.info("request on /user/void");
    }
}
