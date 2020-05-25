package com.ljt.springcloudhystrixclient;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//激活熔断保护
@EnableCircuitBreaker // 激活@EnableHystrix+Spring Cloud功能
//@EnableHystrix // 激活Hystrix，没有Spring Cloud功能 如：/hystrix.stream
//@EnableHystrix
public class SpringCloudHystrixClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHystrixClientApplication.class, args);
	}

	// springboot 版本如果是2.0则需要添加 ServletRegistrationBean，springboot的默认路径不是 "/hystrix.stream"，只要在自己的项目里配置上下面的servlet就可以了
	@Bean
	public ServletRegistrationBean getServlet(){
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		//registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/actuator/hystrix.stream");
		//registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}
}
