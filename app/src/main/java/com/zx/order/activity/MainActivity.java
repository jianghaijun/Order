package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.vpMain)
    private ViewPager vpMain;
    @ViewInject(R.id.actionBar)
    private View actionBar;
    @ViewInject(R.id.bottomNavigationBar)
    private BottomNavigationBar bottomNavigationBar;

    private MeActivity meActivity;
    private EntrustInfoActivity entrustInfoActivity;
    private OrderInfoActivity orderInfoActivity;
    private LatestInfoActivity latestInfoActivity;

    private Activity mContext;

    // 子布局
    private View layoutMe, layoutEntrustInfo, layoutOrderInfo, layoutLatestInfo;
    // View列表
    private ArrayList<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        x.view().inject(this);

        mContext = this;

        //将要分页显示的View装入数组中
        LayoutInflater viewLI = LayoutInflater.from(this);
        layoutMe = viewLI.inflate(R.layout.layout_my, null);
        layoutEntrustInfo = viewLI.inflate(R.layout.layout_entrust_info, null);
        layoutOrderInfo = viewLI.inflate(R.layout.layout_order_info, null);
        layoutLatestInfo = viewLI.inflate(R.layout.layout_order, null);

        //每个页面的view数据
        views = new ArrayList<>();
        views.add(layoutMe);
        views.add(layoutEntrustInfo);
        views.add(layoutOrderInfo);
        views.add(layoutLatestInfo);

        meActivity = new MeActivity(mContext, layoutMe);
        entrustInfoActivity = new EntrustInfoActivity(mContext, layoutEntrustInfo);
        orderInfoActivity = new OrderInfoActivity(mContext, layoutOrderInfo);
        latestInfoActivity = new LatestInfoActivity(mContext, layoutLatestInfo);

        // 设置底部导航按钮
        bottomNavigationBar
            .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
            .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "委托信息").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
            .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "订单信息").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
            .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "最新资讯").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
            .setMode(BottomNavigationBar.MODE_FIXED)
            .setActiveColor("#13227A")
            .setInActiveColor("#F78E62")
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
            .setFirstSelectedPosition(0)
            .initialise();

        // 点击事件
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        vpMain.setCurrentItem(0);
                        break;
                    case 1:
                        vpMain.setCurrentItem(1);
                        break;
                    case 2:
                        vpMain.setCurrentItem(2);
                        break;
                    case 3:
                        vpMain.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });

        vpMain.setOnPageChangeListener(new MyOnPageChangeListener());
        vpMain.setAdapter(mPagerAdapter);
        vpMain.setCurrentItem(0);
        meActivity.setDate(null);
        entrustInfoActivity.setDate();
        latestInfoActivity.setDate();
        orderInfoActivity.setDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            if (arg0 == 2) {
                actionBar.setVisibility(View.GONE);
            } else {
                actionBar.setVisibility(View.VISIBLE);
            }
            bottomNavigationBar.selectTab(arg0);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }
}
