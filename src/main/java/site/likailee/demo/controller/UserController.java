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

import java.util.*;

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

    @GetMapping("/dataType")
    public String dataType(@RequestParam("int") int int0,
                           @RequestParam("Integer") Integer int1,
                           @RequestParam("long") long long0,
                           @RequestParam("Long") Long long1,
                           @RequestParam("float") float float0,
                           @RequestParam("Float") Float float1,
                           @RequestParam("double") double double0,
                           @RequestParam("Double") Double double1,
                           @RequestParam("boolean") boolean boolean0,
                           @RequestParam("Boolean") Boolean boolean1,
                           @RequestParam("byte") Byte byte0,
                           @RequestParam("Byte") Byte byte1,
                           @RequestParam("char") char char0,
                           @RequestParam("Character") Character char1,
                           @RequestParam("short") short short0,
                           @RequestParam("Short") Short short1
    ) {
        return int0 + ", " + int1 + ", " +
                long0 + ", " + long1 + ", " +
                float0 + ", " + float1 + ", " +
                double0 + ", " + double1 + ", " +
                boolean0 + ", " + boolean1 + ", " +
                byte0 + ", " + byte1 + ", " +
                char0 + ", " + char1 + ", " +
                short0 + ", " + short1;
    }

    @GetMapping("/list")
    public Set<Integer> listParam(@RequestParam("list") LinkedList<Boolean> list,
                                 @RequestParam("set") Set<Integer> set,
                                 @RequestBody Map<String, String> bodyMap) {
        log.info(list.toString());
        log.info(set.toString());
        log.info(bodyMap.toString());
        return set;
    }
}
