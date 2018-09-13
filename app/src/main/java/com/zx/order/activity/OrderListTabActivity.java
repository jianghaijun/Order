package com.zx.order.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.BottomInfoAdapter;
import com.zx.order.adapter.OrderListAdapter;
import com.zx.order.adapter.TimeLineAdapter;
import com.zx.order.bean.BottomInfoBean;
import com.zx.order.bean.OrderBean;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 我的
 */
public class OrderListTabActivity {
    private OrderHolder holder;
    private Activity mContext;
    private int loadType = 1, pagePosition = 1;
    private int dataSize = 0;
    private List<OrderBean> orderList = new ArrayList<>();

    public OrderListTabActivity(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new OrderHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate() {

        for (int i = 0; i < 3; i++) {
            orderList.add(new OrderBean());
        }


        // 设置主题颜色
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
                if (orderList.size() < dataSize) {
                    pagePosition++;
                    if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                        getData("", false);
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
                orderList.clear();
                if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                    getData("", false);
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(1000);
                }
            }
        });

        if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
            //getData("", true);
        } else {
            ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
        }

        // 时间轴
        OrderListAdapter orderAdapter = new OrderListAdapter(orderList, 2);
        holder.rvOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
        holder.rvOrderList.setAdapter(orderAdapter);
    }

    /**
     * 获取工序列表
     *
     * @param searchContext
     */
    private void getData(final String searchContext, final boolean isLoading) {
        if (isLoading) {
            LoadingUtils.showLoading(mContext);
        }
        JSONObject obj = new JSONObject();
        obj.put("page", pagePosition);
        obj.put("limit", 10);
        if (!StrUtil.isEmpty(searchContext)) {
            obj.put("keyword", searchContext);
        }
        Request request = ChildThreadUtil.getRequest(mContext, "", obj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                stopLoad();
                ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                /*if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final WorkingListModel model = gson.fromJson(jsonData, WorkingListModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stopLoad();
                                LoadingUtils.hideLoading();
                            }
                        });
                    } else {
                        stopLoad();
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    stopLoad();
                    ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.json_error));
                }*/
            }
        });
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
    private class OrderHolder {
        @ViewInject(R.id.refreshLayout)
        private RefreshLayout refreshLayout;
        @ViewInject(R.id.rvOrderList)
        private RecyclerView rvOrderList;
    }
}
