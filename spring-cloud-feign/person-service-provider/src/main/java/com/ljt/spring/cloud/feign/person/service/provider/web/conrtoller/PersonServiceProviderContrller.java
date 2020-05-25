package com.ljt.spring.cloud.feign.person.service.provider.web.conrtoller;

import com.ljt.spring.cloud.feign.api.domain.Person;
import com.ljt.spring.cloud.feign.person.service.provider.repository.PersonRepository;
import com.ljt.spring.cloud.feign.person.service.provider.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * {@link PersonService} 提供者控制器(也可选择{@link PersonService}的方式来实现)
 *
 */
@RestController
public class PersonServiceProviderContrller {

    private Map<Long,Person> persons = new ConcurrentHashMap<Long, Person>() ;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    private final Random r = new Random();


    /**
     * 保存
     * @param person {@link Person} 人实体类
     * @return 如果成功,<code>true</code>,否则<code>false</code>
     */
    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {// 方法名可以不一样，但是/person/save必须与api一致（契约）
        //存内存
        //return persons.put(person.getId(),person) == null;
        com.ljt.spring.cloud.feign.person.service.provider.entity.Person person1=
                new com.ljt.spring.cloud.feign.person.service.provider.entity.Person();
        // Bean 转换 apache和spring都有。apache的前后属性不一致会报错。
        BeanUtils.copyProperties(person,person1);
        // 实现调用
        //personService.save(person1);
        // 纯接口调用
        personRepository.save(person1);
        return true;
    }

    /**
     * 查找所有的服务
     * @return 不会返回null,当为空时返回空集合
     */
    @GetMapping(value = "/person/find/all")
    @HystrixCommand(fallbackMethod = "fallbackForFindAllPerson", commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "100")})
    Collection<Person> findAll() throws InterruptedException {// springdatajpa里面，findAll模仿其命名这样命名的
        // 模拟看是否熔断,没有熔断person-service-provider会报错，熔断了页面会显示空数据
        System.err.println("check is hystrix :");

        int value = r.nextInt(200);
        System.err.println("wait time : "+value);
        Thread.sleep(value);

        return persons.values();
    }

    /**
     * 查询所有人熔断触发返回空集合
     *
     * {@link #findAll()} Fallback方法
     * @return 返回空集合
     */
    public Collection<Person> fallbackForFindAllPerson(){
        System.err.println("fallbackForFindAllPerson is using ...");
        return Collections.emptyList();
    }

    /**
     * 分页查询，默认每页20条数据，动态代理实现，size为每页数据大小
     * http://localhost:9090/person/list?size=1
     * @param pageable
     * @return
     */
    @GetMapping("/person/list")
    public Page<com.ljt.spring.cloud.feign.person.service.provider.entity.Person> list(Pageable pageable){
        return personRepository.findAll(pageable);
    }
}
