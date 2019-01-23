package com.winner.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ZXL
 * @描述 主启动类
 */
@SpringBootApplication
//声明自己是注册中心的客户端
@EnableEurekaClient //本服务启动后悔自动注册进eureka注册中心中
//声明自己是服务发现客户端
@EnableDiscoveryClient //Enable***Client 申明自己是干什么的 客户端
public class DeptProvider8001_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8001_App.class, args);
	}
}
