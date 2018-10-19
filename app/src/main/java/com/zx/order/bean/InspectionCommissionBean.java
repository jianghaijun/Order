package com.zx.order.bean;

import java.util.List;

/**
 * 查验委托
 * 作者：JHJ
 * 日期：2018/10/17 14:57
 * Q Q: 1320666709
 */
public class InspectionCommissionBean {
    private String navigationalName;        // 航名
    private String voyageNum;               // 航次
    private String wharf;                   // 码头
    private boolean isSelect = false;       // 是否选中
    private List<InspectionCommissionBean> nextDataList; // 下一步列表数据
    private String txtName;                 // key
    private String txtContent;              // value
    private String txtColor;                // 颜色
    private String txtSize;                 // 大小
    private boolean isMust;                 // 是否必须
    private String hint;                    // 提示语
    private long dateTime;                  // 默认时间
    private String dateTimeType;            // 日期类型（1：日期 2：时间 3：日期加时间）
    private String controlType;             // 控件类型（1：输入框 2：下拉框 3：日期选择框）
    private List<InspectionCommissionBean> options; // 下拉框选项
    private String selectOption;            // 下拉框默认选中值
    private String selectOptionId;          // 下拉框默认选中ID
    private String option;                  // 选项
    private String optionId;                // 选项ID

    public String getNavigationalName() {
        return navigationalName;
    }

    public void setNavigationalName(String navigationalName) {
        this.navigationalName = navigationalName;
    }

    public String getVoyageNum() {
        return voyageNum;
    }

    public void setVoyageNum(String voyageNum) {
        this.voyageNum = voyageNum;
    }

    public String getWharf() {
        return wharf;
    }

    public void setWharf(String wharf) {
        this.wharf = wharf;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<InspectionCommissionBean> getNextDataList() {
        return nextDataList;
    }

    public void setNextDataList(List<InspectionCommissionBean> nextDataList) {
        this.nextDataList = nextDataList;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public String getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
    }

    public String getTxtSize() {
        return txtSize;
    }

    public void setTxtSize(String txtSize) {
        this.txtSize = txtSize;
    }

    public boolean isMust() {
        return isMust;
    }

    public void setMust(boolean must) {
        isMust = must;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTimeType() {
        return dateTimeType;
    }

    public void setDateTimeType(String dateTimeType) {
        this.dateTimeType = dateTimeType;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public List<InspectionCommissionBean> getOptions() {
        return options;
    }

    public void setOptions(List<InspectionCommissionBean> options) {
        this.options = options;
    }

    public String getSelectOption() {
        return selectOption;
    }

    public void setSelectOption(String selectOption) {
        this.selectOption = selectOption;
    }

    public String getSelectOptionId() {
        return selectOptionId;
    }

    public void setSelectOptionId(String selectOptionId) {
        this.selectOptionId = selectOptionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }
}