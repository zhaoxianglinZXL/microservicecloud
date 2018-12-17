package com.winner.springcloud.config;

import java.util.Random;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;

/**
 * @author ZXL
 * @描述 springboot配置类 微服务消费者80
 */
@Configuration
public class ConfigBean {//boot - spring applicationContext.xml --》 @Configuration配置    = ConfigBean = applicationContext.xml 
	
	@Bean
	//测试负载均衡时 完成真正的通过微服务名字从eureka上找到并访问
	//Spring cloud Ribbon 是基于Netflix Ribbon 实现的一套客户端 负载均衡的工具
	@LoadBalanced //开启负载均衡ribbon 直接这个注解就可以了
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	/**
	 * @作者：ZXL
	 * @时间：2018年12月17日 下午6:58:00
	 * @描述：自定义轮询算法
	 */
	@Bean
	public IRule myRule()
	{
		//return new RoundRobinRule();//这个不行 没用 上课写错了的
		
		///轮询
		//return new RoundRobinRule();
		
		//用这个就达到了 随机   不会 自动跳过 死掉的服务
		//return new RandomRule();//达到的目的：用我们重新选择的随机算法替代默认的轮询
		
		//服务提供者 没有死掉 或者 停掉的时候 默认算法轮询 但是 其中一个死掉之后  会自动过滤掉死掉的服务（撞头之后 就是 访问死掉的服务 几次之后）
		return new RetryRule();
	}
	
}
