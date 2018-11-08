package com.zx.order.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 集装箱状态
 * 作者：JHJ
 * 日期：2018/10/12 11:16
 * Q Q: 1320666709
 */
public class StatusBean {
    private String cntrId; // 集装箱Id
    private String cntrNo; // 集装箱号
    private String orderType; // 选中项
    private List<StatusBean> statusList;
    private String title;
    private String id;
    private boolean isSelect = false;

    public String getCntrId() {
        return cntrId == null ? "" : cntrId;
    }

    public void setCntrId(String cntrId) {
        this.cntrId = cntrId;
    }

    public String getCntrNo() {
        return cntrNo == null ? "" : cntrNo;
    }

    public void setCntrNo(String cntrNo) {
        this.cntrNo = cntrNo;
    }

    public String getOrderType() {
        return orderType == null ? "" : orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<StatusBean> getStatusList() {
        if (statusList == null) {
            return new ArrayList<>();
        }
        return statusList;
    }

    public void setStatusList(List<StatusBean> statusList) {
        this.statusList = statusList;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
