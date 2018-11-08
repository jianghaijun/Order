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
 * 预约
 * 作者：JHJ
 * 日期：2018/10/12 9:26
 * Q Q: 1320666709
 */
public class ReservationCla {
    private ReservationHolder holder;
    private Activity mContext;

    // tab页面
    private FrozenFruitsCla frozenCla;          // 冻品
    private FrozenFruitsCla fruitsCla;          // 水果
    private IndividualCla individualCla;        // 单选预约
    // 子布局
    private View layFrozen, layFruits, layIndividual;
    // View列表
    private ArrayList<View> views;
    // tab页面是否首次加载
    private boolean firstLoadTab2 = true, firstLoadTab3 = true;
    private String[] str = new String[]{"冻品", "水果", "单项预约"};

    public ReservationCla(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new ReservationHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate() {
        initTabPageData();
        initButtonTabData();
        initViewPageData();
    }

    /**
     * 初始化Tab页面信息
     */
    private void initTabPageData() {
        // 将要分页显示的View装入数组中
        LayoutInflater viewLI = LayoutInflater.from(mContext);
        layFrozen = viewLI.inflate(R.layout.layout_public_list, null);
        layFruits = viewLI.inflate(R.layout.layout_public_list, null);
        layIndividual = viewLI.inflate(R.layout.layout_individual, null);

        //每个页面的view数据
        views = new ArrayList<>();
        views.add(layFrozen);
        views.add(layFruits);
        views.add(layIndividual);

        frozenCla = new FrozenFruitsCla(mContext, layFrozen);
        fruitsCla = new FrozenFruitsCla(mContext, layFruits);
        individualCla = new IndividualCla(mContext, layIndividual);
    }

    /**
     * 初始化顶部菜单
     */
    private void initButtonTabData() {
        holder.tlTop.addTab(holder.tlTop.newTab().setText(str[0]));
        holder.tlTop.addTab(holder.tlTop.newTab().setText(str[1]));
        holder.tlTop.addTab(holder.tlTop.newTab().setText(str[2]));
    }

    /**
     * 初始化ViewPage
     */
    private void initViewPageData() {
        holder.vpReservation.setOnPageChangeListener(new MyOnPageChangeListener());
        holder.vpReservation.setAdapter(mPagerAdapter);
        holder.vpReservation.setCurrentItem(0);

        holder.tlTop.setupWithViewPager(holder.vpReservation);

        frozenCla.setDate("0");
        fruitsCla.setDate("1");
        individualCla.setDate();
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
            /*if (arg0 == 1 && firstLoadTab2) {
                firstLoadTab2 = false;
                fruitsCla.setDate("2");
            } else if (arg0 == 2 && firstLoadTab3) {
                firstLoadTab3 = false;
                individualCla.setDate();
            }*/
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
    private class ReservationHolder {
        @ViewInject(R.id.tlTop)
        private TabLayout tlTop;
        @ViewInject(R.id.vpReservation)
        private ViewPager vpReservation;
    }
}
