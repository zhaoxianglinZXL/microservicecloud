package com.winner.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


//实现了 可以访问到提交都爱github上面 的appliction。yml配置文件
@SpringBootApplication
@EnableConfigServer
public class Config_3344_StartSpringCloudApp {
	
	public static void main(String[] args) {
		SpringApplication.run(Config_3344_StartSpringCloudApp.class, args);
	}
}
