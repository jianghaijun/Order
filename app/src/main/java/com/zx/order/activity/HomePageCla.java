package com.zx.order.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.zx.order.R;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import cn.hutool.core.util.StrUtil;


/**
 * 首页
 * 作者：JHJ
 * 日期：2018/10/9 16:04
 * Q Q: 1320666709
 */
public class HomePageCla {
    private HomePageActHolder holder;
    private Activity mContext;

    private HomePageTab1Cla homePageTab1; // 通关状态
    private boolean firstLoadTab2 = true;

    // 子布局
    private View layTab1, layTab2;
    // View列表
    private ArrayList<View> views;
    private String[] str = new String[]{"通关状态", "关注信息"};

    public HomePageCla(Activity mContext, View layoutMe) {
        this.mContext = mContext;
        holder = new HomePageActHolder();
        x.view().inject(holder, layoutMe);
    }

    /**
     * 赋值
     */
    public void setDate() {
        LayoutInflater viewLI = LayoutInflater.from(mContext);
        layTab1 = viewLI.inflate(R.layout.layout_home_page_tab1, null);
        layTab2 = viewLI.inflate(R.layout.layout_empty, null);

        // 每个页面的view数据
        views = new ArrayList<>();
        views.add(layTab1);
        views.add(layTab2);

        homePageTab1 = new HomePageTab1Cla(mContext, layTab1);

        holder.vpHomePage.setOnPageChangeListener(new MyOnPageChangeListener());
        holder.vpHomePage.setAdapter(mPagerAdapter);
        holder.vpHomePage.setCurrentItem(0);

        holder.tlTop.setupWithViewPager(holder.vpHomePage);

        homePageTab1.setDate("");
    }

    /**
     * 搜索
     */
    public void search() {
        EditText edtSearchContext = (EditText) layTab1.findViewById(R.id.edtSearchContext);
        if (StrUtil.isEmpty(edtSearchContext.getText().toString())) {
            ToastUtil.showShort(mContext, "请输入检索条件！");
        } else {
            homePageTab1.setDate(edtSearchContext.getText().toString());
        }
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
    private class HomePageActHolder {
        @ViewInject(R.id.vpHomePage)
        private ViewPager vpHomePage;
        @ViewInject(R.id.tlTop)
        private TabLayout tlTop;
    }
}
