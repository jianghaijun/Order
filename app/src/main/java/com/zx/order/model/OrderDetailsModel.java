package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.OrderDetailsBean;

/**
 * 订单详情
 * 作者：JHJ
 * 日期：2018/11/5 9:55
 * Q Q: 1320666709
 */
public class OrderDetailsModel extends BaseModel {
    private OrderDetailsBean data;

    public OrderDetailsBean getData() {
        return data;
    }

    public void setData(OrderDetailsBean data) {
        this.data = data;
    }
}
