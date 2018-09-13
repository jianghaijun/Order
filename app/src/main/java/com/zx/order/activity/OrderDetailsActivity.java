package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.utils.ScreenManagerUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class OrderDetailsActivity extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;

    @ViewInject(R.id.txtCommodityName)
    private TextView txtCommodityName;
    @ViewInject(R.id.txtOrderNum)
    private TextView txtOrderNum;
    @ViewInject(R.id.txtShipCompany)
    private TextView txtShipCompany;
    @ViewInject(R.id.txtBillNum)
    private TextView txtBillNum;
    @ViewInject(R.id.txtBillType)
    private TextView txtBillType;
    @ViewInject(R.id.txtPortTime)
    private TextView txtPortTime;
    @ViewInject(R.id.txtShipName)
    private TextView txtShipName;
    @ViewInject(R.id.txtDestination)
    private TextView txtDestination;
    @ViewInject(R.id.txtInvoiceNum)
    private TextView txtInvoiceNum;
    @ViewInject(R.id.txtAmount)
    private TextView txtAmount;
    @ViewInject(R.id.txtSupplier)
    private TextView txtSupplier;
    @ViewInject(R.id.txtForwardingCompany)
    private TextView txtForwardingCompany;
    @ViewInject(R.id.txtConsignee)
    private TextView txtConsignee;

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_details);

        x.view().inject(this);

        ScreenManagerUtil.pushActivity(this);
        mContext = this;

        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        setData();
    }

    /**
     * 赋值
     */
    private void setData() {
        txtCommodityName.setText("商品名称：县猕猴桃");
        txtOrderNum.setText("提单号：123456");
        txtBillNum.setText("单据号：123456");
        txtBillType.setText("单据类型：送货单");
        txtPortTime.setText("到港时间：2012-12-31 12:45:45");
        txtShipName.setText("船名：长江7号");
        txtDestination.setText("目的港：北京");
        txtInvoiceNum.setText("发票号：12313212");
        txtAmount.setText("金额：4555万");
        txtSupplier.setText("供应商：大连船舶有限公司");
        txtForwardingCompany.setText("货代公司：北京爱尼古拉斯当房");
        txtConsignee.setText("收货人：张三");
        txtShipCompany.setText("船公司：AIHSDFK FAJHSD JALS");
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
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
                intent = new Intent(mContext, DetailsActivity.class);
                if (v.getId() == R.id.btn1) {
                    intent.putExtra("type", "1");
                } else if (v.getId() == R.id.btn2) {
                    intent.putExtra("type", "2");
                } else if (v.getId() == R.id.btn3) {
                    intent.putExtra("type", "3");
                } else if (v.getId() == R.id.btn4) {
                    intent.putExtra("type", "4");
                } else if (v.getId() == R.id.btn5) {
                    intent.putExtra("type", "5");
                }
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
