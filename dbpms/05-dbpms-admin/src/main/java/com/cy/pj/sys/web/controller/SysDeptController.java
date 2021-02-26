package com.cy.pj.sys.web.controller;

import com.cy.pj.common.pojo.JsonResult;
import com.cy.pj.sys.pojo.SysDept;
import com.cy.pj.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept/")
public class SysDeptController {
	@Autowired
	private SysDeptService sysDeptService;
	@GetMapping
	public JsonResult doFindObjects() {
		return new JsonResult(sysDeptService.findMenus());
	}
	@GetMapping("treeNodes")
	public JsonResult doFindZTreeNodes() {
		return new JsonResult(sysDeptService.findDeptTreeNodes());
	}
	
	@PutMapping
	public JsonResult doUpdateMenu(@RequestBody SysDept entity){
		sysDeptService.updateMenu(entity);
	    return new JsonResult("update ok");
	}
	
	@PostMapping
	public JsonResult doSaveMenu(@RequestBody SysDept entity){
		sysDeptService.saveMenu(entity);
		return new JsonResult("save ok");
	}
}
