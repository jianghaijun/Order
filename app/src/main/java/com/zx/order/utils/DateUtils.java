package com.zx.order.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.listener.IntListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import cn.qqtheme.framework.widget.WheelView;

public class DateUtils {
    /**
     * 日期选择
     */
    public static void onYearMonthDayPicker(Activity mActivity, final Button btnDate) {
        final DatePicker picker = new DatePicker(mActivity);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(mActivity, 10));
        picker.setRangeEnd(2100, 1, 31);
        picker.setRangeStart(2000, 1, 31);
        String date = btnDate.getText().toString();
        Date time = StrUtil.isEmpty(date) ? new Date() : DateUtil.parse(date);
        picker.setSelectedItem(DateUtil.year(time), DateUtil.month(time) + 1, DateUtil.dayOfMonth(time));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                btnDate.setText(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 日期选择
     */
    public static void onYearMonthDayPicker(Activity mActivity, final TextView btnDate) {
        final DatePicker picker = new DatePicker(mActivity);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(mActivity, 10));
        picker.setRangeEnd(2100, 1, 31);
        picker.setRangeStart(2000, 1, 31);
        String date = btnDate.getText().toString();
        Date time = StrUtil.isEmpty(date) ? new Date() : DateUtil.parse(date);
        picker.setSelectedItem(DateUtil.year(time), DateUtil.month(time) + 1, DateUtil.dayOfMonth(time));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                btnDate.setText(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 意见选择
     *
     * @param mActivity
     * @param strList
     * @param listener
     */
    public static void optionPicker(Activity mActivity, List<String> strList, final IntListener listener) {
        OptionPicker picker = new OptionPicker(mActivity, strList);
        picker.setCanceledOnTouchOutside(true);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setShadowColor(ContextCompat.getColor(mActivity, R.color.main_bg), 80);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                listener.selectPoint(index);
            }
        });
        picker.show();
    }

    /**
     * 时间选择
     *
     * @param mContext
     * @param txt
     */
    public static void onTimePicker(Activity mContext, final TextView txt) {
        TimePicker picker = new TimePicker(mContext, TimePicker.HOUR_24);
        picker.setUseWeight(false);
        picker.setCycleDisable(false);
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
        picker.setTextPadding(ConvertUtils.toPx(mContext, 15));
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                txt.setText(hour + ":" + minute);
            }
        });
        picker.show();
    }

    /**
     * 日期比较大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 2;
            } else {
                return 3;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取系统当前日期
     * yyyy-MM-dd
     *
     * @param lData
     * @return
     */
    public static String setDataToStr(long lData) {
        Date date = DateUtil.date(lData == 0 ? System.currentTimeMillis() : lData);
        String strDate = DateUtil.format(date, "yyyy-MM-dd");
        return strDate;
    }

    /**
     * 获取系统当前日期
     * yyyy-MM-dd
     *
     * @param lData
     * @return
     */
    public static String setDataToStr2(long lData) {
        Date date = DateUtil.date(lData == 0 ? System.currentTimeMillis() : lData);
        String strDate = DateUtil.format(date, "yyyy-MM-dd hh:mm");
        return strDate;
    }

}
