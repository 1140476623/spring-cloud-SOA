配置客户端
1、创建bootstrap.properties或bootstrap.yml的文件
2、在bootstrap.properties配置客户端信息
对应服务端步骤说明在bootstrap.properties
3、设置Endpoints敏感性

1.spring boot业界比较稳定的微服务中间件，不过它使用是易学难精，简单使用容易，扩展很难

2.git在这里扮演什么样的角色，是不是和zookeeper一样
git文件存储方式、分布式的管理系统，spring cloud官方实现基于git,它达到的理念和ZK一样。
为什么不用ZK,不是不能，而是降低学习成本

3.一个DB配置相关的bean用@RefreshScope修饰时，config service修改了db的
配置，比mysql的url，那么这个Bean刷新？如果刷新了是不是获取新的连接的时候url就变了？
做动态替换，实际中，如果发生了配置变更，解决方案是重启Spring Context，可能有关联问题，
如反射获取等，不能做到级联更新，所有重启，负载均衡的时候一个一个关闭重启，@RefreshScope只能做一些动态配置Bean，
如：开关、数据阈值、文案等
A B C
1 1 1

A* B C
0  1 1

A* B C
1  0 1

A* B* C*
1  1  0

A* B* C*
1  1  1