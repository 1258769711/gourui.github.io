package com.cy.pj.sys.dao;

import com.cy.pj.common.pojo.CheckBox;
import com.cy.pj.sys.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**创建角色数据逻辑访问对象，基于此对象访问数据库中角色相关数据*/
@Mapper
public interface SysRoleDao {
    /**
     * 基于条件查询角色信息
     * @param entity 封装了查询条件的对象
     * @return 查询到的结果
     */
    List<SysRole> selectRoles(SysRole entity);
    /**
     * 基于id查找对应的角色以及角色对应的菜单信息
     * @param id 角色id
     * @return 查找到具体角色对象
     */
    SysRole selectById(Integer id);

    /**
     * 向表中新增一条角色信息
     * @param entity 封装了要新增的角色信息的对象
     * @return 新增的行数
     */
    int insertRole(SysRole entity);
    /**
     * 基于角色id更新角色信息
     * @param entity 封装了要新增的角色信息的对象
     * @return 更新的行数
     */
    int updateRole(SysRole entity);

    /**
     * 查询角色id和角色name，
     * 每行记录封装为一个CheckBox对象，
     * 后续在为用户分配角色时会用到。
     * @return
     */
    @Select("select id,name from sys_roles")
    List<CheckBox> selectCheckRoles();
}
