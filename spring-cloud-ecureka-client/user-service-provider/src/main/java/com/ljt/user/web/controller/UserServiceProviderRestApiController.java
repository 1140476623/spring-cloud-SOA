package com.ljt.user.web.controller;

import com.ljt.domain.User;
import com.ljt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * {@link UserService 用户服务}提供方 REST API {@link RestController}
 */
@RestController
public class UserServiceProviderRestApiController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名创建用户
     *
     * @param user {@link User 用户} id不用设置
     * @return 如果保存成功返回 {@link User},否则返回<code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user){
        if(userService.save(user)){
            return user;
        }else {
            return null;
        }
    }

    /**
     * 查询所有用户
     *
     * @return 返回所有的用户 {@link User}
     */
    @GetMapping("/user/list")
    public Collection<User> list(){
        return userService.findAll();
    }
}
