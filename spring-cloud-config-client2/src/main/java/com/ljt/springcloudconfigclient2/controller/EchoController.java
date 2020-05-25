package com.ljt.springcloudconfigclient2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 实时读取配置文件中属性值
 */
@RestController
//@RefreshScope指定返回刷新，服务端ljt-prod.properties修改了，服务端env访问变了，
// 客户端加了application.properties配置了endpoints.refresh.sensitive=false，
// 页面post请求调用http://localhost:8080/refresh让客户端再次获取一次，env也变了，
// 下面的请求方法获得的数还是没变，是因为容器中bean没有更新，
// 加这个注解表示那个bean的数据要刷新（beans容器不可能全局刷新，所以要加注解指定刷新区域）
// bean没有这个注解变化了会出问题
@RefreshScope
public class EchoController {

    @Value("${my.name}")
    private String myName;

    //http://localhost:8080/my-name
    @GetMapping("/my-name")
    public String getName(){
        return myName;
    }
}
