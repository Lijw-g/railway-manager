package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @program: railway_manager
 * @description: 用户类
 * @author: lijiwen
 * @create: 2019-09-15 07:30
 **/
@Data
@Accessors(chain = true)
public class User {
    private String name;
    private String displayName;
    private String password;
    private String secondPwd;
    private Date lastLoginTime;
    /**
     * 关联属性 1
     */
    private List<String> roleCode;
    private Integer id;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;


}
