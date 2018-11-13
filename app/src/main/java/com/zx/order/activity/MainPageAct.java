package com.zx.order.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.model.ClearanceInspectionModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.SpUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private ImageView imgViewUserAvatar;

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
        // 将要分页显示的View装入数组中
        LayoutInflater viewLI = LayoutInflater.from(this);
        layHomePage = viewLI.inflate(R.layout.layout_home_page, null);
        layReservation = viewLI.inflate(R.layout.layout_reservation, null);
        layOrder = viewLI.inflate(R.layout.layout_order, null);
        layPersonalCenter = viewLI.inflate(R.layout.layout_personal_center, null);

        // 每个页面的view数据
        views = new ArrayList<>();
        views.add(layHomePage);
        views.add(layReservation);
        views.add(layOrder);
        views.add(layPersonalCenter);

        homePageCla = new HomePageCla(mContext, layHomePage);
        reservationCla = new ReservationCla(mContext, layReservation);
        orderCla = new OrderCla(mContext, layOrder);
        personalCenterCla = new PersonalCenterCla(mContext, layPersonalCenter);

        imgViewUserAvatar = (ImageView) layPersonalCenter.findViewById(R.id.ivHead);
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
                orderCla.setDate(false);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("result"); // 图片地址
            if (!TextUtils.isEmpty(path)) {
                uploadIcon(path);
            }
        } else if (requestCode == 1002 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            if (pathList != null && pathList.size() > 0) {
                uploadIcon(pathList.get(0));
            }
        }
    }

    /**
     * 上传头像
     *
     * @param path 图片地址
     */
    private void uploadIcon(String path) {
        LoadingUtils.showLoading(mContext);
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream; charset=utf-8"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fileName", fileName, fileBody)
                .build();
        Request request = new Request.Builder()
                .url(ConstantsUtil.BASE_URL + ConstantsUtil.SUBMIT_HEAD)
                .addHeader("token", (String) SpUtil.get(mContext, ConstantsUtil.TOKEN, ""))
                .post(requestBody)
                .build();
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, "文件上传失败！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final ClearanceInspectionModel model = gson.fromJson(jsonData, ClearanceInspectionModel.class);
                    if (model.isSuccess()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String fileUrl = model.getData().getImageUrl();
                                RequestOptions options = new RequestOptions().circleCrop();
                                Glide.with(mContext).load(fileUrl).apply(options).into(imgViewUserAvatar);
                                ChildThreadUtil.toastMsgHidden(mContext, "头像上传成功");
                                SpUtil.put(mContext, ConstantsUtil.USER_HEAD_URL, fileUrl);
                            }
                        });
                    } else {
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    ChildThreadUtil.toastMsgHidden(mContext, getString(R.string.json_error));
                }
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            /*隐藏软键盘*/
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }

            if (bottomNavigationBar.getCurrentSelectedPosition() == 0) {
                homePageCla.search();
            } else if (bottomNavigationBar.getCurrentSelectedPosition() == 2) {
                orderCla.setDate(true);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
