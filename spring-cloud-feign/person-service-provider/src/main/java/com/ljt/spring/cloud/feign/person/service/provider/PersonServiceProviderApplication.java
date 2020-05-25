package com.ljt.spring.cloud.feign.person.service.provider;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *  Person提供者应用程序
 */
@SpringBootApplication
@EnableEurekaClient // 激活注册中心
@EnableHystrix // 激活熔断
@EnableTransactionManagement(proxyTargetClass = true)// 是否用实体类作为事务代理
public class PersonServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceProviderApplication.class,args);
    }
}
