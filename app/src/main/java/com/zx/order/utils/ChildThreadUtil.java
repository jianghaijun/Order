package com.zx.order.utils;

import android.app.Activity;
import android.content.Intent;

import com.zx.order.activity.LoginAct;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 网络请求
 * 作者：JHJ
 * 日期：2018/10/30 14:54
 * Q Q: 1320666709
 */
public class ChildThreadUtil {

    /**
     * 弹出消息---隐藏加载框
     *
     * @param mContext 当前上下文
     * @param msg      提示信息
     */
    public static void toastMsgHidden(final Activity mContext, final String msg) {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadingUtils.hideLoading();
                ToastUtil.showShort(mContext, msg);
            }
        });
    }

    /**
     * 弹出消息(判断token是否过期)---隐藏加载框
     *
     * @param mContext 当前上下文
     * @param msg      提示信息
     * @param code     返回code码
     */
    public static void checkTokenHidden(final Activity mContext, final String msg, final int code) {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadingUtils.hideLoading();
                switch (code) {
                    case 3003:
                    case 3004:
                        // Token异常重新登录
                        ToastUtil.showLong(mContext, msg);
                        SpUtil.put(mContext, ConstantsUtil.IS_LOGIN_SUCCESSFUL, false);
                        ScreenManagerUtil.popAllActivityExceptOne();
                        mContext.startActivity(new Intent(mContext, LoginAct.class));
                        break;
                    default:
                        ToastUtil.showLong(mContext, msg);
                        break;
                }
            }
        });
    }

    /**
     * 获取POST Request
     *
     * @param mContext 当前上下文
     * @param strUrl   请求地址
     * @param param    请求参数
     * @return
     */
    public static Request getRequest(Activity mContext, String strUrl, String param) {
        RequestBody requestBody = RequestBody.create(ConstantsUtil.JSON, param);
        Request request = new Request.Builder()
                .url(ConstantsUtil.BASE_URL + strUrl)
                .addHeader("token", (String) SpUtil.get(mContext, ConstantsUtil.TOKEN, ""))
                .post(requestBody)
                .build();
        return request;
    }

    /**
     * 获取GET Request
     *
     * @param mContext 当前上下文
     * @param strUrl   请求地址
     * @return
     */
    public static Request getRequestGet(Activity mContext, String strUrl) {
        Request request = new Request.Builder()
                .url(ConstantsUtil.BASE_URL + strUrl)
                .addHeader("token", (String) SpUtil.get(mContext, ConstantsUtil.TOKEN, ""))
                .get()
                .build();
        return request;
    }
}
