package com.zx.order.utils;

import android.content.Context;

/**
 * 处理7.0以上手机照片选择问题
 * 作者：JHJ
 * 日期：2018/11/9 16:16
 * Q Q: 1320666709
 */
public class ProviderUtil {
    public static String getFileProviderName(Context context) {
        return context.getPackageName() + ".provider";
    }
}
