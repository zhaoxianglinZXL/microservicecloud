package com.winner.springCloud.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 读自己配置文件的相应信息  从github上面获取
 */
@Controller
@RequestMapping("configClientRest")
public class ConfigClientRest {

	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${eureka.client.service-url.defaultZone}")
	private String eurekaServers;

	@Value("${server.port}")
	private String port;

	@RequestMapping("/config")
	@ResponseBody
	public String getConfig()
	{
		String str = "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
		System.out.println("******str: " + str);
		return "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
	}
}
