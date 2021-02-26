package com.cy.pj.sys.web.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.sys.pojo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/menu/")
@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PutMapping
    public JsonResult doUpdateMenu(@RequestBody SysMenu menu){
        sysMenuService.updateMenu(menu);
        return new JsonResult("Update Ok");
    }
    @GetMapping("{id}")
    public JsonResult doFindById(@PathVariable Integer id){
        return new JsonResult(sysMenuService.findById(id));
    }
    @PostMapping
    public JsonResult doSaveMenu(@RequestBody SysMenu menu){
         sysMenuService.saveMenu(menu);
         return new JsonResult("Save OK");
    }
    @GetMapping("treeNodes")
    public JsonResult doFindMenuTreeNodes(){
        return new JsonResult(sysMenuService.findMenuTreeNodes());
    }

    @GetMapping
    public JsonResult doFindMenus(){
        return new JsonResult(sysMenuService.findMenus());
    }
}
