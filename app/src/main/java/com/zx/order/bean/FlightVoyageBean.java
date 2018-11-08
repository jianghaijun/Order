package com.zx.order.bean;

/**
 * 航班航次
 * 作者：JHJ
 * 日期：2018/10/9 18:48
 * Q Q: 1320666709
 */
public class FlightVoyageBean {
    private String cargoBillId;         // 主键ID
    private String cargoBillNo;         // 提单号
    private String voyageId;            // 航次Id
    private String billState;           // 单据状态 0：齐全 1：缺失
    private String declarationState;    // 申报状态 0：放行 1：扣留
    private String inspectionState;     // 查验状态 0：待查 1：已查
    private String pieces;              // 箱量

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

    public String getVoyageId() {
        return voyageId == null ? "" : voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId;
    }

    public String getBillState() {
        return billState == null ? "" : billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getDeclarationState() {
        return declarationState == null ? "" : declarationState;
    }

    public void setDeclarationState(String declarationState) {
        this.declarationState = declarationState;
    }

    public String getInspectionState() {
        return inspectionState == null ? "" : inspectionState;
    }

    public void setInspectionState(String inspectionState) {
        this.inspectionState = inspectionState;
    }

    public String getPieces() {
        return pieces == null ? "" : pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
