package com.zx.order.bean;

/**
 * 疏港委托
 * 作者：JHJ
 * 日期：2018/10/17 14:57
 * Q Q: 1320666709
 */
public class EntrustingTheHarbourBean {
    private String entrustHarbourId;    // 主键ID
    private String cargoBillNo;         // 提单号
    private String cargoBillId;         // 提单号Id
    private String enVslname;           // 英文船名
    private String importVoyage;        // 进口航次
    private String voyageId;            // 航次Id
    private String cntrNo;              // 箱号
    private String cntrType;            // 箱型
    private String cntrId;              // 箱Id
    private String cntrSize;            // 尺寸
    private String port;                // 疏港码头
    private boolean isSelect = false;   // 是否选中

    public String getEntrustHarbourId() {
        return entrustHarbourId == null ? "" : entrustHarbourId;
    }

    public void setEntrustHarbourId(String entrustHarbourId) {
        this.entrustHarbourId = entrustHarbourId;
    }

    public String getCargoBillNo() {
        return cargoBillNo == null ? "" : cargoBillNo;
    }

    public void setCargoBillNo(String cargoBillNo) {
        this.cargoBillNo = cargoBillNo;
    }

    public String getCargoBillId() {
        return cargoBillId == null ? "" : cargoBillId;
    }

    public void setCargoBillId(String cargoBillId) {
        this.cargoBillId = cargoBillId;
    }

    public String getEnVslname() {
        return enVslname == null ? "" : enVslname;
    }

    public void setEnVslname(String enVslname) {
        this.enVslname = enVslname;
    }

    public String getImportVoyage() {
        return importVoyage == null ? "" : importVoyage;
    }

    public void setImportVoyage(String importVoyage) {
        this.importVoyage = importVoyage;
    }

    public String getVoyageId() {
        return voyageId == null ? "" : voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId;
    }

    public String getCntrNo() {
        return cntrNo == null ? "" : cntrNo;
    }

    public void setCntrNo(String cntrNo) {
        this.cntrNo = cntrNo;
    }

    public String getCntrType() {
        return cntrType == null ? "" : cntrType;
    }

    public void setCntrType(String cntrType) {
        this.cntrType = cntrType;
    }

    public String getCntrId() {
        return cntrId == null ? "" : cntrId;
    }

    public void setCntrId(String cntrId) {
        this.cntrId = cntrId;
    }

    public String getCntrSize() {
        return cntrSize == null ? "" : cntrSize;
    }

    public void setCntrSize(String cntrSize) {
        this.cntrSize = cntrSize;
    }

    public String getPort() {
        return port == null ? "" : port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}