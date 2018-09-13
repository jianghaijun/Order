package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.utils.ScreenManagerUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class DetailsActivity extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;

    @ViewInject(R.id.llType1)
    private LinearLayout llType1;
    @ViewInject(R.id.txtNoBoxPeriod)
    private TextView txtNoBoxPeriod;
    @ViewInject(R.id.txtCustomerDays)
    private TextView txtCustomerDays;
    @ViewInject(R.id.txtHdcDate)
    private TextView txtHdcDate;
    @ViewInject(R.id.txtStagnationBoxDays)
    private TextView txtStagnationBoxDays;
    @ViewInject(R.id.txtOccupiedDays)
    private TextView txtOccupiedDays;
    @ViewInject(R.id.txtBeyondTheNumOfDays)
    private TextView txtBeyondTheNumOfDays;
    @ViewInject(R.id.txtOverdueCost)
    private TextView txtOverdueCost;
    @ViewInject(R.id.txtQueryOverdueCost)
    private TextView txtQueryOverdueCost;
    @ViewInject(R.id.txtActualOverdueCost)
    private TextView txtActualOverdueCost;

    @ViewInject(R.id.llType2)
    private LinearLayout llType2;
    @ViewInject(R.id.txtInspectionNum)
    private TextView txtInspectionNum;
    @ViewInject(R.id.txtCustomsClearanceNum)
    private TextView txtCustomsClearanceNum;
    @ViewInject(R.id.txtDeclareAtCustoms)
    private TextView txtDeclareAtCustoms;
    @ViewInject(R.id.txtCustomsInspectionPoint)
    private TextView txtCustomsInspectionPoint;
    @ViewInject(R.id.txtReleaseTime)
    private TextView txtReleaseTime;
    @ViewInject(R.id.txtDeclareAtCustomsTime)
    private TextView txtDeclareAtCustomsTime;
    @ViewInject(R.id.txtTallyTime)
    private TextView txtTallyTime;
    @ViewInject(R.id.txtWarehouseReceiptTime)
    private TextView txtWarehouseReceiptTime;
    @ViewInject(R.id.txtArrivalTime)
    private TextView txtArrivalTime;
    @ViewInject(R.id.txtInspectionTime)
    private TextView txtInspectionTime;
    @ViewInject(R.id.txtActualArrivalTime)
    private TextView txtActualArrivalTime;

    @ViewInject(R.id.llType3)
    private LinearLayout llType3;
    @ViewInject(R.id.llBottom)
    private LinearLayout llBottom;



    @ViewInject(R.id.llType4)
    private LinearLayout llType4;
    @ViewInject(R.id.txtBillType)
    private TextView txtBillType;
    @ViewInject(R.id.txtConfirmDischargeTime)
    private TextView txtConfirmDischargeTime;
    @ViewInject(R.id.txtFreight)
    private TextView txtFreight;
    @ViewInject(R.id.txtFreightAmount)
    private TextView txtFreightAmount;
    @ViewInject(R.id.txtFreightPayer)
    private TextView txtFreightPayer;
    @ViewInject(R.id.txtReceiptOfTheInvoice)
    private TextView txtReceiptOfTheInvoice;
    @ViewInject(R.id.txtConfirmOfPayTime)
    private TextView txtConfirmOfPayTime;

    private Activity mContext;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);

        x.view().inject(this);

        ScreenManagerUtil.pushActivity(this);
        mContext = this;

        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        type = getIntent().getStringExtra("type");
        switch (type) {
            case "1":
                llType1.setVisibility(View.VISIBLE);
                break;
            case "3":
                llType2.setVisibility(View.VISIBLE);
                break;
            case "4":
                setBillData();
                llBottom.setVisibility(View.VISIBLE);
                llType3.setVisibility(View.VISIBLE);
                break;
            case "5":
                llType4.setVisibility(View.VISIBLE);
                break;
        }

        //setData();
    }

    /**
     * 设置单据信息
     */
    private void setBillData() {
        for (int i = 0; i < 3; i++) {
            LinearLayout.LayoutParams lpLl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(30));
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            // 单据
            TextView txtDj = new TextView(this);
            txtDj.setText("测试" + i);
            txtDj.setTextSize(14);
            txtDj.setTextColor(ContextCompat.getColor(mContext, R.color.dark_grey));
            txtDj.setGravity(Gravity.LEFT|Gravity.CENTER);

            LinearLayout.LayoutParams lpDj = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lpDj.weight = 1;
            layout.addView(txtDj, lpDj);
            // 单据状态
            TextView txtDjzt = new TextView(this);
            txtDjzt.setText("副本" + i);
            txtDjzt.setTextSize(14);
            txtDjzt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_grey));
            txtDjzt.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lpDjzt = new LinearLayout.LayoutParams(DensityUtil.dip2px(70), LinearLayout.LayoutParams.MATCH_PARENT);
            layout.addView(txtDjzt, lpDjzt);

            // 单据状态
            TextView txtSdsj = new TextView(this);
            txtSdsj.setText("2012-12-12");
            txtSdsj.setTextSize(14);
            txtSdsj.setTextColor(ContextCompat.getColor(mContext, R.color.dark_grey));
            txtSdsj.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lpSdsj = new LinearLayout.LayoutParams(DensityUtil.dip2px(80), LinearLayout.LayoutParams.MATCH_PARENT);
            layout.addView(txtSdsj, lpSdsj);

            // 单据状态
            TextView txtNrsh = new TextView(this);
            txtNrsh.setText("未通过");
            txtNrsh.setTextSize(14);
            txtNrsh.setTextColor(ContextCompat.getColor(mContext, R.color.dark_grey));
            txtNrsh.setGravity(Gravity.CENTER);
            layout.addView(txtNrsh, lpDjzt);
            llType3.addView(layout, lpLl);

            View v = new View(this);
            v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
            LinearLayout.LayoutParams lpv = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(1));
            if (i == 2) {lpv.bottomMargin = DensityUtil.dip2px(10);}
            llType3.addView(v, lpv);
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    private void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
            case R.id.btn1:
                //intent = new Intent(mContext, )
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
