package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.sys.pojo.SysRole;

import java.util.List;

/**
 * 定义角色业务规范
 */
public interface SysRoleService {
    /**
     * 基于条件查询角色信息
     * @param entity 封装了查询条件的对象
     * @return 查询到的角色信息
     */
    List<SysRole> findRoles(SysRole entity);

    /**
     * 新增角色以及角色对应的菜单关系数据
     * @param entity 封装了要新增的角色信息
     * @return
     */
    int saveRole(SysRole entity);
    /**
     * 基于角色id查询角色以及角色对应菜单关系数据
     * @param id
     * @return
     */
    SysRole findById(Integer id);

    /**
     * 基于角色id更新角色以及角色对应的菜单关系数据
     * @param entity
     * @return
     */
    int updateRole(SysRole entity);

    /**
     * 为用户授权时，查询可授权的角色
     * @return
     */
    List<CheckBox> findCheckRoles();
}
