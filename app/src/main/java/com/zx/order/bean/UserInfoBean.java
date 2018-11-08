package com.zx.order.bean;

/**
 * 用户信息
 * 作者：JHJ
 * 日期：2018/11/1 12:19
 * Q Q: 1320666709
 */
public class UserInfoBean {
    private String realName;
    private String mobile;
    private String imageUrl;

    public String getRealName() {
        return realName == null ? "" : realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
