package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**基于此对象存储内存中菜单信息
 * java中所有用于存储数据的对象，都建议实现序列化接口，并提供序列化id
 * 标准：
 * 对象序列化：将对象转换为字节 (其目的是便于网络传输或存储)
 * 反序列化：将字节转换为对象
 * 行业扩展：
 * JSON对象序列化：将对象转换JSON字符串
 * JSON对象反序列化：将json字符串转换为对象
 * */
@Data
public class SysMenu implements Serializable {
    /**建议手动生成序列化id，唯一标识*/
    private static final long serialVersionUID = 5531553051566760402L;//ObjectOutputStream
    /**菜单id*/
    private Integer id;
    /**菜单名称*/
    private String name;
    /**菜单类型*/
    private Integer type;
    /**菜单的url(通过此url访问具体资源)*/
    private String url;
    /**上级菜单id*/
    private Integer parentId;
    /**上级菜单名称*/
    private String parentName;
    /**菜单排序号*/
    private Integer sort;
    /**菜单授权标识*/
    private String permission;//null
    /**备注*/
    private String remark;
    /**创建时间*/
    private Date createdTime;
    /**修改时间*/
    private Date modifiedTime;
    /**创建用户*/
    private String createdUser;
    /**修改用户*/
    private String modifiedUser;
}
