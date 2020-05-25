package com.ljt.user.test;

import net.minidev.json.JSONUtil;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestTemplate单元测试
 */
public class RestTemplateTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> data =
                restTemplate.getForObject("http://localhost:8080/env", Map.class);
        System.out.println(data);
        System.out.println(restTemplate.getForObject("http://localhost:8080/env", String.class));
    }
}
