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

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsInfoActivity extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtOrderTitle)
    private TextView txtOrderTitle;
    @ViewInject(R.id.txtStart)
    private TextView txtStart;
    @ViewInject(R.id.llContent)
    private LinearLayout llContent;

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_details_info);

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
        txtOrderTitle.setText("集装箱号：ASDF5454ASDF");
        txtStart.setText("未到港");

        List<String> strList = new ArrayList<>();
        strList.add("商品名称：鲜猕猴桃");
        strList.add("规格：大");
        strList.add("件重：500g/件");
        strList.add("总重：10吨");
        strList.add("单位：吨");
        strList.add("计划数：10吨");
        strList.add("完成数：10吨");
        strList.add("提单号：123456789");
        strList.add("订单编号：123456789");
        strList.add("订单日期：2012-12-12");
        strList.add("入库日期：2012-12-23");
        strList.add("收货月台：大连");
        strList.add("仓库编码：123456789");
        strList.add("作业方式：全天作业");
        strList.add("客户存仓编号：123456789");
        strList.add("铅封号：123456789");
        strList.add("货主：张三");
        strList.add("备注：无");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (String str : strList) {
            TextView txt = new TextView(this);
            txt.setGravity(Gravity.LEFT | Gravity.CENTER);
            txt.setMinHeight(DensityUtil.dip2px(30));
            txt.setPadding(DensityUtil.dip2px(10), 0, 0, 0);
            txt.setText(str);
            txt.setTextSize(14);
            txt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_grey));
            llContent.addView(txt, lp);
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft})
    private void onClick(View v) {
        Intent intent;
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
