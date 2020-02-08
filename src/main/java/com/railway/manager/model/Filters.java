package com.railway.manager.model;

import lombok.Data;

/**
 * @program: railway-manager
 * @description:
 * @author: lijiwen
 * @create: 2019-12-28 16:45
 **/
public class Filters {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
