package user.service;

import com.ljt.domain.User;
import com.ljt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * {@link UserService} Proxy 实现
 */
@Service
public class UserServiceProxy implements UserService {
    // user-service-provider user-service-provider的配置文件properties里面声明的spring.application.name
    private static final String PROVIDER_SERVER_URL_PREFIX = "http://user-service-provider/";

    /**
     * 通过REST API代理到服务器提供者
     */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean save(User user) {
        // restTemplate http请求调用提供者接口
        // "/user/save"来自user-service-provider/com/ljt/user/web/controller#saveUser的requestMapping
        User returnValue =
                restTemplate.postForObject(PROVIDER_SERVER_URL_PREFIX + "/user/save", user, User.class);
        // return returnValue != null // 可读性不如下面的强
        return returnValue == null ? true : false;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/list", Collection.class);
    }
}
