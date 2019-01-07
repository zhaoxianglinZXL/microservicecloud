package com.winner.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winner.springcloud.dao.DeptInfoDao;
import com.winner.springcloud.model.DeptInfo;
import com.winner.springcloud.service.IDeptInfoService;

@Service("deptInfoService")
public class DeptInfoService implements IDeptInfoService{

	@Autowired
	private DeptInfoDao deptInfoDao;
	
	/** (non-Javadoc)
	 * @see com.winner.springcloud.service.IDeptInfoService#addDept(com.winner.springcloud.model.DeptInfo)
	 * @作者: ZXL
	 * @时间：2018年11月27日
	 * @描述：添加一条部门信息
	 */
	@Override
	public boolean addDept(DeptInfo dept) {
		// TODO Auto-generated method stub
		System.out.println(dept.getDname());
		try {
			deptInfoDao.addDept(dept);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	/** (non-Javadoc)
	 * @see com.winner.springcloud.service.IDeptInfoService#queryDeptById(int)
	 * @作者: ZXL
	 * @时间：2018年11月27日
	 * @描述：根据id查询一条部门信息
	 */
	@Override
	public DeptInfo queryDeptById(int id) {
		// TODO Auto-generated method stub
		return deptInfoDao.queryDeptById(id);
	}

	/** (non-Javadoc)
	 * @see com.winner.springcloud.service.IDeptInfoService#queryAll()
	 * @作者: ZXL
	 * @时间：2018年11月27日
	 * @描述：查询所有的部门信息
	 */
	@Override
	public List<DeptInfo> queryAll() {
		// TODO Auto-generated method stub
		return deptInfoDao.queryAll();
	}

}
