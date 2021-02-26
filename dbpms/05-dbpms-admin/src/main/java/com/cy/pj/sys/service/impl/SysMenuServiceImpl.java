package com.cy.pj.sys.service.impl;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**菜单业务管理*/
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 查询所有菜单。
     * 请思考，菜单数据会经常变化吗，假如每次访问菜单数据都查数据库
     * 是否会对数据库带来一定的访问压力。即便是没有压力，那访问数据库
     * 的性能相对于直接访问内存，是不是会低一些？我们能否将查询到的
     * 数据在缓存放一份，下一次再取时，从数据库取。
     * @return
     */
    @Cacheable("sysMenu") //缓存应用的切入点方法(底层AOP)
    @Override
    public List<SysMenu> findMenus() {
        //从缓存取，缓存有则直接返回
        //假如缓存没有，则去查数据库，然后将查询到结果存储到缓存
        return sysMenuDao.selectMenus();
    }

    @Override
    public SysMenu findById(Integer id) {
        return sysMenuDao.selectById(id);
    }

    @CacheEvict(value="sysMenu",allEntries = true)
    @Override
    public int saveMenu(SysMenu entity) {
        return sysMenuDao.insertMenu(entity);
    }

    /**
     * @CacheEvict注解描述方法时，表示方法是一个清缓存的切入点方法
     * 这里的value属性值表示要清除的缓存，allEntries=true表示要清除当前value
     * 属性指向的cache中所有数据
     * @param entity
     * @return
     */
    @CacheEvict(value="sysMenu",allEntries = true,beforeInvocation = false)
    @Override
    public int updateMenu(SysMenu entity) {
        return sysMenuDao.updateMenu(entity);
    }

    @Override
    public List<Node> findMenuTreeNodes() {
        return sysMenuDao.selectMenuTreeNodes();
    }
}
