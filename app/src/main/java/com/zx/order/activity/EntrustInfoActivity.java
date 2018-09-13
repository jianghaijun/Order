package com.zx.order.activity;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zx.order.R;
import com.zx.order.adapter.BottomInfoAdapter;
import com.zx.order.adapter.EntrustInfoAdapter;
import com.zx.order.adapter.TimeLineAdapter;
import com.zx.order.bean.BottomInfoBean;
import com.zx.order.bean.LogisticsBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的
 */
public class EntrustInfoActivity {
    private EntrustHolder holder;
    private Activity mContext;

    public EntrustInfoActivity(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new EntrustHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate() {
        // 服务类型
        List<String> strList = new ArrayList<>();
        strList.add("仓储服务");
        strList.add("堆场服务");
        strList.add("货代服务");
        strList.add("拆提服务");
        strList.add("运输服务");
        strList.add("销售服务");
        strList.add("供应链\r\n金融服务");
        EntrustInfoAdapter entrustInfoAdapter = new EntrustInfoAdapter(strList);
        holder.rvServiceType.setLayoutManager(new GridLayoutManager(mContext, 4));
        holder.rvServiceType.setAdapter(entrustInfoAdapter);
    }

    /**
     * 容纳器
     */
    private class EntrustHolder {
        @ViewInject(R.id.rvServiceType)
        private RecyclerView rvServiceType;
    }
}
