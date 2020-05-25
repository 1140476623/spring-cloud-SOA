package com.ljt.spring.cloud.feign.api.service;

import com.ljt.spring.cloud.feign.api.domain.Person;
import com.ljt.spring.cloud.feign.api.hystrix.PersonServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * {@link Person} 服务
 *
 */
//fallback熔断策略
@FeignClient(value = "person-service",fallback = PersonServiceFallback.class) // 服务提供方应用名称,对应服务端的person-service名称
public interface PersonService {
    /**
     * 保存
     * @param person {@link Person} 人实体类
     * @return 如果成功,<code>true</code>,否则<code>false</code>
     */
    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person);

    /**
     * 查找所有的服务
     * @return 不会返回null,当为空时返回空集合
     */
    @GetMapping(value = "/person/find/all")
    Collection<Person> findAll(); // springdatajpa里面，findAll模仿其命名这样命名的
}
