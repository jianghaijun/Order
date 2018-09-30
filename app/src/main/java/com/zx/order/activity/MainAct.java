package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.moos.library.BottomBarLayout;
import com.moos.library.BottomTabView;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class MainAct extends BaseActivity {
    @ViewInject(R.id.vpMain)
    private ViewPager vpMain;
    @ViewInject(R.id.actionBar)
    private View actionBar;
    @ViewInject(R.id.bottomBar)
    private BottomBarLayout bottomBar;

    private HomePageAct homePageAct; // 首页
    private BookSuitcaseAct bookSuitcaseAct;
    private AddAct addAct;
    private OrderInfoAct orderInfoAct;
    private MeAct meAct;

    private boolean firstLoadTab2 = true, firstLoadTab3 = true, firstLoadTab4 = true, firstLoadTab5 = true;
    private Activity mContext;

    // 子布局
    private View layHomePage, layBookSuitcase, layAdd, layOrderInfo, layMe;
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
        layHomePage = viewLI.inflate(R.layout.layout_home_page, null);
        layBookSuitcase = viewLI.inflate(R.layout.layout_book_suitcase, null);
        layAdd = viewLI.inflate(R.layout.layout_add, null);
        layOrderInfo = viewLI.inflate(R.layout.layout_order_info, null);
        layMe = viewLI.inflate(R.layout.layout_order, null);

        //每个页面的view数据
        views = new ArrayList<>();
        views.add(layHomePage);
        views.add(layBookSuitcase);
        views.add(layAdd);
        views.add(layOrderInfo);
        views.add(layMe);

        homePageAct = new HomePageAct(mContext, layHomePage);
        bookSuitcaseAct = new BookSuitcaseAct(mContext, layBookSuitcase);
        addAct = new AddAct(mContext, layAdd);
        orderInfoAct = new OrderInfoAct(mContext, layOrderInfo);
        meAct = new MeAct(mContext, layMe);

        BottomTabView tabHome = new BottomTabView(this);
        tabHome.setSelectedTabIcon(R.drawable.info_check);
        tabHome.setTabIcon(R.drawable.info_un_check);
        tabHome.setSelectedColor(ContextCompat.getColor(this, R.color.bottom_tab_select));
        tabHome.setUnselectedColor(ContextCompat.getColor(this, R.color.bottom_tab_un_select));
        tabHome.setTabTitle("首页");

        BottomTabView suitcaseReservation = new BottomTabView(this);
        suitcaseReservation.setSelectedTabIcon(R.drawable.fly_check);
        suitcaseReservation.setTabIcon(R.drawable.fly_un_check);
        suitcaseReservation.setSelectedColor(ContextCompat.getColor(this, R.color.bottom_tab_select));
        suitcaseReservation.setUnselectedColor(ContextCompat.getColor(this, R.color.bottom_tab_un_select));
        suitcaseReservation.setTabTitle("提箱预约");

        BottomTabView add = new BottomTabView(this);
        add.setSelectedTabIcon(R.drawable.add_check);
        add.setTabIcon(R.drawable.add_un_check);
        add.setTabIconSize(30);
        add.setSelectedColor(ContextCompat.getColor(this, R.color.bottom_tab_select));
        add.setUnselectedColor(ContextCompat.getColor(this, R.color.bottom_tab_un_select));
        add.setTabIconOnly(true);

        BottomTabView orderInformation = new BottomTabView(this);
        orderInformation.setSelectedTabIcon(R.drawable.order_check);
        orderInformation.setTabIcon(R.drawable.order_un_check);
        orderInformation.setSelectedColor(ContextCompat.getColor(this, R.color.bottom_tab_select));
        orderInformation.setUnselectedColor(ContextCompat.getColor(this, R.color.bottom_tab_un_select));
        orderInformation.setTabTitle("订单信息");

        BottomTabView me = new BottomTabView(this);
        me.setSelectedTabIcon(R.drawable.me_check);
        me.setTabIcon(R.drawable.me_un_check);
        me.setSelectedColor(ContextCompat.getColor(this, R.color.bottom_tab_select));
        me.setUnselectedColor(ContextCompat.getColor(this, R.color.bottom_tab_un_select));
        me.setTabTitle("我的");

        bottomBar
                .addTab(tabHome)
                .addTab(suitcaseReservation)
                .addTab(add)
                .addTab(orderInformation)
                .addTab(me)
                .create(new BottomBarLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(BottomTabView tab) {
                        // 去掉bottomBar.bindViewPager(vpMain)关联---快速同时点击多个按钮会闪屏
                        switch (tab.getTabPosition()) {
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
                            case 4:
                                vpMain.setCurrentItem(4);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(BottomTabView tab) {

                    }

                    @Override
                    public void onTabReselected(BottomTabView tab) {

                    }
                });
        vpMain.setOnPageChangeListener(new MyOnPageChangeListener());
        vpMain.setAdapter(mPagerAdapter);
        vpMain.setCurrentItem(0);
        homePageAct.setDate();
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
            bottomBar.setCurrentTab(arg0); // 设置选中底部tab
            if (arg0 == 1 && firstLoadTab2) {
                firstLoadTab2 = false;
                bookSuitcaseAct.setDate();
            } else if (arg0 == 2 && firstLoadTab3) {
                firstLoadTab3 = false;
                addAct.setDate();
            } else if (arg0 == 3 && firstLoadTab4) {
                firstLoadTab4 = false;
                orderInfoAct.setDate(1);
            } else if (arg0 == 4 && firstLoadTab5) {
                firstLoadTab5 = false;
                meAct.setDate();
            }

            if (arg0 == 3) {
                actionBar.setVisibility(View.GONE);
            } else {
                actionBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }
}
