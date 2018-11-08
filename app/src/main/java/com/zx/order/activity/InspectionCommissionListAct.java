package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.InspectionCommissionListAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.bean.InspectionCommissionBean;
import com.zx.order.model.InspectionCommissionModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.FalseDataUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.SpUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 查验委托---下一步
 * 作者：JHJ
 * 日期：2018/10/18 10:34
 * Q Q: 1320666709
 */
public class InspectionCommissionListAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;

    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;
    @ViewInject(R.id.rvInspectionCommissionList)
    private RecyclerView rvInspectionCommissionList;

    private Activity mContext;
    private int dataTotalNum = 0, loadType = 1, pagePosition = 1;
    private List<InspectionCommissionBean> dataList = new ArrayList<>();
    private InspectionCommissionListAdapter inspectionCommissionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inspection_commission_list);

        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        imgBtnLeft.setVisibility(View.VISIBLE);
        txtTitle.setText("下一步");
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        initData();
        getData(true);
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
        obj.put("voyageId", getIntent().getStringExtra("voyageId"));
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.CNTR_DEATAIL_LIST, obj.toString());
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
                    final InspectionCommissionModel model = gson.fromJson(jsonData, InspectionCommissionModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataTotalNum = model.getTotalNumber();
                                if (model.getData() != null) {
                                    dataList.addAll(model.getData());
                                }
                                stopLoad();
                                setData();
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
     * 赋值
     */
    private void setData() {
        if (inspectionCommissionAdapter == null) {
            inspectionCommissionAdapter = new InspectionCommissionListAdapter(dataList);
            rvInspectionCommissionList.setLayoutManager(new GridLayoutManager(mContext, 1));
            rvInspectionCommissionList.setAdapter(inspectionCommissionAdapter);
        } else {
            inspectionCommissionAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.btnLastStep, R.id.btnNextStep})
    private void onClick(View v) {
        switch (v.getId()) {
            // 返回---上一步
            case R.id.imgBtnLeft:
            case R.id.btnLastStep:
                this.finish();
                break;
            // 下一步
            case R.id.btnNextStep:
                boolean isSelect = false;
                for (InspectionCommissionBean bean : dataList) {
                    if (bean.isSelect()) {
                        isSelect = true;
                        break;
                    }
                }
                if (!isSelect) {
                    ToastUtil.showShort(mContext, "请先选择一条数据！");
                } else {
                    List<Map<String, Object>> dataMap = new ArrayList<>();
                    for (InspectionCommissionBean bean : dataList) {
                        if (bean.isSelect()) {
                            List<Map<String, Object>> obMap = bean.getSubmitList();
                            if (obMap != null) {
                                Map<String, Object> strMap = new HashMap<>();
                                strMap.put("cntrId", bean.getCntrId());
                                String key;
                                for (Map<String, Object> map : obMap) {
                                    String controlType = (String) map.get("controlType");
                                    switch (controlType) {
                                        case "1":
                                            key = (String) map.get("submitFieldName");
                                            EditText edt = (EditText) map.get("control");
                                            strMap.put(key, edt.getText().toString().trim());
                                            break;
                                        case "2":
                                            key = (String) map.get("submitFieldName");
                                            TextView txtSelect = (TextView) map.get("control");
                                            strMap.put(key, txtSelect.getHint().toString().trim());
                                            break;
                                        case "3":
                                            key = (String) map.get("submitFieldName");
                                            TextView txtData = (TextView) map.get("control");
                                            strMap.put(key, DateUtil.parse(txtData.getText().toString().trim()).getTime());
                                            break;
                                    }
                                }
                                dataMap.add(strMap);
                            }
                        }
                    }

                    Intent intent = new Intent(mContext, InspectionCommissionDetailsAct.class);
                    SpUtil.put(mContext, ConstantsUtil.INSPECTION_COMMISSION, new Gson().toJson(dataMap));
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}