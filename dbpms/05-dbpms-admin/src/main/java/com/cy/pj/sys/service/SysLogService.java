package com.cy.pj.sys.service;

import com.cy.pj.sys.pojo.SysLog;

import java.util.List;

public interface SysLogService {
    void saveLog(SysLog sysLog);
    int deleteById(Long...ids);
    SysLog findById(Long id);
    List<SysLog> findLogs(SysLog sysLog);
}
