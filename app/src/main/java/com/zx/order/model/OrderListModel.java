package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表
 * 作者：JHJ
 * 日期：2018/11/1 18:39
 * Q Q: 1320666709
 */
public class OrderListModel extends BaseModel {
    private List<OrderBean> data;

    public List<OrderBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<OrderBean> data) {
        this.data = data;
    }
}
