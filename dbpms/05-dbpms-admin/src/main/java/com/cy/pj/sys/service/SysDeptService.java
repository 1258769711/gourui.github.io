package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptService {
	 List<Map<String,Object>> findMenus();
	 List<Node> findDeptTreeNodes();
	 int saveMenu(SysDept entity);
	 int updateMenu(SysDept entity);
}
