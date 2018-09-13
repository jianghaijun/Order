package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.popwindow.ProcessPopupWindow;
import com.zx.order.utils.ScreenManagerUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends BaseActivity {
    @ViewInject(R.id.vpOrderList)
    private ViewPager vpOrderList;
    @ViewInject(R.id.llTopBtn)
    private LinearLayout llTopBtn;
    @ViewInject(R.id.btnLeft)
    private Button btnLeft;
    @ViewInject(R.id.btnRight)
    private Button btnRight;

    // 子布局
    private View laySeaborneGoods, layAirCargo;
    // View列表
    private ArrayList<View> views;

    private OrderListTabActivity seaborneGoodsActivity;
    private OrderListTabActivity airCargoActivity;

    private Activity mContext;

    private boolean isFirstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_list);
        x.view().inject(this);

        ScreenManagerUtil.pushActivity(this);
        mContext = this;

        List<String> strList = new ArrayList<>();
        strList.add("全部");
        strList.add("未到港");
        strList.add("待提箱");
        strList.add("待还箱");
        strList.add("已还箱");

        setTopBtn(strList);

        //将要分页显示的View装入数组中
        LayoutInflater viewLI = LayoutInflater.from(this);
        laySeaborneGoods = viewLI.inflate(R.layout.layout_order, null);
        layAirCargo = viewLI.inflate(R.layout.layout_order, null);

        //每个页面的view数据
        views = new ArrayList<>();
        views.add(laySeaborneGoods);
        views.add(layAirCargo);

        seaborneGoodsActivity = new OrderListTabActivity(mContext, laySeaborneGoods);
        airCargoActivity = new OrderListTabActivity(mContext, layAirCargo);

        vpOrderList.setOnPageChangeListener(new MyOnPageChangeListener());
        vpOrderList.setAdapter(mPagerAdapter);
        vpOrderList.setCurrentItem(0);

        seaborneGoodsActivity.setDate();
    }

    /**
     * 设置顶部按钮
     *
     * @param strList
     */
    private void setTopBtn(List<String> strList) {
        if (strList == null || strList.size() == 0) {
            return;
        }

        llTopBtn.removeAllViews();
        final List<Button> buttonList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            final Button btn = new Button(this);
            btn.setText(strList.get(i));
            if (i == 0) {
                btn.setTextColor(ContextCompat.getColor(mContext, R.color.main_bg));
            } else {
                btn.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            }
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            btn.setTextSize(14);
            btn.setBackground(null);
            buttonList.add(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTabStart(buttonList, btn);
                }
            });
            llTopBtn.addView(btn, lp);
        }
    }

    /**
     * tab点击事件
     */
    private void setTabStart(List<Button> buttonList, Button btn) {
        for (Button button : buttonList) {
            button.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
        btn.setTextColor(ContextCompat.getColor(mContext, R.color.main_bg));
    }

    /**
     * 填充ViewPager的数据适配器
     */
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }
    };

    /**
     * 页卡切换监听
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int arg0) {
            if (arg0 == 0) {
                btnLeft.setBackground(ContextCompat.getDrawable(mContext, R.drawable.btn_blue));
                btnLeft.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                btnRight.setBackground(ContextCompat.getDrawable(mContext, R.drawable.btn_white));
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.main_bg));
            } else {
                if (isFirstLoad) {
                    isFirstLoad = false;
                    airCargoActivity.setDate();
                }

                btnLeft.setBackground(ContextCompat.getDrawable(mContext, R.drawable.btn_white));
                btnLeft.setTextColor(ContextCompat.getColor(mContext, R.color.main_bg));
                btnRight.setBackground(ContextCompat.getDrawable(mContext, R.drawable.btn_blue));
                btnRight.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnBack, R.id.btnLeft, R.id.btnRight, R.id.txtScreen})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnBack:
                this.finish();
                break;
            case R.id.btnLeft:
                vpOrderList.setCurrentItem(0);
                break;
            case R.id.btnRight:
                vpOrderList.setCurrentItem(1);
                break;
            // 筛选
            case R.id.txtScreen:
                ProcessPopupWindow pop = new ProcessPopupWindow(mContext);
                pop.showAtDropDownRight(v);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
