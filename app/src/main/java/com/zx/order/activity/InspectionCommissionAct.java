package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.zx.order.R;
import com.zx.order.adapter.InspectionCommissionAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.bean.InspectionCommissionBean;
import com.zx.order.listener.IntListener;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.FalseDataUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * 查验委托
 * 作者：JHJ
 * 日期：2018/10/17 14:18
 * Q Q: 1320666709
 */
public class InspectionCommissionAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.imgBtnRight)
    private ImageButton imgBtnRight;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.dlScreen)
    private DrawerLayout dlScreen;
    @ViewInject(R.id.btnConfirm)
    private Button btnConfirm;
    @ViewInject(R.id.rgBox)
    private RadioGroup rgBox;
    @ViewInject(R.id.txtProsecutor) // 检查方
    private TextView txtProsecutor;
    @ViewInject(R.id.txtImportAndExportLogo) // 进出口标识
    private TextView txtImportAndExportLogo;
    @ViewInject(R.id.txtInspectType) // 检查类型
    private TextView txtInspectType;
    @ViewInject(R.id.edtBillOfLadingNum) // 提单号
    private EditText edtBillOfLadingNum;
    @ViewInject(R.id.edtBoxNo) // 箱号
    private EditText edtBoxNo;
    @ViewInject(R.id.llNoData)
    private LinearLayout llNoData;
    @ViewInject(R.id.llHaveData)
    private LinearLayout llHaveData;
    @ViewInject(R.id.llMain)
    private LinearLayout llMain;
    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;
    @ViewInject(R.id.rvInspectionCommission)
    private RecyclerView rvInspectionCommission;

    private Activity mContext;
    private int dataTotalNum = 0, loadType = 1, pagePosition = 1;
    private List<InspectionCommissionBean> dataList = new ArrayList<>();
    private InspectionCommissionAdapter inspectionCommissionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inspection_commission);

        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnRight.setVisibility(View.VISIBLE);
        txtTitle.setText(getIntent().getStringExtra("title"));
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));
        imgBtnRight.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.screen));

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
        dataList.addAll(FalseDataUtil.getInspectionCommissionData());
        dlScreen.closeDrawer(Gravity.RIGHT);
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

        if (inspectionCommissionAdapter == null) {
            inspectionCommissionAdapter = new InspectionCommissionAdapter(dataList);
            rvInspectionCommission.setLayoutManager(new GridLayoutManager(mContext, 1));
            rvInspectionCommission.setAdapter(inspectionCommissionAdapter);
        } else {
            inspectionCommissionAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 判断是否输入搜索条件
     *
     * @return
     */
    private boolean checkSearchInput() {
        if (StrUtil.isNotEmpty(txtProsecutor.getText().toString())) {
            return true;
        }
        if (StrUtil.isNotEmpty(txtImportAndExportLogo.getText().toString())) {
            return true;
        }
        if (StrUtil.isNotEmpty(txtInspectType.getText().toString())) {
            return true;
        }
        if (rgBox.getCheckedRadioButtonId() == R.id.rBtnSpellBox || rgBox.getCheckedRadioButtonId() == R.id.rBtnWholeBox) {
            return true;
        }
        if (StrUtil.isNotEmpty(edtBillOfLadingNum.getText().toString())) {
            return true;
        }
        if (StrUtil.isNotEmpty(edtBoxNo.getText().toString())) {
            return true;
        }
        return false;
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.txtProsecutor, R.id.txtImportAndExportLogo, R.id.txtInspectType,
            R.id.btnConfirm, R.id.imgBtnRight, R.id.btnSearch})
    private void onClick(View v) {
        final String[] strList = new String[]{"条件1", "条件2", "条件3", "条件4", "条件5"};
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
            case R.id.imgBtnRight:
                dlScreen.openDrawer(Gravity.RIGHT);
                break;
            // 检查方
            case R.id.txtProsecutor:
                DateUtils.optionPicker(mContext, Arrays.asList(strList), new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        txtProsecutor.setText(strList[point]);
                    }
                });
                break;
            // 进出口标识
            case R.id.txtImportAndExportLogo:
                DateUtils.optionPicker(mContext, Arrays.asList(strList), new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        txtImportAndExportLogo.setText(strList[point]);
                    }
                });
                break;
            // 检查类型
            case R.id.txtInspectType:
                DateUtils.optionPicker(mContext, Arrays.asList(strList), new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        txtInspectType.setText(strList[point]);
                    }
                });
                break;
            // 搜索
            case R.id.btnSearch:
                if (!JudgeNetworkIsAvailable.isNetworkAvailable(this)) {
                    ToastUtil.showShort(mContext, getString(R.string.not_network));
                } else if (checkSearchInput()) {
                    searchData();
                } else {
                    ToastUtil.showShort(mContext, "至少选中一个条件！");
                }
                break;
            // 确认
            case R.id.btnConfirm:
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
                    Intent intent = new Intent(mContext, InspectionCommissionListAct.class);
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