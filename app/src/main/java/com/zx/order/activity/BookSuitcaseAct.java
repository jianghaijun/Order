package com.zx.order.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.zx.order.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


/**
 * 我的
 */
public class BookSuitcaseAct {
    private EntrustHolder holder;
    private Activity mContext;

    private BookSuitcaseTab unReservationsActivity;
    private BookSuitcaseTab reservationsActivity;
    // 子布局
    private View reservations, unReservations;
    // View列表
    private ArrayList<View> views = new ArrayList<>();
    private String[] str = new String[]{"未预约", "已预约"};
    private boolean firstLoadTab2 = true;

    public BookSuitcaseAct(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new EntrustHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate() {
        //将要分页显示的View装入数组中
        LayoutInflater viewLI = LayoutInflater.from(mContext);
        unReservations = viewLI.inflate(R.layout.layout_order, null);
        reservations = viewLI.inflate(R.layout.layout_order, null);

        views.add(unReservations);
        views.add(reservations);

        unReservationsActivity = new BookSuitcaseTab(mContext, unReservations);
        reservationsActivity = new BookSuitcaseTab(mContext, reservations);

        holder.vpPage.setOnPageChangeListener(new MyOnPageChangeListener());
        holder.vpPage.setAdapter(mPagerAdapter);
        holder.tlTop.setupWithViewPager(holder.vpPage);

        unReservationsActivity.setDate(1);
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

        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }
    };

    /**
     * 页卡切换监听
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int arg0) {
            if (arg0 == 1 && firstLoadTab2) {
                firstLoadTab2 = false;
                reservationsActivity.setDate(2);
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
     * 容纳器
     */
    private class EntrustHolder {
        @ViewInject(R.id.tlTop)
        private TabLayout tlTop;
        @ViewInject(R.id.vpPage)
        private ViewPager vpPage;
    }
}
