package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleDao {
    /**
     * 基于用户id查询角色id
     * @param userId
     * @return
     */
    @Select("select role_id from sys_user_roles where user_id=#{userId}")
    List<Integer> selectRoleIdsByUserId(Integer userId);
    /**
     * 新增用户和角色关系数据
     * @param userId
     * @param roleIds
     * @return
     */
     int insertUserRoles(Integer userId, List<Integer> roleIds);

    /**
     * 基于用户id删除用户和角色关系数据
     * @param userId
     * @return
     */
     @Delete("delete from sys_user_roles where user_id=#{userId}")
     int deleteByUserId(Integer userId);
}
