package com.zx.order.model;

import com.zx.order.base.BaseModel;
import com.zx.order.bean.StatusBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 冻品、水果列表详情
 * 作者：JHJ
 * 日期：2018/11/1 16:29
 * Q Q: 1320666709
 */
public class FrozenFruitsDetailModel extends BaseModel {
    private List<StatusBean> data;

    public List<StatusBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<StatusBean> data) {
        this.data = data;
    }
}
