package com.cy.pj.sys.web.controller;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.common.util.PageUtil;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.github.pagehelper.ISelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//restful 风格url(一种编码风格)
//@GetMapping 所有-->/log/
//@GetMapping 某个-->/log/{id}
//@DeleteMapping 某些-->/log/{id}

@CrossOrigin
@RequestMapping("/log/")
@RestController
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @DeleteMapping("{id}")
    public JsonResult doDeleteById(@PathVariable Long... id){
        sysLogService.deleteById(id);
        return new JsonResult("delete ok");
    }

    @GetMapping("{id}")
    public JsonResult doFindById(@PathVariable Long id){
       return new JsonResult(sysLogService.findById(id));
    }

    @GetMapping
    public JsonResult doFindLogs(SysLog sysLog){
       // try{Thread.sleep(5000);}catch (Exception e){};
//        return new JsonResult(PageUtil.startPage().doSelectPageInfo(()->
//                sysLogService.findLogs(sysLog)
//        ));//lambda (jdk8 中特性)
      return new JsonResult(PageUtil.startPage().doSelectPageInfo(
             new ISelect() {
               @Override
               public void doSelect() {
                sysLogService.findLogs(sysLog);
               }
       }));
    }
}