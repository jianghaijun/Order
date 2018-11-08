package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.InspectionCommissionBean;

import java.util.ArrayList;
import java.util.List;

public class InspectionCommissionModel extends BaseModel {
    private List<InspectionCommissionBean> data;

    public List<InspectionCommissionBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<InspectionCommissionBean> data) {
        this.data = data;
    }
}
