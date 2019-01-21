package com.winner.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//访问github时 因为网络问题可能回启动不成功  
@SpringBootApplication   
public class ConfigClient_3355_StartSpringCloudApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigClient_3355_StartSpringCloudApp.class, args);
	}
}
