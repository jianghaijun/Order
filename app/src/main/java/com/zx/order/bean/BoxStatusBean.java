package com.zx.order.bean;

/**
 * 箱状态
 * 作者：JHJ
 * 日期：2018/10/10 11:32
 * Q Q: 1320666709
 */
public class BoxStatusBean {
    private String cntrNo;      // 集装箱号
    private String outTruckNo;  // 提箱车号
    private long retDate;       // 返箱日期
    private long inCntrTime;    // 箱进场日期
    private long arrivalTime;   // 运抵发送时间
    private long vehicleDateOfSuitcase; // 提箱车辆进场时间
    private long outCntrTime;   // 箱出场时间

    public String getCntrNo() {
        return cntrNo == null ? "" : cntrNo;
    }

    public void setCntrNo(String cntrNo) {
        this.cntrNo = cntrNo;
    }

    public String getOutTruckNo() {
        return outTruckNo == null ? "" : outTruckNo;
    }

    public void setOutTruckNo(String outTruckNo) {
        this.outTruckNo = outTruckNo;
    }

    public long getRetDate() {
        return retDate;
    }

    public void setRetDate(long retDate) {
        this.retDate = retDate;
    }

    public long getInCntrTime() {
        return inCntrTime;
    }

    public void setInCntrTime(long inCntrTime) {
        this.inCntrTime = inCntrTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getVehicleDateOfSuitcase() {
        return vehicleDateOfSuitcase;
    }

    public void setVehicleDateOfSuitcase(long vehicleDateOfSuitcase) {
        this.vehicleDateOfSuitcase = vehicleDateOfSuitcase;
    }

    public long getOutCntrTime() {
        return outCntrTime;
    }

    public void setOutCntrTime(long outCntrTime) {
        this.outCntrTime = outCntrTime;
    }
}
