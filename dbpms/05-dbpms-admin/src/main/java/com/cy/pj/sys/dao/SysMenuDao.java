package com.cy.pj.sys.dao;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * 用于操作菜单表的数据访问对象
 */
@Mapper
public interface SysMenuDao {

    /**
     * 基于用户id查询菜单授权标识(permission)
     * @param userId
     * @return
     */
    Set<String> selectUserPermissions(Integer userId);
    /**
     * 查询所有菜单，以及菜单对应的上级菜单名称
     * @return 返回查询到的菜单信息
     */
    List<SysMenu> selectMenus();

    /**
     * 查询树节点信息，在添加或编辑菜单时，
     * 会以树结构方式呈现可选的上级菜单信息
     * @return
     */
    @Select("select id,name,parentId from sys_menus")
    List<Node>selectMenuTreeNodes();

    /**
     * 基于id查询菜单信息
     * @param id
     * @return
     */
    SysMenu selectById(Integer id);

    /**
     * 向数据库表中添加菜单信息
     * @param menu 存储了菜单信息的对象
     * @return 新增的行数
     */
    int insertMenu(SysMenu menu);
    /**
     * 基于id更新菜单信息
     * @param menu
     * @return 更新的行数
     */
    int updateMenu(SysMenu menu);
}
