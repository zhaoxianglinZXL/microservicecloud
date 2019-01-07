package com.winner.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winner.springcloud.model.DeptInfo;
import com.winner.springcloud.service.DeptClientService;

//Feign通过接口(微服务名称)的方法调用Rest服务（之前是Ribbon+RestTemplate）
//面向微服务编程
@Controller
@RequestMapping("/deptConsumer")
public class DeptController_Consumer {
	
	@Autowired
	private DeptClientService services;
	
	//添加
	@RequestMapping(value = "/addDept")
	@ResponseBody
	public boolean addDept(@RequestBody DeptInfo dept){
		try {
			return services.addDept(dept);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//根据id查询一条部门信息
	@RequestMapping(value="/queryDeptById")
	@ResponseBody
	public DeptInfo queryDeptById(int id){
		return services.queryDeptById(id);
	}

	//查询全部部门信息
	@RequestMapping(value="/queryAll")
	@ResponseBody
	public List<DeptInfo> queryAll(){
		System.out.println("进入了microservicecloud-consumer-dept-feign的全查询方法");
		return services.queryAll();
	}
	
}
