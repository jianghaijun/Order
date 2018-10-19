package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.listener.IntListener;
import com.zx.order.utils.DateUtils;
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
 * 添加预约
 * 作者：JHJ
 * 日期：2018/10/15 9:48
 * Q Q: 1320666709
 */
public class AddIndividualAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    // 疏港委托
    @ViewInject(R.id.edtBillOfLadingNum) // 提单号
    private EditText edtBillOfLadingNum;
    @ViewInject(R.id.edtContainerNo) // 集装箱号
    private EditText edtContainerNo;
    @ViewInject(R.id.txtExpectedDateOfSuitcase) // 预计提箱日期
    private TextView txtExpectedDateOfSuitcase;
    // 查验委托
    @ViewInject(R.id.txtInspectionCommission) // 检查委托
    private TextView txtInspectionCommission;
    @ViewInject(R.id.edtBoxNo) // 箱号
    private EditText edtBoxNo;
    @ViewInject(R.id.edtInspectionNo) // 报验号
    private EditText edtInspectionNo;
    @ViewInject(R.id.edtProductName) // 品名
    private EditText edtProductName;
    @ViewInject(R.id.txtInspectionTime) // 检查时间
    private TextView txtInspectionTime;
    // 入库预约
    @ViewInject(R.id.edtSpecifications) // 规格
    private EditText edtSpecifications;
    @ViewInject(R.id.edtNumber) // 件数
    private EditText edtNumber;
    @ViewInject(R.id.edtWeight) // 件重
    private EditText edtWeight;
    // 出库预约---取样预约
    @ViewInject(R.id.edtCarNo) // 车号
    private EditText edtCarNo;
    @ViewInject(R.id.rgIsNeedCar) // 是否需要配车
    private RadioGroup rgIsNeedCar;
    @ViewInject(R.id.rBtnYes) // 是否需要配车
    private RadioButton rBtnYes;
    @ViewInject(R.id.rBtnNo) // 是否需要配车
    private RadioButton rBtnNo;
    // 对扒预约
    @ViewInject(R.id.edtPrimaryBoxNo) // 原箱号
    private EditText edtPrimaryBoxNo;
    @ViewInject(R.id.edtTargetBoxNo) // 目标箱号
    private EditText edtTargetBoxNo;
    // 拆提预约
    @ViewInject(R.id.edtTargetCarNo) // 目标车号
    private EditText edtTargetCarNo;
    // 熏蒸预约
    @ViewInject(R.id.edtColorNo) // 色号
    private EditText edtColorNo;
    @ViewInject(R.id.txtFumigatingTime) // 熏蒸时间
    private TextView txtFumigatingTime;
    // 验箱预约
    @ViewInject(R.id.edtShipCompany) // 船公司
    private EditText edtShipCompany;
    @ViewInject(R.id.edtCaseRequirements) // 验箱要求
    private EditText edtCaseRequirements;
    // 返箱预约
    @ViewInject(R.id.edtFleet) // 车队
    private EditText edtFleet;
    // 配送预约
    @ViewInject(R.id.edtTotalWeight) // 总重
    private EditText edtTotalWeight;
    @ViewInject(R.id.edtDestination) // 目的地
    private EditText edtDestination;
    @ViewInject(R.id.edtDistributionRequirement) // 配送要求
    private EditText edtDistributionRequirement;
    @ViewInject(R.id.txtDistributionTime) // 配送时间
    private TextView txtDistributionTime;

    private int point; // 点击第几个预约
    private Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        point = getIntent().getIntExtra("clickPoint", 0);
        switch (point) {
            // 疏港委托
            case 0:
                setContentView(R.layout.layout_add_entrusting_the_harbour);
                break;
            // 查验委托
            case 1:
                setContentView(R.layout.layout_add_inspection_commission);
                break;
            // 入库预约
            case 2:
                setContentView(R.layout.layout_add_storage_individual);
                break;
            // 出库预约---取样预约
            case 3:
            case 4:
                setContentView(R.layout.layout_add_reservations);
                break;
            // 对扒预约
            case 5:
                setContentView(R.layout.layout_add_reservations_for_pick_up);
                break;
            // 拆提预约
            case 6:
                setContentView(R.layout.layout_add_dismantling_reservations);
                break;
            // 熏蒸预约
            case 7:
                setContentView(R.layout.layout_add_fumigation_appointment);
                break;
            // 验箱预约
            case 8:
                setContentView(R.layout.layout_add_case_reservation);
                break;
            // 返箱预约
            case 9:
                setContentView(R.layout.layout_add_back_box_reservation);
                break;
            // 配送预约
            case 10:
                setContentView(R.layout.layout_add_distribution_reservation);
                break;
            default:
                setContentView(R.layout.layout_add_entrusting_the_harbour);
                break;
        }

        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        imgBtnLeft.setVisibility(View.VISIBLE);
        txtTitle.setText(getIntent().getStringExtra("title"));
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));
    }

    /**
     * 查验选择
     */
    private void selectInspectionOption() {
        final List<String> strList = new ArrayList<>();
        strList.add("检疫查验");
        strList.add("海关查验");
        DateUtils.optionPicker(mContext, strList, new IntListener() {
            @Override
            public void selectPoint(int point) {
                txtInspectionCommission.setText(strList.get(point));
            }
        });
    }

    /**
     * 检查是否全部填写
     *
     * @param isContinueAdd
     */
    private void checkIsAllInput(boolean isContinueAdd) {
        switch (point) {
            // 疏港委托
            case 0:
                if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtContainerNo.getText().toString().trim())) { // 集装箱号
                    ToastUtil.showShort(mContext, "请输入集装箱号！");
                } else if (StrUtil.isEmpty(txtExpectedDateOfSuitcase.getText().toString().trim())) { // 预计提箱日期
                    ToastUtil.showShort(mContext, "请选择预计提箱日期！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 查验委托
            case 1:
                if (StrUtil.isEmpty(txtInspectionCommission.getText().toString().trim())) { // 检查委托
                    ToastUtil.showShort(mContext, "请选择检查委托！");
                } else if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtBoxNo.getText().toString().trim())) { // 箱号
                    ToastUtil.showShort(mContext, "请输入箱号！");
                } else if (StrUtil.isEmpty(edtInspectionNo.getText().toString().trim())) { // 报验号
                    ToastUtil.showShort(mContext, "请输入报验号！");
                } else if (StrUtil.isEmpty(edtProductName.getText().toString().trim())) { // 品名
                    ToastUtil.showShort(mContext, "请输入品名！");
                } else if (StrUtil.isEmpty(txtInspectionTime.getText().toString().trim())) { // 检查时间
                    ToastUtil.showShort(mContext, "请选择检查时间！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 入库预约
            case 2:
                if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtProductName.getText().toString().trim())) { // 品名
                    ToastUtil.showShort(mContext, "请输入品名！");
                } else if (StrUtil.isEmpty(edtBoxNo.getText().toString().trim())) { // 箱号
                    ToastUtil.showShort(mContext, "请输入箱号！");
                } else if (StrUtil.isEmpty(edtSpecifications.getText().toString().trim())) { // 规格
                    ToastUtil.showShort(mContext, "请输入规格！");
                } else if (StrUtil.isEmpty(edtNumber.getText().toString().trim())) { // 件数
                    ToastUtil.showShort(mContext, "请输入件数！");
                } else if (StrUtil.isEmpty(edtWeight.getText().toString().trim())) { // 件重
                    ToastUtil.showShort(mContext, "请输入件重！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 出库预约---取样预约
            case 3:
            case 4:
                if (StrUtil.isEmpty(edtCarNo.getText().toString().trim())) { // 车号
                    ToastUtil.showShort(mContext, "请输入车号！");
                } else if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtProductName.getText().toString().trim())) { // 品名
                    ToastUtil.showShort(mContext, "请输入品名！");
                } else if (StrUtil.isEmpty(edtBoxNo.getText().toString().trim())) { // 箱号
                    ToastUtil.showShort(mContext, "请输入箱号！");
                } else if (StrUtil.isEmpty(edtSpecifications.getText().toString().trim())) { // 规格
                    ToastUtil.showShort(mContext, "请输入规格！");
                } else if (StrUtil.isEmpty(edtNumber.getText().toString().trim())) { // 件数
                    ToastUtil.showShort(mContext, "请输入件数！");
                } else if (StrUtil.isEmpty(edtWeight.getText().toString().trim())) { // 件重
                    ToastUtil.showShort(mContext, "请输入件重！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 对扒预约
            case 5:
                if (StrUtil.isEmpty(edtPrimaryBoxNo.getText().toString().trim())) { // 原箱号
                    ToastUtil.showShort(mContext, "请输入原箱号！");
                } else if (StrUtil.isEmpty(edtTargetBoxNo.getText().toString().trim())) { // 目标箱号
                    ToastUtil.showShort(mContext, "请输入目标箱号！");
                } else if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtProductName.getText().toString().trim())) { // 品名
                    ToastUtil.showShort(mContext, "请输入品名！");
                } else if (StrUtil.isEmpty(edtSpecifications.getText().toString().trim())) { // 规格
                    ToastUtil.showShort(mContext, "请输入规格！");
                } else if (StrUtil.isEmpty(edtNumber.getText().toString().trim())) { // 件数
                    ToastUtil.showShort(mContext, "请输入件数！");
                } else if (StrUtil.isEmpty(edtWeight.getText().toString().trim())) { // 件重
                    ToastUtil.showShort(mContext, "请输入件重！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 拆提预约
            case 6:
                if (StrUtil.isEmpty(edtPrimaryBoxNo.getText().toString().trim())) { // 原箱号
                    ToastUtil.showShort(mContext, "请输入原箱号！");
                } else if (StrUtil.isEmpty(edtTargetCarNo.getText().toString().trim())) { // 目标车号
                    ToastUtil.showShort(mContext, "请输入目标车号！");
                } else if (StrUtil.isEmpty(edtTargetBoxNo.getText().toString().trim())) { // 目标箱号
                    ToastUtil.showShort(mContext, "请输入目标箱号！");
                } else if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtProductName.getText().toString().trim())) { // 品名
                    ToastUtil.showShort(mContext, "请输入品名！");
                } else if (StrUtil.isEmpty(edtSpecifications.getText().toString().trim())) { // 规格
                    ToastUtil.showShort(mContext, "请输入规格！");
                } else if (StrUtil.isEmpty(edtNumber.getText().toString().trim())) { // 件数
                    ToastUtil.showShort(mContext, "请输入件数！");
                } else if (StrUtil.isEmpty(edtWeight.getText().toString().trim())) { // 件重
                    ToastUtil.showShort(mContext, "请输入件重！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 熏蒸预约
            case 7:
                if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtProductName.getText().toString().trim())) { // 品名
                    ToastUtil.showShort(mContext, "请输入品名！");
                } else if (StrUtil.isEmpty(edtBoxNo.getText().toString().trim())) { // 箱号
                    ToastUtil.showShort(mContext, "请输入箱号！");
                } else if (StrUtil.isEmpty(edtSpecifications.getText().toString().trim())) { // 规格
                    ToastUtil.showShort(mContext, "请输入规格！");
                } else if (StrUtil.isEmpty(edtNumber.getText().toString().trim())) { // 件数
                    ToastUtil.showShort(mContext, "请输入件数！");
                } else if (StrUtil.isEmpty(edtWeight.getText().toString().trim())) { // 件重
                    ToastUtil.showShort(mContext, "请输入件重！");
                } else if (StrUtil.isEmpty(edtColorNo.getText().toString().trim())) { // 色号
                    ToastUtil.showShort(mContext, "请输入色号！");
                } else if (StrUtil.isEmpty(txtFumigatingTime.getText().toString().trim())) { // 熏蒸时间
                    ToastUtil.showShort(mContext, "请选择熏蒸时间！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 验箱预约
            case 8:
                if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtShipCompany.getText().toString().trim())) { // 船公司
                    ToastUtil.showShort(mContext, "请输入船公司！");
                } else if (StrUtil.isEmpty(edtCaseRequirements.getText().toString().trim())) { // 验箱要求
                    ToastUtil.showShort(mContext, "请输入验箱要求！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 返箱预约
            case 9:
                if (StrUtil.isEmpty(edtBillOfLadingNum.getText().toString().trim())) { // 提单号
                    ToastUtil.showShort(mContext, "请输入提单号！");
                } else if (StrUtil.isEmpty(edtBoxNo.getText().toString().trim())) { // 箱号
                    ToastUtil.showShort(mContext, "请输入箱号！");
                } else if (StrUtil.isEmpty(edtCarNo.getText().toString().trim())) { // 车号
                    ToastUtil.showShort(mContext, "请输入车号！");
                } else if (StrUtil.isEmpty(edtFleet.getText().toString().trim())) { // 车队
                    ToastUtil.showShort(mContext, "请输入车队！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
            // 配送预约
            case 10:
                if (StrUtil.isEmpty(edtBoxNo.getText().toString().trim())) { // 箱号
                    ToastUtil.showShort(mContext, "请输入箱号！");
                } else if (StrUtil.isEmpty(edtProductName.getText().toString().trim())) { // 品名
                    ToastUtil.showShort(mContext, "请输入品名！");
                } else if (StrUtil.isEmpty(edtTotalWeight.getText().toString().trim())) { // 总重
                    ToastUtil.showShort(mContext, "请输入总重！");
                } else if (StrUtil.isEmpty(edtDestination.getText().toString().trim())) { // 目的地
                    ToastUtil.showShort(mContext, "请输入目的地！");
                } else if (StrUtil.isEmpty(txtDistributionTime.getText().toString().trim())) { // 配送时间
                    ToastUtil.showShort(mContext, "请选择配送时间！");
                } else if (StrUtil.isEmpty(edtDistributionRequirement.getText().toString().trim())) { // 配送要求
                    ToastUtil.showShort(mContext, "请输入配送要求！");
                } else {
                    submitData(isContinueAdd);
                }
                break;
        }
    }

    /**
     * 提交数据
     *
     * @param isContinueAdd
     */
    private void submitData(boolean isContinueAdd) {
        ToastUtil.showShort(this, "提交成功");
        if (isContinueAdd) {
            clearInputData();
        } else {
            finish();
        }
    }

    /**
     * 清空已填写数据
     */
    private void clearInputData() {
        switch (point) {
            // 疏港委托
            case 0:
                edtBillOfLadingNum.setText("");
                edtContainerNo.setText("");
                txtExpectedDateOfSuitcase.setText("");
                break;
            // 查验委托
            case 1:
                txtInspectionCommission.setText("");
                edtBillOfLadingNum.setText("");
                edtBoxNo.setText("");
                edtInspectionNo.setText("");
                edtProductName.setText("");
                txtInspectionTime.setText("");
                break;
            // 入库预约
            case 2:
                edtBillOfLadingNum.setText("");
                edtProductName.setText("");
                edtBoxNo.setText("");
                edtSpecifications.setText("");
                edtNumber.setText("");
                edtWeight.setText("");
                break;
            // 出库预约---取样预约
            case 3:
            case 4:
                edtCarNo.setText("");
                edtBillOfLadingNum.setText("");
                edtProductName.setText("");
                edtBoxNo.setText("");
                edtSpecifications.setText("");
                edtNumber.setText("");
                edtWeight.setText("");
                rBtnYes.setChecked(false);
                rBtnNo.setChecked(true);
                break;
            // 对扒预约
            case 5:
                edtPrimaryBoxNo.setText("");
                edtTargetBoxNo.setText("");
                edtBillOfLadingNum.setText("");
                edtProductName.setText("");
                edtSpecifications.setText("");
                edtNumber.setText("");
                edtWeight.setText("");
                rBtnYes.setChecked(false);
                rBtnNo.setChecked(true);
                break;
            // 拆提预约
            case 6:
                edtPrimaryBoxNo.setText("");
                edtTargetCarNo.setText("");
                edtTargetBoxNo.setText("");
                edtBillOfLadingNum.setText("");
                edtProductName.setText("");
                edtSpecifications.setText("");
                edtNumber.setText("");
                edtWeight.setText("");
                rBtnYes.setChecked(false);
                rBtnNo.setChecked(true);
                break;
            // 熏蒸预约
            case 7:
                edtBillOfLadingNum.setText("");
                edtProductName.setText("");
                edtBoxNo.setText("");
                edtSpecifications.setText("");
                edtNumber.setText("");
                edtSpecifications.setText("");
                edtWeight.setText("");
                edtColorNo.setText("");
                txtFumigatingTime.setText("");
                break;
            // 验箱预约
            case 8:
                edtBillOfLadingNum.setText("");
                edtShipCompany.setText("");
                edtCaseRequirements.setText("");
                break;
            // 返箱预约
            case 9:
                edtBillOfLadingNum.setText("");
                edtBoxNo.setText("");
                edtCarNo.setText("");
                edtFleet.setText("");
                break;
            // 配送预约
            case 10:
                edtBoxNo.setText("");
                edtProductName.setText("");
                edtTotalWeight.setText("");
                edtDestination.setText("");
                txtDistributionTime.setText("");
                edtDistributionRequirement.setText("");
                break;
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.txtExpectedDateOfSuitcase, R.id.txtInspectionCommission,
            R.id.txtInspectionTime, R.id.txtFumigatingTime, R.id.txtDistributionTime,
            R.id.btnAddSubmit, R.id.btnSubmitAdd})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
            // 疏港委托---》预计提箱日期
            case R.id.txtExpectedDateOfSuitcase:
                DateUtils.onYearMonthDayPicker(mContext, txtExpectedDateOfSuitcase);
                break;
            // 查验委托---》查验
            case R.id.txtInspectionCommission:
                selectInspectionOption();
                break;
            // 查验委托---》查验时间
            case R.id.txtInspectionTime:
                DateUtils.onYearMonthDayPicker(mContext, txtInspectionTime);
                break;
            // 熏蒸预约---》熏蒸时间
            case R.id.txtFumigatingTime:
                DateUtils.onYearMonthDayPicker(mContext, txtFumigatingTime);
                break;
            // 配送预约---》配送时间
            case R.id.txtDistributionTime:
                DateUtils.onYearMonthDayPicker(mContext, txtDistributionTime);
                break;
            // 提交
            case R.id.btnAddSubmit:
                if (JudgeNetworkIsAvailable.isNetworkAvailable(this)) {
                    checkIsAllInput(false);
                } else {
                    ToastUtil.showShort(mContext, getString(R.string.not_network));
                }
                break;
            // 提交并继续添加
            case R.id.btnSubmitAdd:
                if (JudgeNetworkIsAvailable.isNetworkAvailable(this)) {
                    checkIsAllInput(true);
                } else {
                    ToastUtil.showShort(mContext, getString(R.string.not_network));
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
