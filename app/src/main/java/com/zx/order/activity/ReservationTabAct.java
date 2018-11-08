package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.FrozenFruitsAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.bean.StatusBean;
import com.zx.order.model.FrozenFruitsDetailModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.Event;
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
 * 预约---next
 * 作者：JHJ
 * 日期：2018/10/12 10:28
 * Q Q: 1320666709
 */
public class ReservationTabAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.actionBar)
    private View actionBar;
    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;
    @ViewInject(R.id.rvOrderList)
    private RecyclerView rvOrderList;

    private int dataTotalNum = 0, loadType = 1, pagePosition = 1;
    private List<StatusBean> dataList = new ArrayList<>();
    private FrozenFruitsAdapter frozenFruitsAdapter;
    private Activity mContext;
    private String frozenFruitsType; // 0:冻品 1:水果
    private String[] str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_public_list);
        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        frozenFruitsType = getIntent().getStringExtra("frozenFruitsType");

        actionBar.setVisibility(View.VISIBLE);
        imgBtnLeft.setVisibility(View.VISIBLE);
        if (StrUtil.equals(frozenFruitsType, "0")) {
            txtTitle.setText("冻品信息列表");
        } else {
            txtTitle.setText("水果信息列表");
        }
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        if (StrUtil.equals(frozenFruitsType, "0")) {
            str = new String[]{"报关&0", "看货&1", "对扒&2", "入库&3", "取样&4", "出库&5", "配送&6"};
        } else {
            str = new String[]{"报关&0", "看货&1", "诉提&7", "入库&3", "熏蒸&8", "取样&4", "出库&5"};
        }

        initData();
    }

    /**
     * 初始化
     */
    private void initData() {
        // 设置主题颜色
        refreshLayout.setPrimaryColorsId(R.color.main_bg, android.R.color.white);
        refreshLayout.setFooterTriggerRate(1);
        refreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        refreshLayout.setEnableScrollContentWhenRefreshed(true);
        // 通过多功能监听接口实现 在第一次加载完成之后 自动刷新
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadType = 1;
                if (dataList.size() < dataTotalNum) {
                    pagePosition++;
                    if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                        getData(false);
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
                    getData(false);
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(ConstantsUtil.REFRESH_WAITING_TIME);
                }
            }
        });

        if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
            getData(true);
        } else {
            ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
        }
    }

    /**
     * 获取数据
     *
     * @param isLoading 是否显示加载框
     */
    private void getData(final boolean isLoading) {
        if (isLoading) {
            LoadingUtils.showLoading(mContext);
        }
        JSONObject obj = new JSONObject();
        obj.put("page", pagePosition);
        obj.put("limit", 10);
        obj.put("cargoBillId", getIntent().getStringExtra("cargoBillId"));
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.CNTR_LIST, obj.toString());
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
                    final FrozenFruitsDetailModel model = gson.fromJson(jsonData, FrozenFruitsDetailModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataTotalNum = model.getTotalNumber();
                                if (model.getData() != null) {
                                    for (StatusBean statusBean : model.getData()) {
                                        List<StatusBean> statusList = new ArrayList<>();
                                        for (String s : str) {
                                            StatusBean bean = new StatusBean();
                                            bean.setTitle(s.substring(0, 2));
                                            bean.setId(s.substring(3));
                                            bean.setSelect(StrUtil.containsAny(statusBean.getOrderType(), bean.getId()));
                                            statusList.add(bean);
                                        }
                                        statusBean.setStatusList(statusList);
                                    }

                                    dataList.addAll(model.getData());
                                }
                                stopLoad();
                                setFrozenFruitsData();
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
     * 设置数据
     */
    private void setFrozenFruitsData() {
        if (frozenFruitsAdapter == null) {
            frozenFruitsAdapter = new FrozenFruitsAdapter(dataList, getIntent().getStringExtra("cargoBillId"));
            rvOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
            rvOrderList.setAdapter(frozenFruitsAdapter);
        } else {
            frozenFruitsAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 停止加载
     */
    private void stopLoad() {
        if (loadType == 1) {
            refreshLayout.finishLoadMore(ConstantsUtil.REFRESH_WAITING_TIME);
        } else if (loadType == 2) {
            refreshLayout.finishRefresh(ConstantsUtil.REFRESH_WAITING_TIME);
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
