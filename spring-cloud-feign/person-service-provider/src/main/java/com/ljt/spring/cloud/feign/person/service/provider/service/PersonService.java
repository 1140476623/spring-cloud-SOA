package com.ljt.spring.cloud.feign.person.service.provider.service;

import com.ljt.spring.cloud.feign.person.service.provider.entity.Person;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * {@link Person} 服务
 */
@Service
@Transactional // 入口类加启用事务注解
public class PersonService {
    /**
     * 通过标准的JPA的方式注入
     * 国外jpa用的多，国内mybatis多（更灵活）
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 存储{@link Person}
     * @param person
     */
    public void save(Person person){
        entityManager.persist(person);
    }
}
