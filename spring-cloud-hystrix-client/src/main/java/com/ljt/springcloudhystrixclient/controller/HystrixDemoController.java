package com.ljt.springcloudhystrixclient.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
public class HystrixDemoController {// 命令控制器，非前端控制器
    private final static Random random = new Random();
    /**
     *
     * 当{@link #helloWorld()}方法调用超时或失败时，fallback方法{@link #errorContent()}作为替代返回
     * @return
     */
    // 注解方式 参考官方：https://github.com/Netflix/Hystrix/wiki/Configuration
    @GetMapping("hello-world")
    @HystrixCommand(fallbackMethod = "errorContent",commandProperties={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String helloWorld() throws Exception{// 并发性能不高，执行从上到下
        // 如果随机时间 大于100，那么触发容错
        int value = random.nextInt(2000);
        System.out.println("helloWord sleep "+value + " ms");
        Thread.sleep(value);

        return "Hello World";
    }

    // 触发熔断调用
    public String errorContent(){
        return "Fault";
    }
    //------------------------------------------
    /**
     *
     * 当{@link #helloWorld()}方法调用超时或失败时，fallback方法{@link #errorContent()}作为替代返回
     * @return
     */
    @GetMapping("hello-world2")
    public String helloworld2(){
        return new HelloWordCommand().execute();
    }

   /**
     * 编程方式
     */

    private class HelloWordCommand extends com.netflix.hystrix.HystrixCommand<String>{

       protected HelloWordCommand() {
           super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),
                   100); // 执行时间100毫秒
       }

       @Override
       protected String run() throws Exception {
           // 如果随机时间 大于100，那么触发容错
           int value = random.nextInt(200);
           System.out.println("helloWord sleep "+value + " ms");
           Thread.sleep(value);

           return "Hello,World";
       }

       //容错执行
       protected String getFallback(){
           return HystrixDemoController.this.errorContent();
       }
   }

}
