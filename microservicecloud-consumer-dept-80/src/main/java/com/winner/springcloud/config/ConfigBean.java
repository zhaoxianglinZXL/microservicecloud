package com.winner.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZXL
 * @描述 springboot配置类 微服务消费者80
 */
@Configuration
public class ConfigBean {
	
	@Bean
	//测试负载均衡时 完成真正的通过微服务名字从eureka上找到并访问
	@LoadBalanced //开启负载均衡ribbon 直接这个注解就可以了
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
