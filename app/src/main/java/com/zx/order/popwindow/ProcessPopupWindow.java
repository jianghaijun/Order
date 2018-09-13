package com.zx.order.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.listener.IntListener;
import com.zx.order.utils.DateUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class ProcessPopupWindow extends PopupWindow implements View.OnClickListener {
    private Activity mActivity;
    private Holder holder;
    private View mView;
    private int width;

    private boolean goodsType = true;

    public ProcessPopupWindow(Activity mActivity) {
        super();
        this.mActivity = mActivity;
        this.initPopupWindow();
    }

    /**
     * 初始化
     */
    private void initPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mView = inflater.inflate(R.layout.pop_screen, null);
        this.setContentView(mView);
        width = DensityUtil.getScreenWidth();
        this.setWidth(width);
        this.setHeight((int) (DensityUtil.getScreenHeight() * 0.88));
        this.setTouchable(true);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.PopAnim);
        ColorDrawable background = new ColorDrawable(0x4f000000);
        this.setBackgroundDrawable(background);
        this.draw();

        holder = new Holder();
        x.view().inject(holder, mView);

        holder.btnLeft.setOnClickListener(this);
        holder.btnRight.setOnClickListener(this);
        holder.btnBeganTime.setOnClickListener(this);
        holder.btnEndTime.setOnClickListener(this);
        holder.txtCommodityInfo.setOnClickListener(this);
        holder.txtPlaceOfOrigin.setOnClickListener(this);
        holder.txtShipName.setOnClickListener(this);
        holder.txtTransferShipName.setOnClickListener(this);
        holder.txtPortOfDestination.setOnClickListener(this);
        holder.txtSeller.setOnClickListener(this);
        holder.txtForwardingCompany.setOnClickListener(this);
        holder.btnQuery.setOnClickListener(this);
        holder.txtReset.setOnClickListener(this);

    }

    /**
     * 绘制
     */
    private void draw() {
        this.mView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
    }

    /**
     * 重置
     */
    private void resetData() {
        holder.btnLeft.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.btn_blue));
        holder.btnLeft.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        holder.btnRight.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.btn_white));
        holder.btnRight.setTextColor(ContextCompat.getColor(mActivity, R.color.main_bg));
        goodsType = true;
        holder.btnBeganTime.setText("");
        holder.btnEndTime.setText("");
        holder.txtCommodityInfo.setText("全部");
        holder.txtPlaceOfOrigin.setText("全部");
        holder.txtShipName.setText("全部");
        holder.txtTransferShipName.setText("全部");
        holder.txtPortOfDestination.setText("全部");
        holder.txtSeller.setText("全部");
        holder.txtForwardingCompany.setText("全部");
        holder.btnR1.setChecked(false);
        holder.btnR2.setChecked(false);
        holder.btnR3.setChecked(false);
        holder.btnR4.setChecked(false);
        holder.btnR5.setChecked(false);
        holder.btnR6.setChecked(false);
        holder.btnR7.setChecked(false);
        holder.btnR8.setChecked(false);
        holder.btnR9.setChecked(false);
        holder.btnR10.setChecked(false);
        holder.btnR11.setChecked(false);
        holder.btnR12.setChecked(false);

    }

    @Override
    public void onClick(View v) {
        final List<String> strList = new ArrayList<>();
        strList.add("全部");
        strList.add("二分之一");
        strList.add("四分之一");
        strList.add("八分之一");
        switch (v.getId()) {
            case R.id.btnLeft:
                holder.btnLeft.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.btn_blue));
                holder.btnLeft.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                holder.btnRight.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.btn_white));
                holder.btnRight.setTextColor(ContextCompat.getColor(mActivity, R.color.main_bg));
                goodsType = true;
                break;
            case R.id.btnRight:
                holder.btnLeft.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.btn_white));
                holder.btnLeft.setTextColor(ContextCompat.getColor(mActivity, R.color.main_bg));
                holder.btnRight.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.btn_blue));
                holder.btnRight.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                goodsType = false;
                break;
            case R.id.btnBeganTime:
                DateUtils.onYearMonthDayPicker(mActivity, holder.btnBeganTime);
                break;
            case R.id.btnEndTime:
                DateUtils.onYearMonthDayPicker(mActivity, holder.btnEndTime);
                break;
            case R.id.txtCommodityInfo:
                DateUtils.optionPicker(mActivity, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        holder.txtCommodityInfo.setText(strList.get(point));
                    }
                });
                break;
            case R.id.txtPlaceOfOrigin:
                DateUtils.optionPicker(mActivity, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        holder.txtPlaceOfOrigin.setText(strList.get(point));
                    }
                });
                break;
            case R.id.txtShipName:
                DateUtils.optionPicker(mActivity, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        holder.txtShipName.setText(strList.get(point));
                    }
                });
                break;
            case R.id.txtTransferShipName:
                DateUtils.optionPicker(mActivity, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        holder.txtTransferShipName.setText(strList.get(point));
                    }
                });
                break;
            case R.id.txtPortOfDestination:
                DateUtils.optionPicker(mActivity, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        holder.txtPortOfDestination.setText(strList.get(point));
                    }
                });
                break;
            case R.id.txtSeller:
                DateUtils.optionPicker(mActivity, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        holder.txtSeller.setText(strList.get(point));
                    }
                });
                break;
            case R.id.txtForwardingCompany:
                DateUtils.optionPicker(mActivity, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        holder.txtForwardingCompany.setText(strList.get(point));
                    }
                });
                break;
            case R.id.btnQuery:
                this.dismiss();
                break;
            // 重置
            case R.id.txtReset:
                resetData();
                break;

        }
    }

    private class Holder{
        @ViewInject(R.id.btnQuery)
        private Button btnQuery;
        @ViewInject(R.id.btnLeft)
        private Button btnLeft;
        @ViewInject(R.id.btnRight)
        private Button btnRight;
        @ViewInject(R.id.btnBeganTime)
        private Button btnBeganTime;
        @ViewInject(R.id.btnEndTime)
        private Button btnEndTime;
        @ViewInject(R.id.txtCommodityInfo)
        private TextView txtCommodityInfo;
        @ViewInject(R.id.txtPlaceOfOrigin)
        private TextView txtPlaceOfOrigin;
        @ViewInject(R.id.txtShipName)
        private TextView txtShipName;
        @ViewInject(R.id.txtTransferShipName)
        private TextView txtTransferShipName;
        @ViewInject(R.id.txtPortOfDestination)
        private TextView txtPortOfDestination;
        @ViewInject(R.id.txtSeller)
        private TextView txtSeller;
        @ViewInject(R.id.txtForwardingCompany)
        private TextView txtForwardingCompany;
        @ViewInject(R.id.txtReset)
        private TextView txtReset;

        @ViewInject(R.id.btnR1)
        private RadioButton btnR1;
        @ViewInject(R.id.btnR2)
        private RadioButton btnR2;
        @ViewInject(R.id.btnR3)
        private RadioButton btnR3;
        @ViewInject(R.id.btnR4)
        private RadioButton btnR4;
        @ViewInject(R.id.btnR5)
        private RadioButton btnR5;
        @ViewInject(R.id.btnR6)
        private RadioButton btnR6;
        @ViewInject(R.id.btnR7)
        private RadioButton btnR7;
        @ViewInject(R.id.btnR8)
        private RadioButton btnR8;
        @ViewInject(R.id.btnR9)
        private RadioButton btnR9;
        @ViewInject(R.id.btnR10)
        private RadioButton btnR10;
        @ViewInject(R.id.btnR11)
        private RadioButton btnR11;
        @ViewInject(R.id.btnR12)
        private RadioButton btnR12;
    }

    /**
     * 显示在控件下右方
     *
     * @param parent
     */
    public void showAtDropDownRight(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] + parent.getWidth() - this.getWidth(), location[1] + parent.getHeight());
        }
    }
}
