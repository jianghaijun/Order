package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.BoxStatusBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 箱状态列表
 * 作者：JHJ
 * 日期：2018/11/1 11:50
 * Q Q: 1320666709
 */
public class BoxStatusModel extends BaseModel {
    private List<BoxStatusBean> data;

    public List<BoxStatusBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<BoxStatusBean> data) {
        this.data = data;
    }
}
