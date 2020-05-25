
服务治理与dubbo不同，dubbo对应服务，Eureka对应应用
Eureka服务器一般不需要自我注册，也不需要注册其他服务器

Eureka本身服务器都没启动，怎么注册？所以这里不需要注册，配置看application.properties

1.consul和Eureka是一样的吗
    提供功能类似，consul功能更强大，广播式服务发现/注册，Eureka一致性更好，consul可用性更好
2.重启eureka服务器，客户端应用要重启吗
    不用，客户端再不停地上报信息。不过在Eureka服务器启动过程中，客户端会大量报错，或者出现数据不一致的情况。
3.生产环境中，consumer是分别注册成多个服务，还是统一放在一起注册成一个服务？权限应该如何处理？
    consumer是否要分为多个服务，要看情况，大多数情况是需要，根据应用职责划分。权限根据服务方法需要，
    比如有些敏感操作的话，可以根据不同用户做鉴权。
4.客户端上报的信息存储在哪里？内存中还是数据库？
    都是在内存里面缓存着,EurekaClient并不是所有的服务，需要的服务。比如：
    Eureka Server管理了200个应用（如：用户模块），每个应用存在100个实例（如：用户模块部署在192.168.0.1-192.168.0.100的机器上），总体管理20000个实例。客户端根据自己的需要的应用实例

5.要是其他模块查询列表里面有用到用户信息怎么办呢？是循环调用用户接口还是直接关联用户表？怎么实现好？
    用户API依赖即可
6.consumer调用Aprovider-a挂了，会自动切换Aprovider-b吗，保证请求可用吗。
    当Aprovider-a挂了，会自动切换到其他Aprovider,不过不一定即时。服务端可能存在脏数据，或者轮询更新时间未到达

7.一个业务中调用多个service时如何保证事务
    需要分布式事务实现，可是一般互联网项目，没有这种昂贵的实现。锁表锁库/JTA(Automic)/消息中间件最终一致性


Eureka高可用
Eureka高可用注册中心集群

运行参数配置--server.port=9091就可以启动第二个服务器

客户端eureka.client.serviceUrl.defaultZone=http://localhost:9090/eureka配置
改为
eureka.client.serviceUrl.defaultZone=http://localhost:9090/eureka,http://localhost:9091/eureka即可
