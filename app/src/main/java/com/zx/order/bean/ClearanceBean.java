package com.zx.order.bean;

import cn.hutool.core.util.StrUtil;

/**
 * 通关状态
 * 作者：JHJ
 * 日期：2018/10/9 17:13
 * Q Q: 1320666709
 */
public class ClearanceBean {
    private String shipNameContext;
    private String portOfDestination;
    private long estimatedBerthingDate;
    private long actualBerthingDate;
    private String boxNum;

    public String getShipNameContext() {
        return StrUtil.isEmpty(shipNameContext) ? "" : shipNameContext;
    }

    public void setShipNameContext(String shipNameContext) {
        this.shipNameContext = shipNameContext;
    }

    public String getPortOfDestination() {
        return portOfDestination;
    }

    public void setPortOfDestination(String portOfDestination) {
        this.portOfDestination = portOfDestination;
    }

    public long getEstimatedBerthingDate() {
        return estimatedBerthingDate;
    }

    public void setEstimatedBerthingDate(long estimatedBerthingDate) {
        this.estimatedBerthingDate = estimatedBerthingDate;
    }

    public long getActualBerthingDate() {
        return actualBerthingDate;
    }

    public void setActualBerthingDate(long actualBerthingDate) {
        this.actualBerthingDate = actualBerthingDate;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }
}
