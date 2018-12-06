package com.winner.springcloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.winner.springcloud.model.DeptInfo;

/**
 * @author ZXL
 * @描述 部门dao层
 */

/**
 * 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
 * 
 * 需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
 * 也就是说这个接口是不支持重载的
 */
@Mapper
public interface DeptInfoDao {//这是一个 通过注解实现增删改查的dao层
	
	//添加一条部门信息
	public void addDept(@Param("dept") DeptInfo dept);
	
	//根据id查询一条部门信息
	public DeptInfo queryDeptById(int id);

	//查询全部部门信息
	public List<DeptInfo> queryAll();

}
