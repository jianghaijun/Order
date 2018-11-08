package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.EntrustingTheHarbourAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.base.BaseModel;
import com.zx.order.bean.EntrustingTheHarbourBean;
import com.zx.order.dialog.SelectDataDialog;
import com.zx.order.listener.StrListListener;
import com.zx.order.model.EntrustingTheHarbourModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 疏港委托
 * 作者：JHJ
 * 日期：2018/10/17 14:18
 * Q Q: 1320666709
 */
public class EntrustingTheHarbourAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.edtBillOfLadingNum)
    private EditText edtBillOfLadingNum;
    @ViewInject(R.id.btnConfirm)
    private Button btnConfirm;
    @ViewInject(R.id.llNoData)
    private LinearLayout llNoData;
    @ViewInject(R.id.llHaveData)
    private LinearLayout llHaveData;
    @ViewInject(R.id.llMain)
    private LinearLayout llMain;
    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;
    @ViewInject(R.id.rvEntrustingTheHarbour)
    private RecyclerView rvEntrustingTheHarbour;

    private Activity mContext;
    private int dataTotalNum = 0, loadType = 1, pagePosition = 1;
    private List<EntrustingTheHarbourBean> dataList = new ArrayList<>();
    private EntrustingTheHarbourAdapter entrustingTheHarbourAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_entrusting_the_harbour);

        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        imgBtnLeft.setVisibility(View.VISIBLE);
        txtTitle.setText(getIntent().getStringExtra("title"));
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        initData();

        llNoData.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.GONE);
        llHaveData.setVisibility(View.GONE);
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
     * 查询数据
     */
    private void searchData() {
        dataList.clear();
        getData(true);
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
        obj.put("cargoBillNo", edtBillOfLadingNum.getText().toString().trim());
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.ENTRUST_HARBOUR_LIST, obj.toString());
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
                    final EntrustingTheHarbourModel model = gson.fromJson(jsonData, EntrustingTheHarbourModel.class);
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
     * 提交预约
     *
     * @param strList
     */
    private void submitData(List<String> strList) {
        LoadingUtils.showLoading(mContext);
        List<Map<String, String>> entrustIdList = new ArrayList<>();
        for (EntrustingTheHarbourBean harbourBean : dataList) {
            Map<String, String> map = new HashMap<>();
            map.put("entrustHarbourId", harbourBean.getEntrustHarbourId());
            map.put("voyageId", harbourBean.getVoyageId());
            map.put("cntrId", harbourBean.getCntrId());
            map.put("cargoBillId", harbourBean.getCargoBillId());
            map.put("entrustTerminal", strList.get(0));
            map.put("portPurpose", strList.get(1));
            map.put("orderType", "9");
            entrustIdList.add(map);
        }
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.ENTRUST_HARBOUR, new Gson().toJson(entrustIdList));
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final BaseModel model = gson.fromJson(jsonData, BaseModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShort(mContext, "提交成功");
                                for (EntrustingTheHarbourBean bean : dataList) {
                                    bean.setSelect(false);
                                }
                                dataList.clear();
                                entrustingTheHarbourAdapter = null;
                                edtBillOfLadingNum.setText("");
                                edtBillOfLadingNum.setFocusable(false);
                                edtBillOfLadingNum.setFocusableInTouchMode(true);
                                llMain.setFocusable(true);
                                llMain.setFocusableInTouchMode(true);
                                setData();
                                LoadingUtils.hideLoading();
                            }
                        });
                    } else {
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.json_error));
                }
            }
        });
    }

    /**
     * 赋值
     */
    private void setData() {
        if (dataList != null && dataList.size() != 0) {
            llNoData.setVisibility(View.GONE);
            llHaveData.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.VISIBLE);
        } else {
            llNoData.setVisibility(View.VISIBLE);
            llHaveData.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.GONE);
        }

        if (entrustingTheHarbourAdapter == null) {
            entrustingTheHarbourAdapter = new EntrustingTheHarbourAdapter(dataList);
            rvEntrustingTheHarbour.setLayoutManager(new GridLayoutManager(mContext, 1));
            rvEntrustingTheHarbour.setAdapter(entrustingTheHarbourAdapter);
        } else {
            entrustingTheHarbourAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.btnQuery, R.id.btnConfirm})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
            // 查询
            case R.id.btnQuery:
                if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString())) {
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (!JudgeNetworkIsAvailable.isNetworkAvailable(this)) {
                    ToastUtil.showShort(mContext, getString(R.string.not_network));
                } else {
                    searchData();
                }
                break;
            // 确认
            case R.id.btnConfirm:
                boolean isSelect = false;
                for (EntrustingTheHarbourBean entrustingTheHarbourBean : dataList) {
                    if (entrustingTheHarbourBean.isSelect()) {
                        isSelect = true;
                        break;
                    }
                }
                if (!isSelect) {
                    ToastUtil.showShort(mContext, "至少选中一条预约！");
                } else {
                    new SelectDataDialog(mContext, "选择", new StrListListener() {
                        @Override
                        public void selectStrList(List<String> strList) {
                            submitData(strList);
                        }
                    }).show();
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