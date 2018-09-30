package com.zx.order.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zhy.android.percent.support.PercentLinearLayout;
import com.zx.order.R;
import com.zx.order.adapter.LatestInfoAdapter;
import com.zx.order.adapter.OrderListAdapter;
import com.zx.order.bean.LatestInfoBean;
import com.zx.order.bean.OrderBean;
import com.zx.order.popwindow.ProcessPopupWindow;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;


/**
 * 我的
 */
public class OrderInfoAct {
    private LatestInfoHolder holder;
    private Activity mContext;
    private List<OrderBean> latestInfoBeanList = new ArrayList<>();
    private int loadType, dataSize, pagePosition = 1;

    public OrderInfoAct(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new LatestInfoHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate(int type) {
        if (type == 2) {
            holder.actionBar.setVisibility(View.GONE);
            holder.tlTop.setVisibility(View.GONE);
        }

        holder.imgBtnBack.setVisibility(View.GONE);
        holder.txtBackTitle.setPadding(DensityUtil.dip2px(10), 0, 0, 0);
        holder.txtScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessPopupWindow pop = new ProcessPopupWindow(mContext);
                pop.showAtDropDownRight(v);
            }
        });

        // 服务类型
        OrderBean bean = new OrderBean();
        bean.setTitle("集装箱：TRIU8722569");
        bean.setCommodityName("FRESH BANANA NETSCO BRAND GROW");
        bean.setBillOfLadingNo("ASC0185223");
        bean.setOrderNo("ZCSU5846054");
        bean.setOrderDate(DateUtil.parseDate("2018-05-20").getTime());
        bean.setToThePortDate(DateUtil.parseDate("2018-06-36").getTime());
        latestInfoBeanList.add(bean);

        OrderBean bean1 = new OrderBean();
        bean1.setTitle("集装箱：JXLU5842906");
        bean1.setCommodityName("KOTA NANHAI / 0028E");
        bean1.setBillOfLadingNo("GES800184200");
        bean1.setOrderNo("OOLU2026826980");
        bean1.setOrderDate(DateUtil.parseDate("2018-04-12").getTime());
        bean1.setToThePortDate(DateUtil.parseDate("2018-05-25").getTime());
        latestInfoBeanList.add(bean1);

        OrderBean bean2 = new OrderBean();
        bean2.setTitle("集装箱：OOLU6176360");
        bean2.setCommodityName("FRESH WHITE PITAYA");
        bean2.setBillOfLadingNo("GES800184200");
        bean2.setOrderNo("ASC0185223");
        bean2.setOrderDate(DateUtil.parseDate("2018-04-18").getTime());
        bean2.setToThePortDate(DateUtil.parseDate("2018-05-24").getTime());
        latestInfoBeanList.add(bean2);

        OrderBean bean3 = new OrderBean();
        bean3.setTitle("集装箱：TRIU8722569");
        bean3.setCommodityName("FRESH BANANA NETSCO BRAND GROW");
        bean3.setBillOfLadingNo("ASC0185223");
        bean3.setOrderNo("ZCSU5846054");
        bean3.setOrderDate(DateUtil.parseDate("2018-05-20").getTime());
        bean3.setToThePortDate(DateUtil.parseDate("2018-06-36").getTime());
        latestInfoBeanList.add(bean3);

        OrderBean bean4 = new OrderBean();
        bean4.setTitle("集装箱：JXLU5842906");
        bean4.setCommodityName("KOTA NANHAI / 0028E");
        bean4.setBillOfLadingNo("GES800184200");
        bean4.setOrderNo("OOLU2026826980");
        bean4.setOrderDate(DateUtil.parseDate("2018-04-12").getTime());
        bean4.setToThePortDate(DateUtil.parseDate("2018-05-25").getTime());
        latestInfoBeanList.add(bean4);

        OrderBean bean5 = new OrderBean();
        bean5.setTitle("集装箱：OOLU6176360");
        bean5.setCommodityName("FRESH WHITE PITAYA");
        bean5.setBillOfLadingNo("GES800184200");
        bean5.setOrderNo("ASC0185223");
        bean5.setOrderDate(DateUtil.parseDate("2018-04-18").getTime());
        bean5.setToThePortDate(DateUtil.parseDate("2018-05-24").getTime());
        latestInfoBeanList.add(bean5);

        holder.refreshLayout.setPrimaryColorsId(R.color.main_bg, android.R.color.white);
        holder.refreshLayout.setFooterTriggerRate(1);
        holder.refreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        holder.refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        holder.refreshLayout.setEnableScrollContentWhenRefreshed(true);
        // 通过多功能监听接口实现 在第一次加载完成之后 自动刷新
        holder.refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadType = 1;
                if (latestInfoBeanList.size() < dataSize) {
                    pagePosition++;
                    if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                        stopLoad();
                        //getData("", false);
                    } else {
                        ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
                    }
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(1000);
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadType = 2;
                pagePosition = 1;
                //latestInfoBeanList.clear();
                if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                    stopLoad();
                    //getData("", false);
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(1000);
                }
            }
        });

        OrderListAdapter latestInfoAdapter = new OrderListAdapter(latestInfoBeanList, 1);
        holder.rvOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
        holder.rvOrderList.setAdapter(latestInfoAdapter);
    }

    /**
     * 停止加载
     */
    private void stopLoad() {
        if (loadType == 1) {
            holder.refreshLayout.finishLoadMore(1000);
        } else if (loadType == 2) {
            holder.refreshLayout.finishRefresh(1000);
        }
    }

    /**
     * 容纳器
     */
    private class LatestInfoHolder {
        @ViewInject(R.id.rvOrderList)
        private RecyclerView rvOrderList;
        @ViewInject(R.id.refreshLayout)
        private RefreshLayout refreshLayout;
        @ViewInject(R.id.imgBtnBack)
        private ImageButton imgBtnBack;
        @ViewInject(R.id.txtBackTitle)
        private TextView txtBackTitle;
        @ViewInject(R.id.txtScreen)
        private TextView txtScreen;
        @ViewInject(R.id.actionBar)
        private PercentLinearLayout actionBar;
        @ViewInject(R.id.tlTop)
        private TabLayout tlTop;
    }
}
