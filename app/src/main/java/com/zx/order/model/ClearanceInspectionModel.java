package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.ClearanceInspectionBean;

/**
 * 通关状态 查验
 * 作者：JHJ
 * 日期：2018/11/1 11:27
 * Q Q: 1320666709
 */
public class ClearanceInspectionModel extends BaseModel {
    private ClearanceInspectionBean data;

    public ClearanceInspectionBean getData() {
        return data;
    }

    public void setData(ClearanceInspectionBean data) {
        this.data = data;
    }
}
