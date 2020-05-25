package com.ljt.spring.cloud.feign.person.service.provider.repository;

import com.ljt.spring.cloud.feign.person.service.provider.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Person} 仓储
 * person实体类，Long主键
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {
}
