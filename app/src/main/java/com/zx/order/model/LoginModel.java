package com.zx.order.model;


import com.zx.order.base.BaseModel;
import com.zx.order.bean.LoginBean;

/**
 * 登录
 * 作者：JHJ
 * 日期：2018/10/31 19:00
 * Q Q: 1320666709
 */
public class LoginModel extends BaseModel {
    private LoginBean data;

    public LoginBean getData() {
        return data;
    }

    public void setData(LoginBean data) {
        this.data = data;
    }
}
