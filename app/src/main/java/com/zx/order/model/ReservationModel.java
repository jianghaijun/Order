package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.ReservationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 冻品、水果
 * 作者：JHJ
 * 日期：2018/11/1 16:13
 * Q Q: 1320666709
 */
public class ReservationModel extends BaseModel {
    private List<ReservationBean> data;

    public List<ReservationBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<ReservationBean> data) {
        this.data = data;
    }
}
