package user.web.controller;

import com.ljt.domain.User;
import com.ljt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 用户服务 REST API
 */
@RestController
public class UserRestApiController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名创建用户
     *
     * @param name 请求参数名为"name"的数据
     * @return 如果保存成功返回 {@link User},否则返回<code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
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
