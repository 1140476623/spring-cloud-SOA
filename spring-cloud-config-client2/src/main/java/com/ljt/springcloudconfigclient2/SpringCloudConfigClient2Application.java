package com.ljt.springcloudconfigclient2;

import com.ljt.springcloudconfigclient2.indicator.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.ws.BindingType;
import java.util.Set;

@SpringBootApplication
public class SpringCloudConfigClient2Application {
   /* private final ContextRefresher contextRefresher;

    private final Environment environment;

    @Autowired
    public SpringCloudConfigClient2Application(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }*/


    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClient2Application.class, args);
    }

    //定时拉取服务端ljt-prod.properties配置信息，固定五秒钟刷新一次，延迟三秒开始执行，my.name ${value}获取在类上加@RefreshScope更新新数据
    /*@Scheduled(fixedRate = 5 * 1000, initialDelay = 3 * 1000)
    public void autoRefresh(){
        Set<String> updatedPropertiesName = contextRefresher.refresh();
        updatedPropertiesName.forEach(propertyName->{
            System.err.printf("[Thread :%s]当前配置已更新，具体Key：%s , Value : %s \n",
                    Thread.currentThread().getName(), propertyName,environment.getProperty(propertyName));
        });
    }*/

    // 暴露自定义健康，CompositeHealthIndicator才能put该bean,才能聚合起来显示信息
    /*@Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }*/

}
