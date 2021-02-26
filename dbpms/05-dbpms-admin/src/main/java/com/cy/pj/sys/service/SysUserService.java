package com.cy.pj.sys.service;

import com.cy.pj.sys.pojo.SysUser;

import java.util.List;

/**
 * 定义用户业务接口,负责用户业务逻辑规范定义
 */
public interface SysUserService {
    /**
     * 修改密码
     * @param sourcePassword 原密码
     * @param newPassword 新密码
     * @param confirmPassword 密码确认
     * @return 修改的行数
     */
    int updatePassword(String sourcePassword,
                       String newPassword,String confirmPassword);
    /**
     * 基于条件查询用户信息
     * @param entity
     * @return
     */
    List<SysUser> findUsers(SysUser entity);

    /**
     * 基于id查询用户信息,用户对应的部门信息,用户对应的角色信息
     * @param id
     * @return
     */
    SysUser findById(Integer id);

    /**
     * 添加新的用户信息以及用户和角色关系数据
     * @param entity
     * @return
     */
    int saveUser(SysUser entity);

    /**
     * 更新用户信息以及用户和角色关系数据
     * @param entity
     * @return
     */
    int updateUser(SysUser entity);

    /**
     * 基于用户id修改用户状态(禁用,启用)
     * @param id 用户id
     * @param valid 用户状态
     * @return 更新的行数
     */
    int validById(Integer id,Integer valid);

}
