package com.ljt.spring.cloud.feign.client.web.controller;

import com.ljt.spring.cloud.feign.api.domain.Person;
import com.ljt.spring.cloud.feign.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PersonClientController {// 这里也可以实现PersonService接口，相当于作为一个代理，但是@ResponseBody这种不会应用过来，还是要申明

    private final PersonService personService;

    @Autowired
    public PersonClientController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * 保存
     * @param person {@link Person} 人实体类
     * @return 如果成功,<code>true</code>,否则<code>false</code>
     */
    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {
        return personService.save(person);

    }

    /**
     * 查找所有的服务
     * @return 不会返回null,当为空时返回空集合
     */
    @GetMapping(value = "/person/find/all")
    Collection<Person> findAll() {// springdatajpa里面，findAll模仿其命名这样命名的
        return personService.findAll();
    }

}
