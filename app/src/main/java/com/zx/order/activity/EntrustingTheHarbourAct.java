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

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.EntrustingTheHarbourAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.bean.EntrustingTheHarbourBean;
import com.zx.order.dialog.SelectDataDialog;
import com.zx.order.listener.StrListListener;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.FalseDataUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;

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
                dataList.clear();
                if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                    //getData("", false);
                    stopLoad();
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(1000);
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
        dataList.addAll(FalseDataUtil.getEntrustingData());
        setData();
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