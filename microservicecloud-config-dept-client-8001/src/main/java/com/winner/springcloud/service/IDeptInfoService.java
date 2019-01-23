package com.winner.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.winner.springcloud.model.DeptInfo;

public interface IDeptInfoService {
	
	//添加
	public boolean addDept(DeptInfo dept);
	
	//根据id查询一条部门信息
	public DeptInfo queryDeptById(int id);

	//查询全部部门信息
	public List<DeptInfo> queryAll();
}
