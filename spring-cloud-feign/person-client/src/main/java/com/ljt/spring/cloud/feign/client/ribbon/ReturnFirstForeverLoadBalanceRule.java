package com.ljt.spring.cloud.feign.client.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义负载均衡规则
 *  {@link IRule} 实现该接口
 */
public class ReturnFirstForeverLoadBalanceRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        Server server = null;
        ILoadBalancer loadBalancer = getLoadBalancer();

        System.out.println("使用了自定义负载均衡策略");

        // 返回application.perperties配置的server即： person-service.ribbon.listOfServers
        List<Server> allServers = loadBalancer.getAllServers();
        return allServers.get(0);// 永远返回第一台
    }

}
