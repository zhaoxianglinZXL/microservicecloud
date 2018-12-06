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

import com.winner.springcloud.model.DeptInfo;
import com.winner.springcloud.service.IDeptInfoService;

/**
 * @author ZXL
 * @描述 部门操作相关controller
 */

@Controller
@RequestMapping("/dept")
public class DeptInfoController {

	@Autowired
	@Qualifier("deptInfoService")
	private IDeptInfoService ideptInfoService;
	
	@Autowired
	private DiscoveryClient client;//暴露服务
	
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
	
	//根据id查询一条部门信息
	@RequestMapping(value="/queryDeptById",method = RequestMethod.GET)
	@ResponseBody
	public DeptInfo queryDeptById(int id){
		return ideptInfoService.queryDeptById(id);
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
