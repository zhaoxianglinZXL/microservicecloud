package com.winner.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.winner.springcloud.model.DeptInfo;

/**
 * @author ZXL
 * @描述 部门类消费者  只负责消费 不需要负责生产
 */
@Controller
@RequestMapping("/deptConsumer")
public class DeptController_Consumer {
	
	private static final String REST_URL_PREFIX = "http://localhost:8001";
	
	/**
	 * 使用
	 *  使用resttemplate访问restful接口非常的简单粗暴无脑
	 *  （url,requestMap,ResponseBean.class）这三个参数分别代表rest请求地址 、请求参数、http响应转换被转换成的对象类型
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping(value="/addDeptInfo",method = RequestMethod.POST)
	@ResponseBody
	//添加一条部门信息
	public boolean addDeptInfo(DeptInfo dept){
		System.out.println(dept);
		System.out.println(dept.getDname());
		return restTemplate.postForObject(REST_URL_PREFIX+"/dept/addDept", dept, boolean.class);
	}
	
	@RequestMapping(value="/queryDeptById",method = RequestMethod.GET)
	@ResponseBody
	//查询一条部门信息
	public DeptInfo queryDeptById(int id ){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/queryDeptById?id="+id, DeptInfo.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/queryAll",method = RequestMethod.GET)
	@ResponseBody
	public List<DeptInfo> queryAll(){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/queryAll", List.class);
	}
	
}
