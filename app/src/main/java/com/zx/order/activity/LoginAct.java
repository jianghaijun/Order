package com.zx.order.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.google.gson.Gson;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.listener.PermissionListener;
import com.zx.order.model.LoginModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.SpUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 登录界面
 * 作者：JHJ
 * 日期：2018/10/31 11:36
 * Q Q: 1320666709
 */
public class LoginAct extends BaseActivity {
    @ViewInject(R.id.kenBurnsView)
    private KenBurnsView kenBurnsView;
    @ViewInject(R.id.imgLogo)
    private ImageView imgLogo;
    @ViewInject(R.id.shadow)
    private View shadow;
    @ViewInject(R.id.llSignUp)
    private LinearLayout llSignUp;
    @ViewInject(R.id.rlFormLogin)
    private RelativeLayout rlFormLogin;
    @ViewInject(R.id.edtUserName)
    private EditText edtUserName;
    @ViewInject(R.id.edtPassWord)
    private EditText edtPassWord;

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        x.view().inject(this);

        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        String userId = (String) SpUtil.get(mContext, ConstantsUtil.USER_ID, "");
        edtUserName.setText(userId);

        RandomTransitionGenerator generator = new RandomTransitionGenerator(20000, new AccelerateDecelerateInterpolator());
        kenBurnsView.setTransitionGenerator(generator);
        imgLogo.animate().setStartDelay(1000).setDuration(2000).alpha(1).start();
        shadow.animate().setStartDelay(1000).setDuration(2000).alpha(0.6f).start();
        llSignUp.animate().setStartDelay(1500).setDuration(2000).alpha(1).start();
        rlFormLogin.animate().translationY(DensityUtil.getScreenHeight()).setStartDelay(0).setDuration(0).start();
        rlFormLogin.animate().translationY(0).setDuration(1500).alpha(1).setStartDelay(1500).start();

        if (Build.VERSION.SDK_INT >= 23) {
            requestAuthority(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, new PermissionListener() {
                @Override
                public void agree() {

                }

                @Override
                public void refuse(List<String> refusePermission) {
                    ToastUtil.showLong(mContext, "您已拒绝拍照权限!");
                }
            });
        }
    }

    /**
     * 登录
     */
    private void Login() {
        LoadingUtils.showLoading(mContext);
        JSONObject boj = new JSONObject();
        boj.put("userId", edtUserName.getText().toString().trim());
        boj.put("loginType", "1");
        boj.put("userPwd", edtPassWord.getText().toString().trim());
        boj.put("accountId", ConstantsUtil.ACCOUNT_ID);
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.LOGIN, boj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final LoginModel loginModel = gson.fromJson(jsonData, LoginModel.class);
                    if (loginModel.isSuccess()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 存储登录信息
                                SpUtil.put(mContext, ConstantsUtil.TOKEN, loginModel.getData().getToken());
                                SpUtil.put(mContext, ConstantsUtil.IS_LOGIN_SUCCESSFUL, true);
                                SpUtil.put(mContext, ConstantsUtil.USER_NAME, loginModel.getData().getUserInfo().getRealName());
                                SpUtil.put(mContext, ConstantsUtil.USER_HEAD_URL, loginModel.getData().getUserInfo().getImageUrl());
                                SpUtil.put(mContext, ConstantsUtil.USER_ID, edtUserName.getText().toString());
                                LoadingUtils.hideLoading();
                                startActivity(new Intent(mContext, MainPageAct.class));
                                LoginAct.this.finish();
                            }
                        });
                    } else {
                        ChildThreadUtil.checkTokenHidden(mContext, loginModel.getMessage(), loginModel.getCode());
                    }
                } else {
                    ChildThreadUtil.toastMsgHidden(mContext, getString(R.string.json_error));
                }
            }
        });
    }

    @Event({R.id.btnLogin})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (StrUtil.isEmpty(edtUserName.getText().toString().trim())) {
                    ToastUtil.showShort(this, "请输入用户名！");
                } else if (StrUtil.isEmpty(edtPassWord.getText().toString().trim())) {
                    ToastUtil.showShort(this, "请输入密码！");
                } else {
                    if (JudgeNetworkIsAvailable.isNetworkAvailable(this)) {
                        Login();
                    } else {
                        ToastUtil.showShort(mContext, getString(R.string.not_network));
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.popActivity(this);
    }
}
