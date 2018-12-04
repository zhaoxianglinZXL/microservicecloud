package com.winner.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //声明自己是客户端
public class ConsumerDept80_APP {
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerDept80_APP.class, args);
	}
}
