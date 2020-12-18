/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.ioc.Autowired;
import site.likailee.winter.core.annotation.ioc.Qualifier;
import site.likailee.winter.core.annotation.springmvc.*;
import site.likailee.winter.web.entity.User;
import site.likailee.winter.web.service.PrintService;
import site.likailee.winter.web.service.ReadService;
import site.likailee.winter.web.service.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author likailee.llk
 * @version UserController.java 2020/11/27 Fri 1:04 PM likai
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
        LOGGER.info("request on /user/void");
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
    public Map<String, Object> listParam(@RequestParam("list") LinkedList<Boolean> list,
                                         @RequestParam("set") Set<Integer> set,
                                         @RequestParam("obj") Object o,
                                         @RequestParam Map<String, Object> paramsMap,
                                         @RequestBody Map<String, String> bodyMap) {
        LOGGER.info(list.toString());
        LOGGER.info(set.toString());
        LOGGER.info(o.toString());
        LOGGER.info(bodyMap.toString());
        LOGGER.info(paramsMap.toString());
        return paramsMap;
    }
}
