package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    /**关联角色表dao对象*/
    @Autowired
    private SysRoleDao sysRoleDao;

    /**关联角色菜单关系表dao对象*/
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

   // @RequiresPermissions("sys:role:view")
    @Override
    public List<SysRole> findRoles(SysRole entity) {
        return sysRoleDao.selectRoles(entity);
    }

    /**
     * 保存角色以及角色菜单关系数据(思考如何保证数据的完整性)
     * @param entity 封装了要新增的角色信息
     * @return
     */
    @Override
    public int saveRole(SysRole entity) {
        //保存角色自身信息
        int rows=sysRoleDao.insertRole(entity);//sqlSession
        //保存角色菜单关系数据
        sysRoleMenuDao.insertRoleMenus(entity.getId(),entity.getMenuIds());
        return rows;
    }
    @Override
    public SysRole findById(Integer id) {
        return sysRoleDao.selectById(id);
    }

    @Override
    public int updateRole(SysRole entity) {
        //更新角色自身信息
        int rows=sysRoleDao.updateRole(entity);
        if(rows==0)throw new ServiceException("记录可能已经不存在了");
        //更新角色菜单关系数据(one2many数据更新一般是先删除原有关系，再添加新的关系)
        sysRoleMenuDao.deleteByRoleId(entity.getId());
        sysRoleMenuDao.insertRoleMenus(entity.getId(),entity.getMenuIds());
        return rows;
    }

    @Override
    public List<CheckBox> findCheckRoles() {
        return sysRoleDao.selectCheckRoles();
    }
}
