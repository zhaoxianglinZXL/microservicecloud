package com.winner.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient //声明自己是客户端
@EnableFeignClients(basePackages={ "com.winner.springcloud"})//申明自己是FEIGN //扫描包  数据形式，就意味着可以传多个包名
@ComponentScan("com.winner.springcloud")
public class ConsumerDept80_Feign_APP 
{
	
	public static void main(String[] args) 
	{
		SpringApplication.run(ConsumerDept80_Feign_APP.class, args);
	}
}
