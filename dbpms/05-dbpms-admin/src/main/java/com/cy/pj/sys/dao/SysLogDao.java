package com.cy.pj.sys.dao;

import com.cy.pj.sys.pojo.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysLogDao {
    int insertLog(SysLog entity);
    int deleteById(Long...ids);
    SysLog selectById(Long id);
    List<SysLog> selectLogs(SysLog sysLog);
}
