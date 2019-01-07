package com.winner.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
/**
 * @author ZXL
 * @描述 依旧轮询策略，但是加上新需求，每隔服务器要求被调用5次，也即以前是每台机器一次
 *     ，现在每台机器5次
 */
public class RandomRule_me extends AbstractLoadBalancerRule
{

	
	//total = 0 ； //当total == 5以后 我们的指针才会往下走
	//index= 0 ;// 当前对外提供服务的服务器地址
	//total需要重新赋值为0 但是已经到达过一个5次 ， 我们的index = 1
	// 分析：我们只有三台服务器 ， total = 15 这个时候我们该选择那台服务器
	
	private int total = 0;//总共被调用的次数，目前要求每台被调用5次
	private int currentIndex = 0;// 当前提供服务的机器号
	//返回具体服务的哪一个服务器
	//重写 IRULE算法的时候 真正其中用的是这一个类
	
	/* ILoadBalancer 负载平衡器;负载均衡策略的抽象类，在该抽象类中定义了负载均衡器
	 * ILoadBalancer对象，该对象能够在具体实现选择服务策略时，获取到一些负载均衡器中
	 * 一些维护的信息来作为分配的依据，并以此设计一些算法来实现针对特定场景的高效率策略。
	 */
	public Server choose(ILoadBalancer lb , Object key)
	{
		System.out.println("进入了自定义算法");
		if(lb == null)
		{
			return null;
		}
		
		Server server = null;
		
		while(server == null){
			
			if(Thread.interrupted()){
				return null;
			}
			
			List<Server> upList = lb.getReachableServers();//
			for(Server s : upList){
				System.out.println(s+"===lb.getReachableServers();");
			}
			List<Server> allList = lb.getAllServers();//得到所有的服务
			for(Server s : allList){
				System.out.println(s+"===lb.getAllServers();");
			}
			
			
			int serverCount = allList.size();
			if (serverCount == 0) 
			{
				/*
				 * No servers. End regardless of pass, because subsequent passes only get more
				 * 没有服务器。结束不管传递，因为后续传递只会得到更多
				 * restrictive. 限制性的
				 */
				return null;
			}
			
			if( total < 5 )
			{
				server = upList.get(currentIndex);//通过下标得到服务
				total ++;
			}else{
				
				currentIndex++; 
				total = 0;
				
				if(currentIndex >= allList.size()){
					currentIndex = 0 ; 
				}
			}
			
			if (server == null) {
				/*
				 * The only time this should happen is if the server list were somehow trimmed.
				 * This is a transient condition. Retry after yielding.
				 */
				/*  Thread.yield();
				 *  Java线程中的Thread.yield( )方法，译为线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
				 *	让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。
				 *	yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
				 *	证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
				 */
				Thread.yield();
				continue;//跳过本次循环进行下次循环
			}

			if (server.isAlive()) {
				return (server);
			}

			// Shouldn't actually happen.. but must be transient or a bug.
			server = null;
			Thread.yield();
		}
		
		return server;
		
	}
	
	@Override
	public Server choose(Object arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initWithNiwsConfig(IClientConfig arg0) 
    {
		// TODO Auto-generated method stub
		
	}

}
