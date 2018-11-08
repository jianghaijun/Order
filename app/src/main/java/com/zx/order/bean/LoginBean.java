package com.zx.order.bean;

/**
 * 登录
 * 作者：JHJ
 * 日期：2018/10/31 19:00
 * Q Q: 1320666709
 */
public class LoginBean {
    private String token;
    private UserInfoBean userInfo;

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
}
