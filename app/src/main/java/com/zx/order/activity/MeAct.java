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
import com.zx.order.adapter.EntrustInfoAdapter;
import com.zx.order.adapter.LatestInfoAdapter;
import com.zx.order.bean.LatestInfoBean;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 我的
 */
public class MeAct {
    private LatestInfoHolder holder;
    private Activity mContext;
    private List<LatestInfoBean> latestInfoBeanList = new ArrayList<>();
    private int loadType, dataSize, pagePosition = 1;

    public MeAct(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new LatestInfoHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate() {
        // 服务类型
        for (int i = 0; i < 10; i++) {
            LatestInfoBean bean = new LatestInfoBean();
            bean.setTitle("最新新闻" + i);
            bean.setImgUrl("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2069251242,892993321&fm=26&gp=0.jpg");
            bean.setNewsDate(new Date().getTime());
            bean.setContent("家里开始缴费卡积分阿萨德科技发收款两地分居阿拉山口");
            latestInfoBeanList.add(bean);
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

        LatestInfoAdapter latestInfoAdapter = new LatestInfoAdapter(latestInfoBeanList);
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
    }
}
