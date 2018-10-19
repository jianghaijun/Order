package com.zx.order.activity;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zx.order.R;
import com.zx.order.adapter.IndividualTypeAdapter;
import com.zx.order.bean.IndividualBean;
import com.zx.order.utils.FalseDataUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 单选预约列表
 * 作者：JHJ
 * 日期：2018/10/12 14:47
 * Q Q: 1320666709
 */
public class IndividualCla {
    private TabHolder holder;
    private Activity mContext;

    public IndividualCla(Activity mContext, View layoutMe) {
        this.mContext = mContext;
        holder = new TabHolder();
        x.view().inject(holder, layoutMe);
    }

    /**
     * 赋值
     */
    public void setDate() {
        List<IndividualBean> strList = FalseDataUtil.getIndividualTypeData2();
        IndividualTypeAdapter typeAdapter = new IndividualTypeAdapter(strList);
        holder.rvIndividualType.setLayoutManager(new GridLayoutManager(mContext, 3));
        holder.rvIndividualType.setAdapter(typeAdapter);
    }


    /**
     * 容纳器
     */
    private class TabHolder {
        @ViewInject(R.id.rvIndividualType)
        private RecyclerView rvIndividualType;
    }
}
