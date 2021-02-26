package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public int updatePassword(String sourcePassword,
                              String newPassword,String confirmPassword) {
        //1.参数校验
        if(sourcePassword==null||"".equals(sourcePassword))
            throw new IllegalArgumentException("原密码不能为空");
        if(newPassword==null||"".equals(newPassword))
            throw new IllegalArgumentException("新密码不能为空");
        if(!newPassword.equals(confirmPassword))
            throw new IllegalArgumentException("两次输入的新密码不一致");
        //获取登录用户,并判定登录用户的密码和用户输入的原密码是否一致
        SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
        SimpleHash sh=new SimpleHash("MD5",
                sourcePassword,user.getSalt(),1);
        if(!user.getPassword().equals(sh.toHex()))//MD5加密，相同内容加密结果也相同
            throw new IllegalArgumentException("原密码不正确");
        //2.对新密码进行加密
        String newSalt=UUID.randomUUID().toString();
        sh=new SimpleHash("MD5",
                newPassword,newSalt,1);//shiro api
        String newHashedPassword=sh.toHex();//已加密的密码
        //3.更新用户密码
        int rows=sysUserDao.updatePassword(newHashedPassword,newSalt,user.getId());
        return rows;
    }

    @Override
    public List<SysUser> findUsers(SysUser entity) {
//        return PageUtil.startPage().doSelectPageInfo(()->{
//            sysUserDao.selectUsers(entity);
//        });
         return sysUserDao.selectUsers(entity);
    }

    @Override
    public SysUser findById(Integer id) {
        //查询用户以及用户对应的部门信息
        SysUser user=sysUserDao.selectById(id);
        if(user==null)
            throw new ServiceException("没有找到对应的用户");
        //查询用户对应的角色信息
        List<Integer> roleIds=sysUserRoleDao.selectRoleIdsByUserId(id);
        //将查询到的角色id封装到user对象
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public int saveUser(SysUser entity) {
        //1.保存用户自身信息
        //1.1对参数进行校验
        //1.2对密码进行加密(MD5盐值加密-MD5特点(不可逆,相同内容加密结果也相同))
        String password=entity.getPassword();
        String salt= UUID.randomUUID().toString();//随机字符串
        SimpleHash simpleHash= new SimpleHash(
                "MD5",password,salt,1);
        password=simpleHash.toHex();//将加密结果转换为16进制字符串(建议)
        entity.setSalt(salt);
        entity.setPassword(password);
        //1.3将用户信息持久化到数据库
        int rows=sysUserDao.insertUser(entity);
        //2.保存用户和角色关系数据
        sysUserRoleDao.insertUserRoles(entity.getId(),entity.getRoleIds());
        return rows;
    }
    @Override
    public int updateUser(SysUser entity) {
        //1.更新用户自身信息
        int rows=sysUserDao.updateUser(entity);
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        //2.更新用户和角色关系数据
        //2.1删除原有用户和角色关系数据
        sysUserRoleDao.deleteByUserId(entity.getId());
        //2.2添加新的用户和角色关系数据
        sysUserRoleDao.insertUserRoles(entity.getId(),entity.getRoleIds());
        return rows;
    }


    @Override
    public int validById(Integer id, Integer valid) {
        int rows=sysUserDao.validById(id,valid,"admin");//这里的admin为假数据,后续为登录用户
        if(rows==0)
            throw new ServiceException("记录可能不存在了");
        return rows;
    }
}
