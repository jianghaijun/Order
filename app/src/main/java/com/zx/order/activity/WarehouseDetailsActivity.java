package com.zx.order.activity;

import android.app.Activity;
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

public class WarehouseDetailsActivity extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.llMain)
    private LinearLayout llMain;

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_warehouse_details);

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
        for (int i = 0; i < 30; i++) {
            // 添加一条
            LinearLayout.LayoutParams lpLl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(30));
            lpLl.setMargins(DensityUtil.dip2px(1), DensityUtil.dip2px(1), DensityUtil.dip2px(1), DensityUtil.dip2px(1));

            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.touch_bg));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams lpTxt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lpTxt.weight = 1;

            TextView txtType = new TextView(mContext);
            txtType.setGravity(Gravity.CENTER);
            txtType.setText("肉类" + i);
            txtType.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            txtType.setTextSize(14);
            linearLayout.addView(txtType, lpTxt);

            TextView txtWeight = new TextView(mContext);
            txtWeight.setGravity(Gravity.CENTER);
            txtWeight.setText(i + 10 + "吨");
            txtWeight.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            txtWeight.setTextSize(14);
            linearLayout.addView(txtWeight, lpTxt);

            TextView txtNum = new TextView(mContext);
            txtNum.setGravity(Gravity.CENTER);
            txtNum.setText(i + 100 + "箱");
            txtNum.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            txtNum.setTextSize(14);
            linearLayout.addView(txtNum, lpTxt);

            if (i == 29) {
                lpLl.bottomMargin = DensityUtil.dip2px(10);
            }
            llMain.addView(linearLayout, lpLl);
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft})
    private void onClick(View v) {
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
