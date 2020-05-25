package com.ljt.spring.cloud.feign.api.hystrix;

import com.ljt.spring.cloud.feign.api.domain.Person;
import com.ljt.spring.cloud.feign.api.service.PersonService;

import java.util.Collection;
import java.util.Collections;

/**
 * 熔断回调{@link PersonService}
 */
public class PersonServiceFallback implements PersonService {
    @Override
    public boolean save(Person person) {
        return false;
    }

    @Override
    public Collection<Person> findAll() {
        return Collections.emptyList();
    }
}
