package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.base.VoyageFlowBean;

/**
 * 航次流程
 * 作者：JHJ
 * 日期：2018/10/30 15:40
 * Q Q: 1320666709
 */
public class VoyageFlowModel extends BaseModel {
    private VoyageFlowBean data;

    public VoyageFlowBean getData() {
        return data;
    }

    public void setData(VoyageFlowBean data) {
        this.data = data;
    }
}
