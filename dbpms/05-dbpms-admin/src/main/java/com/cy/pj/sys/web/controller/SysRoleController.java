package com.cy.pj.sys.web.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.util.PageUtil;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色业务请求与响应控制逻辑对象
 */
@RequestMapping("/role/")
@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取为用户分配角色时,需要的角色id和角色名称
     * @return
     */
    @GetMapping("checkRoles")
    public JsonResult doFindCheckRoles(){
         return new JsonResult(sysRoleService.findCheckRoles());
    }

    /**
     * 基于id查找角色以及角色对应的菜单关系数据
     * @param id
     * @return
     * 访问url:http://localhost/role/50
     */
    @GetMapping("{id}")
    private JsonResult doFindById(@PathVariable  Integer id){
        return new JsonResult(sysRoleService.findById(id));
    }

    @PutMapping
    public JsonResult doUpdateRole(@RequestBody  SysRole entity){
        System.out.println("entity="+entity);
        sysRoleService.updateRole(entity);
        return new JsonResult("update ok");
    }


    /**
     * 执行角色save操作,注意:
     * 1)客户端请求方式,请求url
     * 2)客户端请求参数(格式,类型,请求头,参数名的大小写)
     * 3)服务端接收数据的方式(@RequestBody->接收json数据)
     * //可能出现的问题:404,405,400,415,.....
     * @param entity
     * @return
     */
    @PostMapping
    public JsonResult doSaveRole(@RequestBody SysRole entity){
        sysRoleService.saveRole(entity);
        return new JsonResult("save ok");
    }

    @GetMapping
    public JsonResult doFindRoles(SysRole entity){
        //底层分页查询实现(借助了PageHelper)
        //jdk8之前的写法,现在也可以用
//      return new JsonResult(PageUtil.startPage().doSelectPageInfo(new ISelect() {
//            @Override
//            public void doSelect() {
//                sysRoleService.findRoles(entity);
//            }
//      }));

        //lambda的写法
        return new JsonResult(PageUtil.startPage().doSelectPageInfo(()->{//lambda表达式
            sysRoleService.findRoles(entity);
        }));
    }
}
