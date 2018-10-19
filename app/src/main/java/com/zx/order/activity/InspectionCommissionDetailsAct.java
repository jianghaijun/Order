package com.zx.order.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.bean.InspectionCommissionBean;
import com.zx.order.listener.IntListener;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.FalseDataUtil;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * 查验委托---下一步
 * 作者：JHJ
 * 日期：2018/10/18 10:34
 * Q Q: 1320666709
 */
public class InspectionCommissionDetailsAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.llMain)
    private LinearLayout llMain;

    private Activity mContext;
    private List<Object> viewList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inspection_commission_details);

        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        imgBtnLeft.setVisibility(View.VISIBLE);
        txtTitle.setText("查验详情");
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));


        InspectionCommissionBean bean = FalseDataUtil.getInspectionCommissionSubmitData();
        initView(bean);
    }

    /**
     * 动态添加
     * @param bean
     */
    private void initView(InspectionCommissionBean bean) {
        llMain.removeAllViews();

        List<InspectionCommissionBean> beanList = bean.getNextDataList();
        if (beanList != null && beanList.size() > 0) {
            int size = beanList.size();
            int tenDp = DensityUtil.dip2px(10);
            // kay样式
            LinearLayout.LayoutParams keyLp = new LinearLayout.LayoutParams(DensityUtil.dip2px(100), LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams valLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            valLp.weight = 1;
            valLp.setMargins(tenDp, 0, DensityUtil.dip2px(5), 0);
            LinearLayout.LayoutParams llContentLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(40));
            llContentLp.setMargins(0, 0, 0, DensityUtil.dip2px(5));

            for (int i = 0; i < size; i++) {
                LinearLayout llContent = new LinearLayout(mContext);
                llContent.setOrientation(LinearLayout.HORIZONTAL);

                InspectionCommissionBean contentBean = beanList.get(i);
                llContent.addView(addNewTitleTxt(contentBean), keyLp);
                if (StrUtil.equals(contentBean.getControlType(), "1")) {
                    EditText editText = addNewEdt(contentBean);
                    llContent.addView(editText, valLp);
                    viewList.add(editText);
                } else if (StrUtil.equals(contentBean.getControlType(), "2")) {
                    TextView textView = addNewSelect(contentBean);
                    llContent.addView(textView, valLp);
                    viewList.add(textView);
                } else if (StrUtil.equals(contentBean.getControlType(), "3")) {
                    TextView textView = addNewDate(contentBean);
                    llContent.addView(textView, valLp);
                    viewList.add(textView);
                }

                llMain.addView(llContent, llContentLp);
            }
        }
    }

    /**
     * 添加一个TextView
     *
     * @param bean 文本样式
     * @return
     */
    private TextView addNewTitleTxt(InspectionCommissionBean bean) {
        TextView txt = new TextView(mContext);
        txt.setText(bean.getTxtName());
        txt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        txt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        txt.setGravity(Gravity.RIGHT | Gravity.CENTER);
        return txt;
    }

    /**
     * 添加一个EditText
     *
     * @param bean 文本样式
     * @return
     */
    private EditText addNewEdt(InspectionCommissionBean bean) {
        EditText edt = new EditText(mContext);
        edt.setText(bean.getTxtContent());
        edt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        edt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        edt.setGravity(Gravity.LEFT | Gravity.CENTER);
        edt.setHint(bean.getHint());
        edt.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
        edt.setPadding(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5));
        return edt;
    }

    /**
     * 添加一个下拉框
     *
     * @param bean 文本样式
     * @return
     */
    private TextView addNewSelect(final InspectionCommissionBean bean) {
        final TextView txt = new TextView(mContext);
        txt.setText(bean.getTxtContent());
        txt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        txt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        txt.setGravity(Gravity.LEFT | Gravity.CENTER);
        txt.setHint(bean.getHint());
        txt.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
        txt.setPadding(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5));
        Drawable rightDrawable = ContextCompat.getDrawable(mContext, R.drawable.drop_down);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        txt.setCompoundDrawables(null, null, rightDrawable, null);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<InspectionCommissionBean> beans = bean.getOptions();
                if (beans == null || beans.size() == 0) {
                    ToastUtil.showShort(mContext, "暂无数据！");
                } else {
                    final List<String> strList = new ArrayList<>();
                    for (InspectionCommissionBean commissionBean : beans) {
                        strList.add(commissionBean.getOption());
                    }
                    DateUtils.optionPicker((Activity) mContext, strList, new IntListener() {
                        @Override
                        public void selectPoint(int point) {
                            txt.setText(strList.get(point));
                        }
                    });
                }
            }
        });

        return txt;
    }

    /**
     * 添加一个日期选择控件
     *
     * @param bean 文本样式
     * @return
     */
    private TextView addNewDate(final InspectionCommissionBean bean) {
        final TextView txt = new TextView(mContext);
        txt.setText(bean.getTxtContent());
        txt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        txt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        txt.setGravity(Gravity.LEFT | Gravity.CENTER);
        txt.setHint(bean.getHint());
        txt.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
        txt.setPadding(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5));
        Drawable rightDrawable = ContextCompat.getDrawable(mContext, R.drawable.date);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        txt.setCompoundDrawables(null, null, rightDrawable, null);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtils.onYearMonthDayPicker((Activity) mContext, txt);
            }
        });

        return txt;
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.btnLastStep, R.id.btnSubmit})
    private void onClick(View v) {
        switch (v.getId()) {
            // 返回---上一步
            case R.id.imgBtnLeft:
            case R.id.btnLastStep:
                this.finish();
                break;
            // 提交
            case R.id.btnSubmit:
                ToastUtil.showShort(mContext, "提交成功！");
                ScreenManagerUtil.popAllActivityExceptOne(MainPageAct.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}