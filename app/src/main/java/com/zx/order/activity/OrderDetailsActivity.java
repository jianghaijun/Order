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

public class OrderDetailsActivity extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.llMain)
    private LinearLayout llMain;

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
        List<String[]> dataList = new ArrayList<>();
        dataList.add(new String[]{"集装箱号：", "ASJLDKFKALJSF", "未到港"});
        dataList.add(new String[]{"商品名称：", "县猕猴桃"});
        dataList.add(new String[]{"提单号：", "123456"});
        dataList.add(new String[]{"单据号：", "123456"});
        dataList.add(new String[]{"单据类型：", "送货单"});
        dataList.add(new String[]{"到港时间：", "2012-12-31 12:45:45"});
        dataList.add(new String[]{"船名：", "长江7号"});
        dataList.add(new String[]{"目的港：", "北京"});
        dataList.add(new String[]{"发票号：", "12313212"});
        dataList.add(new String[]{"金额：", "4555万"});
        dataList.add(new String[]{"供应商：", "大连船舶有限公司"});
        dataList.add(new String[]{"货代公司：", "北京爱尼古拉斯当房"});
        dataList.add(new String[]{"收货人：", "张三"});
        dataList.add(new String[]{"船公司：", "AIHSDFK FAJHSD JALS"});
        dataList.add(new String[]{"申报时间：", "2012-12-31"});

        if (dataList != null && dataList.size() > 0) {
            llMain.removeAllViews();
            int size = dataList.size();
            LinearLayout.LayoutParams lpTitleLine = new LinearLayout.LayoutParams(DensityUtil.dip2px(80), DensityUtil.dip2px(40));
            LinearLayout.LayoutParams lpLine = new LinearLayout.LayoutParams(DensityUtil.dip2px(80), DensityUtil.dip2px(30));
            for (int i = 0; i <  size; i++) {
                String[] str = dataList.get(i);
                if (i == 0) {
                    // 添加头信息
                    LinearLayout llHead = new LinearLayout(this);
                    llHead.setOrientation(LinearLayout.HORIZONTAL);
                    llHead.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams lpHead = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lpHead.setMargins(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), 0);
                    // 头部添加数据
                    TextView txtTitle = new TextView(this);
                    txtTitle.setText(str[0]);
                    txtTitle.setGravity(Gravity.CENTER);
                    txtTitle.setTextColor(ContextCompat.getColor(this, R.color.black));
                    txtTitle.setTextSize(14);
                    llHead.addView(txtTitle, lpTitleLine);
                    TextView txtTitleContext = new TextView(this);
                    LinearLayout.LayoutParams lpTitleContext = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lpTitleContext.weight = 1;
                    txtTitleContext.setText(str[1]);
                    txtTitleContext.setGravity(Gravity.LEFT|Gravity.CENTER);
                    txtTitleContext.setTextColor(ContextCompat.getColor(this, R.color.black));
                    txtTitleContext.setTextSize(14);
                    txtTitleContext.setMinHeight(DensityUtil.dip2px(40));
                    llHead.addView(txtTitleContext, lpTitleContext);
                    TextView txtTitleStates = new TextView(this);
                    LinearLayout.LayoutParams lpTitleStates = new LinearLayout.LayoutParams(DensityUtil.dip2px(50), LinearLayout.LayoutParams.WRAP_CONTENT);
                    lpTitleStates.setMargins(DensityUtil.dip2px(10), 0, DensityUtil.dip2px(10), 0);
                    txtTitleStates.setText(str[2]);
                    txtTitleStates.setGravity(Gravity.CENTER);
                    txtTitleStates.setTextColor(ContextCompat.getColor(this, R.color.main_bg));
                    txtTitleStates.setTextSize(14);
                    llHead.addView(txtTitleStates, lpTitleStates);
                    llMain.addView(llHead, lpHead);
                    // 添加分割线
                    View viewTitle = new View(this);
                    LinearLayout.LayoutParams lpTitleView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(1));
                    lpTitleView.setMargins(DensityUtil.dip2px(1), 0, DensityUtil.dip2px(1), 0);
                    viewTitle.setBackgroundColor(ContextCompat.getColor(mContext, R.color.main_bg));
                    llMain.addView(viewTitle, lpTitleView);
                }

                LinearLayout llBody = new LinearLayout(this);
                llBody.setOrientation(LinearLayout.HORIZONTAL);
                llBody.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams lpBody = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lpBody.setMargins(DensityUtil.dip2px(5), 0, DensityUtil.dip2px(5), 0);
                // Body添加数据
                TextView txtBody = new TextView(this);
                txtBody.setText(str[0]);
                txtBody.setGravity(Gravity.CENTER);
                txtBody.setTextColor(ContextCompat.getColor(this, R.color.dark_grey));
                txtBody.setTextSize(14);
                llBody.addView(txtBody, lpLine);
                TextView txtBodyContext = new TextView(this);
                LinearLayout.LayoutParams lpBodyContext = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lpBodyContext.weight = 1;
                txtBodyContext.setText(str[1]);
                txtBodyContext.setGravity(Gravity.LEFT|Gravity.CENTER);
                txtBodyContext.setTextColor(ContextCompat.getColor(this, R.color.dark_grey));
                txtBodyContext.setTextSize(14);
                txtBodyContext.setMinHeight(DensityUtil.dip2px(30));
                llBody.addView(txtBodyContext, lpBodyContext);
                llMain.addView(llBody, lpBody);
            }
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.btn2, R.id.btn3, R.id.btn4})
    private void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
                intent = new Intent(mContext, DetailsActivity.class);
                if (v.getId() == R.id.btn2) {
                    intent.putExtra("type", "1");
                } else if (v.getId() == R.id.btn3) {
                    intent.putExtra("type", "3");
                } else if (v.getId() == R.id.btn4) {
                    intent.putExtra("type", "4");
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
