package com.zx.order.bean;

/**
 * 预约
 * 作者：JHJ
 * 日期：2018/10/12 9:27
 * Q Q: 1320666709
 */
public class ReservationBean {
    private String cargoBillId; // 提单Id
    private String cargoBillNo; // 提单号
    private String pieces;      // 箱量

    public String getCargoBillId() {
        return cargoBillId == null ? "" : cargoBillId;
    }

    public void setCargoBillId(String cargoBillId) {
        this.cargoBillId = cargoBillId;
    }

    public String getCargoBillNo() {
        return cargoBillNo == null ? "" : cargoBillNo;
    }

    public void setCargoBillNo(String cargoBillNo) {
        this.cargoBillNo = cargoBillNo;
    }

    public String getPieces() {
        return pieces == null ? "" : pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
