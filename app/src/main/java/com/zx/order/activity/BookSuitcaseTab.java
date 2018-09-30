package com.zx.order.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.EntrustListAdapter;
import com.zx.order.bean.OrderBean;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的
 */
public class BookSuitcaseTab {
    private EntrustListHolder holder;
    private Activity mContext;
    private List<OrderBean> entrustList = new ArrayList<>();
    private int loadType, dataSize, pagePosition = 1;

    public BookSuitcaseTab(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new EntrustListHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     *
     * @param type
     */
    public void setDate(int type) {
        // 服务类型
        for (int i = 0; i < 10; i++) {
            OrderBean bean = new OrderBean();
            bean.setTitle("集装箱号：TRIU8176540" + (i + 1));
            bean.setCommodityName("FRESH WHITE PITAYA");
            bean.setOrderDate(System.currentTimeMillis());
            bean.setToThePortDate(System.currentTimeMillis());
            bean.setOrderNo("ASC0185223");
            bean.setBillOfLadingNo("ASC0185680B");
            if (type == 1) {
                bean.setTextLabel("未预约");
                bean.setStates("预约");
            } else {
                bean.setTextLabel("已预约");
                bean.setStates("发送给车队");
            }
            entrustList.add(bean);
        }

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
                if (entrustList.size() < dataSize) {
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
                //entrustList.clear();
                if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                    stopLoad();
                    //getData("", false);
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(1000);
                }
            }
        });

        EntrustListAdapter adapter = new EntrustListAdapter(entrustList, type);
        holder.rvOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
        holder.rvOrderList.setAdapter(adapter);
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
    private class EntrustListHolder {
        @ViewInject(R.id.refreshLayout)
        private RefreshLayout refreshLayout;
        @ViewInject(R.id.rvOrderList)
        private RecyclerView rvOrderList;
    }
}
