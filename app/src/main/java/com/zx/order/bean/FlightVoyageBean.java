package com.zx.order.bean;

/**
 * 航班航次
 * 作者：JHJ
 * 日期：2018/10/9 18:48
 * Q Q: 1320666709
 */
public class FlightVoyageBean {
    private String billOfLadingNumContext;
    private String billStatus;
    private String declareStatus;
    private String inspectionStatus;
    private String boxNum;

    public String getBillOfLadingNumContext() {
        return billOfLadingNumContext;
    }

    public void setBillOfLadingNumContext(String billOfLadingNumContext) {
        this.billOfLadingNumContext = billOfLadingNumContext;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getDeclareStatus() {
        return declareStatus;
    }

    public void setDeclareStatus(String declareStatus) {
        this.declareStatus = declareStatus;
    }

    public String getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(String inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }
}
