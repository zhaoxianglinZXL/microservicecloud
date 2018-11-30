package com.winner.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//申明我是微服务注册中心启动类  EurekaServer服务端启动类，接受其他服务注册进来
@EnableEurekaServer//在主启动类上面，标注的启动该新组件技术的相关注解标签
public class EurekaServer7003_APP {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer7003_APP.class, args);
	}
}
