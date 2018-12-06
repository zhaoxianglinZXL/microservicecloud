package com.winner.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	/*//  没使用负载均衡的时候这么使用
	 * //@Value("${REST_URL_PREFIX}") //无法取static 静态变量的值
	private static String REST_URL_PREFIX;
	
	
	@Value("${REST_URL_PREFIX}")//静态变量必须使用set方法进行赋值
	public void setREST_URL_PREFIX(String rEST_URL_PREFIX) {
		REST_URL_PREFIX = rEST_URL_PREFIX;
	}*/
	
	//Spring Cloud 封装了 Nefix公司开发的Eureka 模块来实现服务注册与发现（请对比Zookeeper）
	//当服务者在只有一个时
	//使用负载均衡之后这么使用 他应该去访问的是微服务的名称  消费者对外暴露的服务
	//private static String REST_URL_PREFIX = "http://microservicecloud-dept";
	
	
	//Spring Cloud 封装了 Nefix公司开发的Eureka 模块来实现服务注册与发现（请对比Zookeeper）
	//即使是多个微服务的情况下 多个微服务 对外暴露的微服务名还是没有更改的
	private static String REST_URL_PREFIX = "http://microservicecloud-dept";
	@Value("${server.port}")
	private String port;
	
	@Value("${restUrlSuffix}")
	private String restUrlSuffix;
	
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
	
	@SuppressWarnings("unchecked")//抛出的异常不用管它
	@RequestMapping(value="/queryAll",method = RequestMethod.GET)
	@ResponseBody
	public List<DeptInfo> queryAll(){
		System.out.println(REST_URL_PREFIX);
		System.out.println(port);
		System.out.println(restUrlSuffix);
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/queryAll", List.class);
	}
	
	//消费者调用服务的 服务发现
	@RequestMapping(value="/discovery",method = RequestMethod.GET)
	@ResponseBody
	public Object discovery(){
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}
	
}
