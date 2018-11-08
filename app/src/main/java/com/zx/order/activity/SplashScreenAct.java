package com.zx.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.SpUtil;

import org.xutils.x;

/**
 * 启动界面
 * 作者：JHJ
 * 日期：2018/10/19 16:14
 * Q Q: 1320666709
 */
public class SplashScreenAct extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);
        x.view().inject(this);

        // 闪屏的核心代码
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity();
            }
        }, 2000); // 启动动画持续2秒钟
    }

    /**
     * 启动下一界面
     */
    private void startNextActivity() {
        boolean isLoginFlag = (boolean) SpUtil.get(this, ConstantsUtil.IS_LOGIN_SUCCESSFUL, false);
        if (isLoginFlag) {
            startActivity(new Intent(this, MainPageAct.class));
        } else {
            startActivity(new Intent(this, LoginAct.class));
        }
        this.finish();
    }
}
