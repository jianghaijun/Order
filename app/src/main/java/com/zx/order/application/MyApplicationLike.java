package com.zx.order.application;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import org.litepal.LitePal;
import org.xutils.x;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * application
 */
public class MyApplicationLike extends DefaultApplicationLike {
    public static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(getApplication());
        x.Ext.setDebug(false);
        LitePal.initialize(getApplication());
        instance = getApplication();

        // 腾讯热更新初始化(appId:后台申请地址)
        Bugly.init(getApplication(), "997c283347", false);

        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(getApplication(), null);
    }

    public MyApplicationLike(Application application, int tinkerFlags,
                             boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                             long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag,
                applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);

        // 安装tinker
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

}
