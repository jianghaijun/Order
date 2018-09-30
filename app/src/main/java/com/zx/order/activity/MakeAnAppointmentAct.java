package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class MakeAnAppointmentAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtAppointmentDate)
    private TextView txtAppointmentDate;
    @ViewInject(R.id.txtAppointmentTimeStart)
    private TextView txtAppointmentTimeStart;
    @ViewInject(R.id.txtAppointmentTimeEnd)
    private TextView txtAppointmentTimeEnd;
    @ViewInject(R.id.txtFleetNo)
    private TextView txtFleetNo;
    @ViewInject(R.id.txtLicensePlateNo)
    private TextView txtLicensePlateNo;
    @ViewInject(R.id.edtLicensePlateNo)
    private EditText edtLicensePlateNo;
    @ViewInject(R.id.edtDriverName)
    private EditText edtDriverName;
    @ViewInject(R.id.edtDriverPhone)
    private EditText edtDriverPhone;

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_make_appointment);

        x.view().inject(this);

        ScreenManagerUtil.pushActivity(this);
        mContext = this;

        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        //setData();
    }

    /**
     * 提交数据
     */
    private void submitData() {
        Intent intent = new Intent(mContext, ReservationsActivity.class);
        startActivity(intent);
    }

    /**
     * 判断是否都已经填写
     * @return
     */
    private boolean checkEmpty() {
        boolean isEmpty = true;
        if (StrUtil.isEmpty(txtAppointmentDate.getText().toString())) {
            ToastUtil.showShort(this, "请选择预约日期！");
        } else if (StrUtil.isEmpty(txtAppointmentTimeStart.getText().toString())) {
            ToastUtil.showShort(this, "请选择预约时间！");
        } else if (StrUtil.isEmpty(txtAppointmentTimeEnd.getText().toString())) {
            ToastUtil.showShort(this, "请选择预约时间！");
        } else if (DateUtils.compareDate(txtAppointmentDate.getText().toString() + " " + txtAppointmentTimeStart.getText().toString(), txtAppointmentDate.getText().toString() + " " + txtAppointmentTimeEnd.getText().toString()) == 1) {
            ToastUtil.showShort(this, "预约开始时间不能大于结束时间！");
        } else if (StrUtil.isEmpty(txtFleetNo.getText().toString())) {
            ToastUtil.showShort(this, "请选择车队代码！");
        } else if (StrUtil.isEmpty(txtLicensePlateNo.getText().toString())) {
            ToastUtil.showShort(this, "请选择车牌号！");
        } else if (StrUtil.isEmpty(edtLicensePlateNo.getText().toString())) {
            ToastUtil.showShort(this, "请填写车牌号！");
        } else if (StrUtil.isEmpty(edtDriverName.getText().toString())) {
            ToastUtil.showShort(this, "请填写司机姓名！");
        } else if (StrUtil.isEmpty(edtDriverPhone.getText().toString())) {
            ToastUtil.showShort(this, "请填写司机手机号！");
        } else {
            isEmpty = false;
        }
        return isEmpty;
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.txtAppointmentDate, R.id.txtAppointmentTimeStart,
            R.id.txtAppointmentTimeEnd, R.id.txtFleetNo, R.id.txtLicensePlateNo,
            R.id.btnNo, R.id.btnYes})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNo:
            case R.id.imgBtnLeft:
                this.finish();
                break;
            // 预约日期
            case R.id.txtAppointmentDate:
                DateUtils.onYearMonthDayPicker(mContext, txtAppointmentDate);
                break;
            // 预约开始时间
            case R.id.txtAppointmentTimeStart:
                DateUtils.onTimePicker(mContext, txtAppointmentTimeStart);
                break;
            // 预约结束时间
            case R.id.txtAppointmentTimeEnd:
                DateUtils.onTimePicker(mContext, txtAppointmentTimeEnd);
                break;
            // 车队代码
            case R.id.txtFleetNo:
                final List<String> strList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    strList.add("10000" + i);
                }
                DateUtils.optionPicker(mContext, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        txtFleetNo.setText(strList.get(point));
                    }
                });
                break;
            // 车牌号
            case R.id.txtLicensePlateNo:
                final List<String> plateNo = new ArrayList<>();
                plateNo.add("辽A");
                plateNo.add("辽B");
                plateNo.add("辽C");
                plateNo.add("辽D");
                plateNo.add("辽E");
                plateNo.add("辽F");
                DateUtils.optionPicker(mContext, plateNo, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        txtLicensePlateNo.setText(plateNo.get(point));
                    }
                });
                break;
            // 确认
            case R.id.btnYes:
                if (!JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                    ToastUtil.showShort(mContext, getString(R.string.not_network));
                } else {
                    if (!checkEmpty()) {
                        submitData();
                    }
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
