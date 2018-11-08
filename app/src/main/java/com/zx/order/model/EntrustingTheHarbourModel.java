package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.EntrustingTheHarbourBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 疏港委托列表
 * 作者：JHJ
 * 日期：2018/11/6 11:12
 * Q Q: 1320666709
 */
public class EntrustingTheHarbourModel extends BaseModel {
    private List<EntrustingTheHarbourBean> data;

    public List<EntrustingTheHarbourBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<EntrustingTheHarbourBean> data) {
        this.data = data;
    }
}
