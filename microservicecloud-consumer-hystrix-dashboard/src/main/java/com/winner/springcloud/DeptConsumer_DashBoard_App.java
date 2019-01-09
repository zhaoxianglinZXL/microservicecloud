package com.winner.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//开启 仪表盘图形化Dashboard的注解
//所有Provider微服务提供类（8001、8002、8003）都需要监控依赖配置
//在provider中 添加actuator 这个 才能监控到 之前解决主页INFO时已经添加了 现在不用管了
//访问路径 http://localhost:9001/hystrix 在配置文件中配置 spring项目的访问名hystrix
@EnableHystrixDashboard
public class DeptConsumer_DashBoard_App {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
	}
}
