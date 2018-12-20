package com.winner.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @author ZXL
 * @描述 相当于重写MyRole类
 */
@Configuration
public class MySelfRule 
{
	@Bean
	public IRule myRule()
	{
		//return new RandomRule();//Ribbon默认是轮询，我自定义为随机
		//轮询
		//return new RandomRule();// 轮询
		//返回到自定义的轮询算法的页面
		return new RandomRule_me();//我自定义为每台机器循环5次
	}
	//原始ribbon在github上的位置
	//https://github.com/Netflix/ribbon/blob/master/ribbon-loadbalancer/src/main/java/com/netflix/loadbalancer/RandomRule.java
}
