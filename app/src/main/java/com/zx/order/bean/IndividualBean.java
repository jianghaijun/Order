package com.zx.order.bean;

import java.util.List;

/**
 * 单选预约
 * 作者：JHJ
 * 日期：2018/10/12 14:54
 * Q Q: 1320666709
 */
public class IndividualBean {
    private String containerNo;
    private List<IndividualBean> typeList;
    private String typeName;
    private String imgUrl;
    private int imgPath;
    private long individualTime;

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public List<IndividualBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<IndividualBean> typeList) {
        this.typeList = typeList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgPath() {
        return imgPath;
    }

    public void setImgPath(int imgPath) {
        this.imgPath = imgPath;
    }

    public long getIndividualTime() {
        return individualTime;
    }

    public void setIndividualTime(long individualTime) {
        this.individualTime = individualTime;
    }
}
