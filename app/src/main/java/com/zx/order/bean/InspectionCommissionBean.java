package com.zx.order.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查验委托
 * 作者：JHJ
 * 日期：2018/10/17 14:57
 * Q Q: 1320666709
 */
public class InspectionCommissionBean {
    private String voyageId;                // 航次Id
    private String cntrId;                  // 箱号Id
    private String chVslname;               // 船名
    private String voyage;                  // 航次
    private String port;                    // 码头
    private boolean isSelect = false;       // 是否选中
    private List<InspectionCommissionBean> nextDataList; // 下一步列表数据
    private String txtName;                 // key
    private String txtContent;              // value
    private String submitFieldName;         // 提交时字段名称
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
    private List<Map<String, Object>> submitList = new ArrayList<>(); // 提交数据

    public String getVoyageId() {
        return voyageId == null ? "" : voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId;
    }

    public String getCntrId() {
        return cntrId == null ? "" : cntrId;
    }

    public void setCntrId(String cntrId) {
        this.cntrId = cntrId;
    }

    public String getChVslname() {
        return chVslname == null ? "" : chVslname;
    }

    public void setChVslname(String chVslname) {
        this.chVslname = chVslname;
    }

    public String getSubmitFieldName() {
        return submitFieldName == null ? "" : submitFieldName;
    }

    public void setSubmitFieldName(String submitFieldName) {
        this.submitFieldName = submitFieldName;
    }

    public String getVoyage() {
        return voyage == null ? "" : voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
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

    public List<InspectionCommissionBean> getNextDataList() {
        if (nextDataList == null) {
            return new ArrayList<>();
        }
        return nextDataList;
    }

    public void setNextDataList(List<InspectionCommissionBean> nextDataList) {
        this.nextDataList = nextDataList;
    }

    public String getTxtName() {
        return txtName == null ? "" : txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtContent() {
        return txtContent == null ? "" : txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public String getTxtColor() {
        return txtColor == null ? "" : txtColor;
    }

    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
    }

    public String getTxtSize() {
        return txtSize == null ? "" : txtSize;
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
        return hint == null ? "" : hint;
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
        return dateTimeType == null ? "" : dateTimeType;
    }

    public void setDateTimeType(String dateTimeType) {
        this.dateTimeType = dateTimeType;
    }

    public String getControlType() {
        return controlType == null ? "" : controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public List<InspectionCommissionBean> getOptions() {
        if (options == null) {
            return new ArrayList<>();
        }
        return options;
    }

    public void setOptions(List<InspectionCommissionBean> options) {
        this.options = options;
    }

    public String getSelectOption() {
        return selectOption == null ? "" : selectOption;
    }

    public void setSelectOption(String selectOption) {
        this.selectOption = selectOption;
    }

    public String getSelectOptionId() {
        return selectOptionId == null ? "" : selectOptionId;
    }

    public void setSelectOptionId(String selectOptionId) {
        this.selectOptionId = selectOptionId;
    }

    public String getOption() {
        return option == null ? "" : option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOptionId() {
        return optionId == null ? "" : optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public List<Map<String, Object>> getSubmitList() {
        if (submitList == null) {
            return new ArrayList<>();
        }
        return submitList;
    }

    public void setSubmitList(List<Map<String, Object>> submitList) {
        this.submitList = submitList;
    }
}