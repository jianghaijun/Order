package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.FlightVoyageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 提单列表
 * 作者：JHJ
 * 日期：2018/10/30 16:45
 * Q Q: 1320666709
 */
public class FlightVoyageModel extends BaseModel {
    private List<FlightVoyageBean> data;

    public List<FlightVoyageBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<FlightVoyageBean> data) {
        this.data = data;
    }
}
