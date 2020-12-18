/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.service.impl;

import site.likailee.winter.core.annotation.ioc.Component;
import site.likailee.winter.web.entity.User;
import site.likailee.winter.web.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author likailee.llk
 * @version UserServiceImpl.java 2020/12/02 Wed 1:27 PM likai
 */
@Component(name = "userServiceImpl")
public class UserServiceImpl implements UserService {
    private Integer id = 1;
    private static final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        users.put(id++, new User("name0", 10));
        users.put(id++, new User("name1", 10));
    }

    @Override
    public List<User> createUser(User user) {
        users.put(id++, user);
        return new ArrayList<>(users.values());
    }

    @Override
    public User getById(Integer id) {
        return users.get(id);
    }
}
