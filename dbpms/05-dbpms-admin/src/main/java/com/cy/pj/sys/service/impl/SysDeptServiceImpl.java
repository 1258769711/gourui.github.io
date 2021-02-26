package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.pojo.SysDept;
import com.cy.pj.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptDao sysDeptDao;

	@Override
	public List<Map<String, Object>> findMenus() {
		List<Map<String, Object>> list=
		sysDeptDao.selectDepts();
		if(list==null||list.size()==0)
		throw new ServiceException("没有部门信息");
		return list;
	}
	@Override
	public List<Node> findDeptTreeNodes() {
		List<Node> list=
		sysDeptDao.selectDeptTreeNodes();
		if(list==null||list.size()==0)
		throw new ServiceException("没有部门信息");
		return list;
	}
	@Override
	public int updateMenu(SysDept entity) {
		//1.合法验证
		if(entity==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("部门不能为空");
		int rows;
		//2.更新数据
		try{
		rows=sysDeptDao.updateMenu(entity);
		}catch(Exception e){
		e.printStackTrace();
		throw new ServiceException("更新失败");
		}
		//3.返回数据
		return rows;
	}
	
	@Override
	public int saveMenu(SysDept entity) {
		//1.合法验证
		if(entity==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("部门不能为空");
		//2.保存数据
		int rows=sysDeptDao.insertMenu(entity);
		//if(rows==1)
		//throw new ServiceException("save error");
		//3.返回数据
		return rows;
	}



}
