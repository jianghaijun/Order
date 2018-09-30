package com.zx.order.activity;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.vivian.timelineitemdecoration.itemdecoration.SpanIndexListener;
import com.zx.order.R;
import com.zx.order.adapter.WaterfallFlowTimeLineAdapter;
import com.zx.order.bean.TimeLineBean;
import com.zx.order.custom.DotItemDecoration;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.hutool.core.date.DateUtil;


/**
 * 我的
 */
public class HomePageTab2 {
    private TabHolder holder;
    private Activity mContext;

    public HomePageTab2(Activity mContext, View layoutMe) {
        this.mContext = mContext;
        holder = new TabHolder();
        x.view().inject(holder, layoutMe);
    }

    /**
     * 赋值
     *
     * @param dateList
     */
    public void setDate(List<TimeLineBean> dateList) {
        if (dateList == null) {
            dateList = new ArrayList<>();
        }

        List<String[]> strList = new ArrayList<>();
        strList.add(new String[] {"集装箱号：", "MWCU1234567"});
        strList.add(new String[] {"预计到港时间：", "2018-09-28 15:00:00"});
        strList.add(new String[] {"实际到港时间：", "2018-09-28 18:00:00"});
        strList.add(new String[] {"卸船时间：", "2018-09-29 08:00:00"});
        strList.add(new String[] {"进场时间：", "2018-09-29 08:20:00"});
        strList.add(new String[] {"放行时间：", "2018-09-29 10:00:00"});
        strList.add(new String[] {"提箱时间：", "2018-09-29 13:00:00"});
        strList.add(new String[] {"到仓库时间：", "2018-10-01 18:00:00"});
        strList.add(new String[] {"还箱时间：", "2018-10-07 08:00:00"});

        boolean isZero = true;
        for (String[] str : strList) {
            TimeLineBean bean = new TimeLineBean();
            bean.setStatus(str[0]);
            if (isZero) {
                bean.setTitle(str[1]);
                isZero = false;
            } else {
                bean.setActionTime(DateUtil.parse(str[1], "yyyy-MM-dd hh:mm:ss").getTime());
            }
            dateList.add(bean);
        }
        initView(dateList);
    }

    /**
     * 设置显示数据
     *
     * @param data
     */
    private void initView(List<TimeLineBean> data) {
        holder.rvTimeLineWaterfallFlow.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        DotItemDecoration mItemDecoration = new DotItemDecoration
                .Builder(mContext)
                .setOrientation(DotItemDecoration.VERTICAL)//if you want a horizontal item decoration,remember to set horizontal orientation to your LayoutManager
                .setItemStyle(DotItemDecoration.STYLE_DRAW)
                .setTopDistance(30)//dp
                .setItemInterVal(10)//dp
                .setDotColor(ContextCompat.getColor(mContext, R.color.main_bg))
                .setDotRadius(5)//dp
                .setDotPaddingTop(0)
                .setDotInItemOrientationCenter(true)//set true if you want the dot align center
                .setLineColor(ContextCompat.getColor(mContext, R.color.main_bg))
                .setLineWidth(3)//dp
                .setEndText("END")
                .setBottomDistance(40)
                .create();
        mItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                view.setBackgroundResource(spanIndex == 0 ? R.drawable.left : R.drawable.right);
            }
        });
        holder.rvTimeLineWaterfallFlow.addItemDecoration(mItemDecoration);
        WaterfallFlowTimeLineAdapter mAdapter = new WaterfallFlowTimeLineAdapter(mContext, data);
        holder.rvTimeLineWaterfallFlow.setAdapter(mAdapter);
    }

    /**
     * 容纳器
     */
    private class TabHolder {
        @ViewInject(R.id.rvTimeLineWaterfallFlow)
        private RecyclerView rvTimeLineWaterfallFlow;
    }
}
