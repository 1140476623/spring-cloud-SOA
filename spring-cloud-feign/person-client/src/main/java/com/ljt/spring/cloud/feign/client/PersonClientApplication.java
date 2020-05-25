package com.ljt.spring.cloud.feign.client;

import com.ljt.spring.cloud.feign.api.service.PersonService;
import com.ljt.spring.cloud.feign.client.ribbon.ReturnFirstForeverLoadBalanceRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Person Client 应用程序
 */
@SpringBootApplication
@EnableEurekaClient // 激活注册中心
@EnableFeignClients(clients = PersonService.class) // 激活Fegin客户端,这里传入PersonService.class相当于申明bean，这样web里面就可以用了
//不加@RibbonClient，否则会重复加载负载均衡
//@RibbonClient(value = "person-service",configuration = PersonClientApplication.class) // 启用自定义负载均衡策略
@EnableHystrix // 激活熔断
public class PersonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonClientApplication.class,args);
    }

    // 自定义负载均衡
    /*@Bean
    public ReturnFirstForeverLoadBalanceRule getReturnFirstForeverLoadBalanceRule(){
        return new ReturnFirstForeverLoadBalanceRule();
    }*/

    // 选择均衡负载策略
   /* @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }*/
}
