package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.ClearanceBean;

import java.util.List;

/**
 * 航次列表
 * 作者：JHJ
 * 日期：2018/10/30 14:44
 * Q Q: 1320666709
 */
public class VoyageListModel extends BaseModel {
    private List<ClearanceBean> data;

    public List<ClearanceBean> getData() {
        return data;
    }

    public void setData(List<ClearanceBean> data) {
        this.data = data;
    }
}
