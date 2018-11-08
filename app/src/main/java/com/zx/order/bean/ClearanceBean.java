package com.zx.order.bean;

/**
 * 通关状态
 * 作者：JHJ
 * 日期：2018/10/9 17:13
 * Q Q: 1320666709
 */
public class ClearanceBean {
    private String voyageId;        // 航次ID
    private String voyage;          // 航次
    private long decArriveTime;     // 预计靠泊日期
    private long actualArriveTime;  // 实际靠泊日期
    private String portdestination; // 目的港
    private String pieces;          // 箱量

    public String getVoyageId() {
        return voyageId == null ? "" : voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId;
    }

    public String getVoyage() {
        return voyage == null ? "" : voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public long getDecArriveTime() {
        return decArriveTime;
    }

    public void setDecArriveTime(long decArriveTime) {
        this.decArriveTime = decArriveTime;
    }

    public long getActualArriveTime() {
        return actualArriveTime;
    }

    public void setActualArriveTime(long actualArriveTime) {
        this.actualArriveTime = actualArriveTime;
    }

    public String getPortdestination() {
        return portdestination == null ? "" : portdestination;
    }

    public void setPortdestination(String portdestination) {
        this.portdestination = portdestination;
    }

    public String getPieces() {
        return pieces == null ? "" : pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
