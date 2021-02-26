package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**用户对象设计及实现*/
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 926757209123294684L;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String mobile;
    private String email;
    private Integer valid=1;//默认为有效状态,0 代表无效
    private Integer deptId;
    private String deptName;
   // private SysDept sysDept;
    /**用户拥有的角色*/
    private List<Integer> roleIds;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
