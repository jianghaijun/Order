package com.zx.order.bean;

import java.util.List;

/**
 * 集装箱状态
 * 作者：JHJ
 * 日期：2018/10/12 11:16
 * Q Q: 1320666709
 */
public class StatusBean {
    private String containerNo;
    private List<StatusBean> statusList;
    private String title;
    private boolean isSelect = false;

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public List<StatusBean> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusBean> statusList) {
        this.statusList = statusList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
