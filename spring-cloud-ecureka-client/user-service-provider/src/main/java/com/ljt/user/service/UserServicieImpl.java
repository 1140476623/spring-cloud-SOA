package com.ljt.user.service;

import com.ljt.domain.User;
import com.ljt.service.UserService;
import com.ljt.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * {@link UserService 用户服务} 提供者实现
 *
 */
@Service
public class UserServicieImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
