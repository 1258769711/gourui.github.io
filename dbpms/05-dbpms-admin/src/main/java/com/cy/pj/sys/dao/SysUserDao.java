package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**定义SysUserDao接口,负责用户数据访问逻辑规范的定义*/
@Mapper
public interface SysUserDao {
    /**
     * 修改密码
     * @param newPassword 新密码
     * @param newSalt 新盐值
     * @param userId 用户id
     * @return
     */
    @Update("update sys_users set password=#{newPassword},salt=#{newSalt} where id=#{userId}")
    int updatePassword(String newPassword,String newSalt,Integer userId);
    /**
     * 基于用户名查询用户信息(登录时需要这个数据)
     * @param username
     * @return
     */
    @Select("select * from sys_users where username=#{username}")
    SysUser selectUserByUsername(String username);

    /**
     * 分页查询用户以及用户对应部门相关信息
     * @param entity 封装了查询条件的对象
     * @return 查询到的用户信息,表中的一行记录映射为内存中一个SysUser对象
     */
    List<SysUser> selectUsers(SysUser entity);

    /**
     * 禁用启用操作
     * @param id
     * @param valid
     * @param modifiedUser
     * @return
     */
    @Update("update sys_users set valid=#{valid},modifiedTime=now(),modifiedUser=#{modifiedUser} where id=#{id}")
    int validById(Integer id,Integer valid,String modifiedUser);

    /**
     * 保存用户自身信息(用户信息中密码要求是已加密的密码)
     * @param entity
     * @return
     */
    int insertUser(SysUser entity);

    /**
     * 基于id查询用户信息
     * @param id
     * @return
     */
    SysUser selectById(Integer id);
    /**
     * 更新用户自身信息(这里的更新不更新密码)
     * @param entity
     * @return
     */
    int updateUser(SysUser entity);

}
