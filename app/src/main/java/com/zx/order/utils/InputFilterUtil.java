package com.zx.order.utils;

import android.app.Activity;
import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 限制表情输入
 * 作者：JHJ
 * 日期：2018/11/13 11:32
 * Q Q: 1320666709
 */
public class InputFilterUtil {

    public static InputFilter[] inputFilter(final Activity mActivity) {
        InputFilter emojiFilter = new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    ToastUtil.showShort(mActivity, "不支持输入表情!");
                    return "";
                }
                return null;
            }
        };
        return new InputFilter[]{emojiFilter};
    }
}
