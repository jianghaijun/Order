package com.zx.order.bean;

/**
 * 疏港委托
 * 作者：JHJ
 * 日期：2018/10/17 14:57
 * Q Q: 1320666709
 */
public class EntrustingTheHarbourBean {
    private String billOfLadingNo;      // 提单号
    private String englishShipName;     // 英文船名
    private String importVoyage;        // 进口航次
    private String boxNum;              // 箱号
    private String boxType;             // 箱型
    private String boxSize;             // 尺寸
    private String harbourDredge;       // 疏港码头
    private boolean isSelect = false;   // 是否选中

    public String getBillOfLadingNo() {
        return billOfLadingNo;
    }

    public void setBillOfLadingNo(String billOfLadingNo) {
        this.billOfLadingNo = billOfLadingNo;
    }

    public String getEnglishShipName() {
        return englishShipName;
    }

    public void setEnglishShipName(String englishShipName) {
        this.englishShipName = englishShipName;
    }

    public String getImportVoyage() {
        return importVoyage;
    }

    public void setImportVoyage(String importVoyage) {
        this.importVoyage = importVoyage;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    public String getBoxType() {
        return boxType;
    }

    public void setBoxType(String boxType) {
        this.boxType = boxType;
    }

    public String getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(String boxSize) {
        this.boxSize = boxSize;
    }

    public String getHarbourDredge() {
        return harbourDredge;
    }

    public void setHarbourDredge(String harbourDredge) {
        this.harbourDredge = harbourDredge;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}