package com.winner.springcloud.config;

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
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
