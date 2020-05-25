package com.ljt.springcloudsleuth.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class DemoController {
    protected final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    private final RestTemplate restTemplate;

    @Autowired
    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/index")
    public String index(){
        String returnValue = "hello,world";
        logger.info("{} index() : {}",getClass().getSimpleName(),returnValue);
        return returnValue;
    }

    // 添加restTemplate bean并且设置成负载均衡
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //调用链路：spring-cloud-sleuth-->zuul-->person-client-->person-server
    @GetMapping("/to/zuul/person-client/person/find/all")
    public Object toZuul(){
        logger.info("spring-cloud-sleuth#zuul()");// 这里日志DMC会把自己的application-name,traceId和调用经过的所有spanId都展示出来（应用配置了zipkin的）
        // 调用网管zuul
        String url = "http://spring-cloud-zuul:7070/person-client/person/find/all";
        return restTemplate.getForObject(url, Object.class);
    }
}
