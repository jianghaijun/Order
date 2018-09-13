package com.zx.order.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
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
import com.zx.order.R;
import com.zx.order.adapter.LatestInfoAdapter;
import com.zx.order.adapter.OrderListAdapter;
import com.zx.order.bean.LatestInfoBean;
import com.zx.order.bean.OrderBean;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 我的
 */
public class OrderInfoActivity {
    private LatestInfoHolder holder;
    private Activity mContext;
    private List<OrderBean> latestInfoBeanList = new ArrayList<>();
    private int loadType, dataSize, pagePosition = 1;

    public OrderInfoActivity(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new LatestInfoHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate() {
        holder.imgBtnBack.setVisibility(View.GONE);
        holder.txtBackTitle.setPadding(DensityUtil.dip2px(10), 0, 0, 0);

        // 添加头部按钮
        List<String> strList = new ArrayList<>();
        strList.add("全部");
        strList.add("未到港");
        strList.add("已到港");
        setTopBtn(strList);

        // 服务类型
        for (int i = 0; i < 10; i++) {
            OrderBean bean = new OrderBean();
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

        OrderListAdapter latestInfoAdapter = new OrderListAdapter(latestInfoBeanList, 1);
        holder.rvOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
        holder.rvOrderList.setAdapter(latestInfoAdapter);
    }

    /**
     * 设置顶部按钮
     *
     * @param strList
     */
    private void setTopBtn(List<String> strList) {
        if (strList == null || strList.size() == 0) {
            return;
        }

        holder.llTopBtn.removeAllViews();
        final List<Button> buttonList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            final Button btn = new Button(mContext);
            btn.setText(strList.get(i));
            if (i == 0) {
                btn.setTextColor(ContextCompat.getColor(mContext, R.color.main_bg));
            } else {
                btn.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            }
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            btn.setTextSize(14);
            btn.setBackground(null);
            buttonList.add(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTabStart(buttonList, btn);
                }
            });
            holder.llTopBtn.addView(btn, lp);
        }
    }

    /**
     * tab点击事件
     */
    private void setTabStart(List<Button> buttonList, Button btn) {
        for (Button button : buttonList) {
            button.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
        btn.setTextColor(ContextCompat.getColor(mContext, R.color.main_bg));
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
        @ViewInject(R.id.llTopBtn)
        private LinearLayout llTopBtn;
        @ViewInject(R.id.imgBtnBack)
        private ImageButton imgBtnBack;
        @ViewInject(R.id.txtBackTitle)
        private TextView txtBackTitle;
    }
}
