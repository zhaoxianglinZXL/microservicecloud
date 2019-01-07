package com.winner.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.winner.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient //声明自己是客户端
/**
 *  自定义轮询算法的类 配置细节
 *  官方文档明确给出了警告！
 *  这个自定义配置类不能放在@ComponentScan所扫描的当前包下及子包下
 *  否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享
 *  也就是说我们达不到特殊化定制的目的了
 *  @SpringBootApplication 拥有@ComponentScan
 *  所以不能放在主启动类下面
 */
//再启动该服务的时候就能去加载我们的自定义Ribbon的配置类，从而使配置生效
//name==》 针对于这个微服务使用   configuration==》自定义轮询算法得类
//该出微服务名必须与  eurake注册页面中Application 中对应的名称一样 否则找不到
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class ) //使用这个后 不在使用ribbon自定义的 规则了 
public class ConsumerDept80_APP 
{
	
	public static void main(String[] args) 
	{
		SpringApplication.run(ConsumerDept80_APP.class, args);
	}
}
