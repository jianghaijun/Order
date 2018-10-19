package com.zx.order.bean;

/**
 * 箱状态
 * 作者：JHJ
 * 日期：2018/10/10 11:32
 * Q Q: 1320666709
 */
public class BoxStatusBean {
    private String containerNo;
    private String suitcaseCarNo;
    private long returnBoxDate;
    private long boxEntryTime;
    private long arrivalTime;
    private long approachTimeOfSuitcaseVehicle;
    private long boxDrivingTime;

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getSuitcaseCarNo() {
        return suitcaseCarNo;
    }

    public void setSuitcaseCarNo(String suitcaseCarNo) {
        this.suitcaseCarNo = suitcaseCarNo;
    }

    public long getReturnBoxDate() {
        return returnBoxDate;
    }

    public void setReturnBoxDate(long returnBoxDate) {
        this.returnBoxDate = returnBoxDate;
    }

    public long getBoxEntryTime() {
        return boxEntryTime;
    }

    public void setBoxEntryTime(long boxEntryTime) {
        this.boxEntryTime = boxEntryTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getApproachTimeOfSuitcaseVehicle() {
        return approachTimeOfSuitcaseVehicle;
    }

    public void setApproachTimeOfSuitcaseVehicle(long approachTimeOfSuitcaseVehicle) {
        this.approachTimeOfSuitcaseVehicle = approachTimeOfSuitcaseVehicle;
    }

    public long getBoxDrivingTime() {
        return boxDrivingTime;
    }

    public void setBoxDrivingTime(long boxDrivingTime) {
        this.boxDrivingTime = boxDrivingTime;
    }
}
