package com.zx.order.base;

/**
 * 航次流程
 * 作者：JHJ
 * 日期：2018/10/30 15:41
 * Q Q: 1320666709
 */
public class VoyageFlowBean {
    private String contaNo;            // 集装箱号
    private long decArriveTime;        // 预计靠泊时间
    private long actualArriveTime;     // 实际靠泊时间
    private long dischargeDate;        // 卸船时间
    private long inDate;               // 进场时间
    private long outDate;              // 放行时间
    private long arriveDate;           // 提箱时间
    private long storageDate;          // 入库时间
    private long retDate;              // 返箱时间

    public String getContaNo() {
        return contaNo == null ? "" : contaNo;
    }

    public void setContaNo(String contaNo) {
        this.contaNo = contaNo;
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

    public long getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(long dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public long getInDate() {
        return inDate;
    }

    public void setInDate(long inDate) {
        this.inDate = inDate;
    }

    public long getOutDate() {
        return outDate;
    }

    public void setOutDate(long outDate) {
        this.outDate = outDate;
    }

    public long getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(long arriveDate) {
        this.arriveDate = arriveDate;
    }

    public long getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(long storageDate) {
        this.storageDate = storageDate;
    }

    public long getRetDate() {
        return retDate;
    }

    public void setRetDate(long retDate) {
        this.retDate = retDate;
    }
}
