package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 3232155697809432339L;
    /**角色id*/
    private Integer id;
    /**角色名称*/
    private String name;
    /**菜单id*/
    private List<Integer> menuIds;
    /**备注*/
    private String remark;
    /**创建时间*/
    //@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    /**修改时间*/
    //@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedTime;
    /**创建用户*/
    private String createdUser;
    /**修改用户*/
    private String modifiedUser;
}
