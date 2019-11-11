package com.railwaymanager.railwaymanager.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: subway_manager
 * @description: 角色
 * @author: lijiwen
 * @create: 2019-09-15 13:24
 **/
@Data
@Accessors(chain = true)
public class Role {
    private String userName;
    private String roleCode;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
