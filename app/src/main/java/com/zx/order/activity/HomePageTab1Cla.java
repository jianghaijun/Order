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
import com.zx.order.adapter.ClearanceStatusAdapter;
import com.zx.order.bean.ClearanceBean;
import com.zx.order.model.VoyageListModel;
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
 * 首页TAB1
 * 作者：JHJ
 * 日期：2018/10/9 16:31
 * Q Q: 1320666709
 */
public class HomePageTab1Cla {
    private TabHolder holder;
    private Activity mContext;
    private int dataTotalNum = 0, loadType = 1, pagePosition = 1;
    private List<ClearanceBean> dataList = new ArrayList<>();
    private ClearanceStatusAdapter clearanceStatusAdapter;

    public HomePageTab1Cla(Activity mContext, View layoutMe) {
        this.mContext = mContext;
        holder = new TabHolder();
        x.view().inject(holder, layoutMe);
    }

    /**
     * 赋值
     *
     * @param searchContext 搜索内容
     */
    public void setDate(final String searchContext) {
        pagePosition = 1;
        dataList.clear();

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
                if (dataList.size() < dataTotalNum) {
                    pagePosition++;
                    if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                        getData(searchContext, false);
                    } else {
                        ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
                    }
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(ConstantsUtil.REFRESH_WAITING_TIME);
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadType = 2;
                pagePosition = 1;
                dataList.clear();
                if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                    getData(searchContext, false);
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(ConstantsUtil.REFRESH_WAITING_TIME);
                }
            }
        });

        if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
            getData(searchContext, true);
        } else {
            ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
        }
    }

    /**
     * 获取航次列表
     *
     * @param searchContext 搜索内容
     * @param isLoading     是否弹出加载框
     */
    private void getData(final String searchContext, final boolean isLoading) {
        if (isLoading) {
            LoadingUtils.showLoading(mContext);
        }
        JSONObject obj = new JSONObject();
        obj.put("page", pagePosition);
        obj.put("limit", 10);
        if (StrUtil.isNotEmpty(searchContext)) {
            obj.put("voyage", searchContext);
        }
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.YD_VOYAGE_LIST, obj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                stopLoad();
                ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final VoyageListModel model = gson.fromJson(jsonData, VoyageListModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataTotalNum = model.getTotalNumber();
                                if (model.getData() != null) {
                                    dataList.addAll(model.getData());
                                }
                                stopLoad();
                                setClearanceData();
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
                }
            }
        });
    }

    /**
     * 设置通关状态数据
     */
    private void setClearanceData() {
        if (clearanceStatusAdapter == null) {
            clearanceStatusAdapter = new ClearanceStatusAdapter(dataList);
            holder.rvMyOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
            holder.rvMyOrderList.setAdapter(clearanceStatusAdapter);
        } else {
            clearanceStatusAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 停止加载
     */
    private void stopLoad() {
        if (loadType == 1) {
            holder.refreshLayout.finishLoadMore(ConstantsUtil.REFRESH_WAITING_TIME);
        } else if (loadType == 2) {
            holder.refreshLayout.finishRefresh(ConstantsUtil.REFRESH_WAITING_TIME);
        }
    }

    /**
     * 容纳器
     */
    private class TabHolder {
        @ViewInject(R.id.refreshLayout)
        private RefreshLayout refreshLayout;
        @ViewInject(R.id.rvMyOrderList)
        private RecyclerView rvMyOrderList;
    }
}
