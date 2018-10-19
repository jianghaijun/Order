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
import com.zx.order.utils.ScreenManagerUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 主界面
 * 作者：JHJ
 * 日期：2018/10/9 15:13
 * Q Q: 1320666709
 */
public class MainPageAct extends BaseActivity {
    @ViewInject(R.id.vpMain)
    private ViewPager vpMain;
    @ViewInject(R.id.bottomNavigationBar)
    private BottomNavigationBar bottomNavigationBar;
    // tab页面
    private HomePageCla homePageCla; // 首页
    private ReservationCla reservationCla; // 预约
    private OrderCla orderCla; // 订单
    private PersonalCenterCla personalCenterCla; // 个人中心
    // tab页面是否首次加载
    private boolean firstLoadTab2 = true, firstLoadTab3 = true, firstLoadTab4 = true;
    private Activity mContext;

    // 子布局
    private View layHomePage, layReservation, layOrder, layPersonalCenter;
    // View列表
    private ArrayList<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_page);
        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        initTabPageData();
        initButtonTabData();
        initViewPageData();
    }

    /**
     * 初始化Tab页面信息
     */
    private void initTabPageData() {
        //将要分页显示的View装入数组中
        LayoutInflater viewLI = LayoutInflater.from(this);
        layHomePage = viewLI.inflate(R.layout.layout_home_page, null);
        layReservation = viewLI.inflate(R.layout.layout_reservation, null);
        layOrder = viewLI.inflate(R.layout.layout_order, null);
        layPersonalCenter = viewLI.inflate(R.layout.layout_personal_center, null);

        //每个页面的view数据
        views = new ArrayList<>();
        views.add(layHomePage);
        views.add(layReservation);
        views.add(layOrder);
        views.add(layPersonalCenter);

        homePageCla = new HomePageCla(mContext, layHomePage);
        reservationCla = new ReservationCla(mContext, layReservation);
        orderCla = new OrderCla(mContext, layOrder);
        personalCenterCla = new PersonalCenterCla(mContext, layPersonalCenter);
    }

    /**
     * 初始化底部菜单
     */
    private void initButtonTabData() {
        // 设置底部导航按钮
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.info_check, "首页").setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.info_un_check)))
                .addItem(new BottomNavigationItem(R.drawable.fly_check, "预约").setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.fly_un_check)))
                .addItem(new BottomNavigationItem(R.drawable.order_check, "订单").setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.order_un_check)))
                .addItem(new BottomNavigationItem(R.drawable.me_check, "个人中心").setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.me_un_check)))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor("#0099FF")
                .setInActiveColor("#8A8A8A")
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setFirstSelectedPosition(0)
                .initialise();

        // 点击事件
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                setCurrentItem(position);
            }
        });
    }

    /**
     * 设置底部菜单选中Tab
     *
     * @param position
     */
    private void setCurrentItem(int position) {
        vpMain.setCurrentItem(position);
    }

    /**
     * 初始化ViewPage
     */
    private void initViewPageData() {
        vpMain.setOnPageChangeListener(new MyOnPageChangeListener());
        vpMain.setAdapter(mPagerAdapter);
        vpMain.setCurrentItem(0);

        // 首页数据
        homePageCla.setDate();
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
            bottomNavigationBar.selectTab(arg0); // 设置选中底部tab
            if (arg0 == 1 && firstLoadTab2) {
                firstLoadTab2 = false;
                reservationCla.setDate();
            } else if (arg0 == 2 && firstLoadTab3) {
                firstLoadTab3 = false;
                orderCla.setDate();
            } else if (arg0 == 3 && firstLoadTab4) {
                firstLoadTab4 = false;
                personalCenterCla.setDate();
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
