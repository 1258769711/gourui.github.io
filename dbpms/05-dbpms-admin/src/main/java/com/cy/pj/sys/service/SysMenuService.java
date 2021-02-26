package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;

import java.util.List;

/**
 * 菜单逻辑业务操作的规范定义
 */
public interface SysMenuService {
    /**查询所有菜单信息以及菜单对应的上级菜单名称*/
    List<SysMenu> findMenus();
    /**基于id查询菜单对象*/
    SysMenu findById(Integer id);
    /**新增菜单信息*/
    int saveMenu(SysMenu entity);
    /**更新菜单信息*/
    int updateMenu(SysMenu entity);
    /**查找菜单树节点信息*/
    List<Node> findMenuTreeNodes();
}
