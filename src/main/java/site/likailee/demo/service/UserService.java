/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.service;

import site.likailee.demo.entity.User;

import java.util.List;

/**
 * @author likailee.llk
 * @version UserService.java 2020/12/02 Wed 1:27 PM likai
 */
public interface UserService {
    /**
     * create
     *
     * @param user
     * @return
     */
    List<User> createUser(User user);

    /**
     * get user by id
     *
     * @param id
     * @return
     */
    User getById(Integer id);
}
