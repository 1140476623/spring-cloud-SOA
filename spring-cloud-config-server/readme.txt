构建spring cloud配置服务器
实现步骤
1.在Configuration  Class标记@EnableConfigServer
    Configuration  Class是配置类的class，启动类也是配置类
2.配置文件目录（基于git，D://gitspringcloud下） spring的特性，不是springboot/cloud的
    1.ljt.properties(默认)//默认环境，跟着代码仓库走
    2.ljt-dev.properties(profile = "dev")//开发环境
    2.ljt-test.properties(profile = "test")//测试环境
    3.ljt-staging.properties(profile = "staging")//预发布环境
    4.ljt-prod.properties(profile = "prod")//生产环境

3.服务端配置版本仓库（本地）--创建git仓库，把上面的文件添加到仓库，并提交到本地仓库，最好不用config文件，springboot会默认加载config下的XXX.properties
spring.cloud.config.server.git.uri = file:///D:/gitspringcloud/springcloud-2/config

4.启动页面能访问http://localhost:9090/ljt-dev.properties返回的是文件中的内容即可，几个文件都能访问