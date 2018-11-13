package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.BoxStatusAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.bean.BoxStatusBean;
import com.zx.order.model.BoxStatusModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.InputFilterUtil;
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

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 首页--->航班航次--->箱状态
 * 作者：JHJ
 * 日期：2018/10/10 11:31
 * Q Q: 1320666709
 */
public class BoxStatusAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.imgBtnRight)
    private ImageButton imgBtnRight;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;
    @ViewInject(R.id.rvOrderList)
    private RecyclerView rvOrderList;
    @ViewInject(R.id.dlScreen)
    private DrawerLayout dlScreen;
    @ViewInject(R.id.edtContainerNo)
    private EditText edtContainerNo;
    @ViewInject(R.id.edtSuitcaseCarNo)
    private EditText edtSuitcaseCarNo;
    @ViewInject(R.id.txtReturnBoxDate)
    private TextView txtReturnBoxDate;

    private int dataTotalNum = 0, loadType = 1, pagePosition = 1;
    private List<BoxStatusBean> dataList = new ArrayList<>();
    private BoxStatusAdapter boxStatusAdapter;
    private Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_box_status_list);
        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        txtTitle.setText("箱状态");
        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));
        imgBtnRight.setVisibility(View.VISIBLE);
        imgBtnRight.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.screen));

        initData();

        if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
            getData(true);
        } else {
            ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
        }

        edtContainerNo.setFilters(InputFilterUtil.inputFilter(this));
        edtSuitcaseCarNo.setFilters(InputFilterUtil.inputFilter(this));
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
        obj.put("cargoBillId", getIntent().getStringExtra("cargoBillId"));
        if (StrUtil.isNotEmpty(edtContainerNo.getText().toString().trim())) {
            obj.put("cntrNo", edtContainerNo.getText().toString().trim());
        }
        if (StrUtil.isNotEmpty(edtSuitcaseCarNo.getText().toString().trim())) {
            obj.put("outTruckNo", edtSuitcaseCarNo.getText().toString().trim());
        }
        if (StrUtil.isNotEmpty(txtReturnBoxDate.getText().toString().trim())) {
            obj.put("retDate", DateUtil.parse(txtReturnBoxDate.getText().toString().trim(), "yyyy-MM-dd").getTime());
        }

        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.BOX_STATUS, obj.toString());
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
                    final BoxStatusModel model = gson.fromJson(jsonData, BoxStatusModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataTotalNum = model.getTotalNumber();
                                if (model.getData() != null) {
                                    dataList.addAll(model.getData());
                                }
                                stopLoad();
                                setFlightVoyageData();
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
    private void setFlightVoyageData() {
        if (boxStatusAdapter == null) {
            boxStatusAdapter = new BoxStatusAdapter(dataList);
            rvOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
            rvOrderList.setAdapter(boxStatusAdapter);
        } else {
            boxStatusAdapter.notifyDataSetChanged();
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
    @Event({R.id.imgBtnLeft, R.id.imgBtnRight, R.id.txtReturnBoxDate,
            R.id.btnSearch, R.id.btnClear})
    private void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.imgBtnLeft:
                this.finish();
                break;
            // 筛选
            case R.id.imgBtnRight:
                dlScreen.openDrawer(Gravity.RIGHT);
                break;
            // 返箱日期
            case R.id.txtReturnBoxDate:
                DateUtils.onYearMonthDayPicker(mContext, txtReturnBoxDate);
                break;
            // 重置
            case R.id.btnClear:
                edtContainerNo.setText("");
                edtSuitcaseCarNo.setText("");
                txtReturnBoxDate.setText("");
                break;
            // 搜索
            case R.id.btnSearch:
                /*if (StrUtil.isEmpty(edtContainerNo.getText().toString().trim()) &&
                        StrUtil.isEmpty(edtSuitcaseCarNo.getText().toString().trim()) &&
                        StrUtil.isEmpty(txtReturnBoxDate.getText().toString().trim())) {
                    ToastUtil.showShort(mContext, "只少选择一个检索条件！");
                } else {*/
                    pagePosition = 1;
                    dataList.clear();
                    dlScreen.closeDrawer(Gravity.RIGHT);
                    getData(true);
                /*}*/
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
