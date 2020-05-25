package com.ljt.springcloudconfigclient2.indicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * 自定义实现健康
 *
 * 实现接口，入口类暴露@bean该类，配置#全局关闭Actuator安全
 * management.security.enabled=false
 * 页面访问http://localhost:8080/health
 * {"status":"UP",
 * "my":{"status":"UP","MyHealthIndicator":"Day day up"},
 * "diskSpace":{"status":"UP","total":403396620288,"free":185691303936,"threshold":10485760},
 * "refreshScope":{"status":"UP"},"configServer":{"status":"UP",
 * "propertySources":["configClient","file:///D:/gitspringcloud/springcloud-2/config/ljt-prod.properties",
 * "file:///D:/gitspringcloud/springcloud-2/config/ljt.properties"]}}
 *
 * my diskSpace refreshScope propertySources file是聚合关系，只要有一个down挂了，status的值就是down的
 */
// 单机属性展示，单联的，与集群不同
public class MyHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("MyHealthIndicator","Day day up");
        //builder.down().withDetail("MyHealthIndicator","Down");
    }
}
