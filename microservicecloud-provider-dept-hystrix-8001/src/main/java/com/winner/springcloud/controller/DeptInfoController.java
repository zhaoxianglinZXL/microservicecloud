package com.winner.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.winner.springcloud.model.DeptInfo;
import com.winner.springcloud.service.IDeptInfoService;

/**
 * @author ZXL
 * @描述 部门操作相关controller
 * @hystrix 我的理解： 服务熔断  即访问服务报错了 这个时候这个服务就会暂用我们的系统资源 为了能够在程序报错时能够给我们返回一个符合预期的 我们想要的结果
 */
@Controller
@RequestMapping("/dept")
public class DeptInfoController {

	@Autowired
	@Qualifier("deptInfoService")
	private IDeptInfoService ideptInfoService;
	
	@Autowired
	private DiscoveryClient client;//暴露服务
	
	/**
	 * @作者：ZXL
	 * @时间：2019年1月4日 下午5:07:09
	 * @return
	 * @描述：根据id查询一条部门信息
	 * @描述：主要靠queryDeptById 以及 processHystrix_Get这两个方法进行测试 Hystrix(初步测试)
	 */
	@RequestMapping(value="/queryDeptById",method = RequestMethod.GET)
	@ResponseBody
	//调用本方法报错时 将会调用 fallbackMethod 对应的方法
	@HystrixCommand(fallbackMethod = "processHystrix_Get") //报异常后如何处理
	public DeptInfo queryDeptById(int id){
		System.out.println("进来了");
		DeptInfo dept = ideptInfoService.queryDeptById(id);
		System.out.println("进来了");
		if(dept == null){
			throw new RuntimeException("该ID: " +id+ "没有定应的信息");
		}
		return dept;
	}

	public DeptInfo processHystrix_Get(int id){
		DeptInfo dept = new DeptInfo();
		//dept.setDeptno(id);
		dept.setDname("该ID: " +id+ "没有定应的信息,null-@HystrixCommand");
		dept.setDb_source("no this database in MySQL");
		return dept;
	}
	
	//添加
	@RequestMapping(value = "/addDept",method = RequestMethod.POST)
	@ResponseBody
	public boolean addDept(@RequestBody DeptInfo dept){
		try {
			return ideptInfoService.addDept(dept);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	

	//查询全部部门信息
	@RequestMapping(value="/queryAll",method = RequestMethod.GET)
	@ResponseBody
	public List<DeptInfo> queryAll(){
		System.out.println("进入了");
		return ideptInfoService.queryAll();
	}
	
	/**
	 * @作者：ZXL
	 * @时间：2018年11月30日 上午10:01:30
	 * @parameter
	 * @return
	 * @描述：循环出所有的微服务
	 */
	@RequestMapping(value="/discovery",method = RequestMethod.GET)
	@ResponseBody
	public Object discovery(){
		List<String> list = client.getServices();
		System.out.println("************"+list);
		
		List<ServiceInstance> serverList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for(ServiceInstance element : serverList){
			// 主机名  -- 地址  -- 端口
			System.out.println(element.getServiceId() +"\t"+ element.getHost() +"\t"+ element.getPort() 
					+"\t"+ element.getUri() );
		}
		return this.client;
	}
}
