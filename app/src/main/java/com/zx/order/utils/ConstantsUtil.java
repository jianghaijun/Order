package com.zx.order.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * 常量类
 */

public class ConstantsUtil {
    // 中交路径
    public static final String BASE_URL = "http://192.168.1.118:8080/web/";
    // 前缀
    public static String prefix = "";
    // ACCOUNT_ID
    public static String ACCOUNT_ID = "tongren_qyh_app_id";
    // 参数格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    // OkHttpClient
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30000L, TimeUnit.MILLISECONDS)
            .readTimeout(30000L, TimeUnit.MILLISECONDS)
            .build();

    // 用户id
    public static String USER_ID = "USER_ID";
    // token
    public static String TOKEN = "TOKEN";
    // 是否登录成功
    public static final String IS_LOGIN_SUCCESSFUL = "IS_LOGIN_SUCCESSFUL";

    // 登录
    public static final String LOGIN = prefix + "user/" + "login";

    // 文件存储路径
    public static final String SAVE_PATH = "/mnt/sdcard/zxOrder/";
    // 下载apk文件名称
    public static final String APK_NAME = "zxOrder.apk";
}
