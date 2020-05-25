package com.ljt.user.repository;

import com.ljt.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link User 用户} 仓储
 */
@Repository
public class UserRepository {
    // 这里内存存储演示
    private ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();

    // id
    private static final AtomicLong idGenerator = new AtomicLong(0);

    /**
     * 存储用户
     *
     * @param user
     * @return 存储成功返回<code>true</code>,否则返回<code>false</code>
     */
    public boolean save(User user) {
        long id = idGenerator.incrementAndGet();
        user.setId(id);

        //存在了就不会再put了
        return userMap.putIfAbsent(user.getId(),user) == null;
    }

    /**
     * 获取所有用户
     *
     * @return 返回用户集合，不会返回null
     */
    public Collection<User> findAll() {
        return userMap.values();
    }

}
