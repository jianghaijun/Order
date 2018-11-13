package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.base.BaseModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.InputFilterUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.SpUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 修改密码
 * 作者：JHJ
 * 日期：2018/10/19 15:26
 * Q Q: 1320666709
 */
public class UpdatePassWordAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageView imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.edtOldPassWord)
    private EditText edtOldPassWord;
    @ViewInject(R.id.edtNewPassWord)
    private EditText edtNewPassWord;
    @ViewInject(R.id.edtQueryPassWord)
    private EditText edtQueryPassWord;

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update_pass_word);

        x.view().inject(this);
        ScreenManagerUtil.pushActivity(this);
        mContext = this;

        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));
        txtTitle.setText("修改密码");

        edtOldPassWord.setFilters(InputFilterUtil.inputFilter(this));
        edtNewPassWord.setFilters(InputFilterUtil.inputFilter(this));
        edtQueryPassWord.setFilters(InputFilterUtil.inputFilter(this));
    }

    /**
     * 密码校验
     */
    private void checkPassWord() {
        String oldPassWord = edtOldPassWord.getText().toString().trim();
        String newPassWord = edtNewPassWord.getText().toString().trim();
        String queryPassWord = edtQueryPassWord.getText().toString().trim();
        if (StrUtil.isEmpty(oldPassWord)) {
            ToastUtil.showShort(this, "原始密码不能为空!");
        } else if (StrUtil.isEmpty(newPassWord)) {
            ToastUtil.showShort(this, "新密码不能为空!");
        } else if (!newPassWord.equals(queryPassWord)) {
            ToastUtil.showShort(this, "两次输入密码不一致!");
        } else {
            updatePassWord(newPassWord, oldPassWord);
        }
    }

    /**
     * 修改密码
     *
     * @param newPassWord
     * @param oldPassWord
     */
    private void updatePassWord(String newPassWord, String oldPassWord) {
        LoadingUtils.showLoading(this);
        JSONObject obj = new JSONObject();
        obj.put("userPwdOld", oldPassWord);
        obj.put("userPwd", newPassWord);
        obj.put("userPwdNew", newPassWord);
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.UPDATE_PASSWORD, obj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    BaseModel model = gson.fromJson(jsonData, BaseModel.class);
                    if (model.isSuccess()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LoadingUtils.hideLoading();
                                // Token异常重新登录
                                ToastUtil.showLong(mContext, "密码修改成功请重新登录！");
                                SpUtil.put(mContext, ConstantsUtil.IS_LOGIN_SUCCESSFUL, false);
                                ScreenManagerUtil.popAllActivityExceptOne();
                                startActivity(new Intent(mContext, LoginAct.class));
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

    @Event({R.id.imgBtnLeft, R.id.btnQueryUpdate})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
            case R.id.btnQueryUpdate:
                if (JudgeNetworkIsAvailable.isNetworkAvailable(this)) {
                    checkPassWord();
                } else {
                    ToastUtil.showShort(this, getString(R.string.not_network));
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.popActivity(this);
    }
}
