package com.zx.order.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.bean.OrderBean;
import com.zx.order.custom.StarBar;
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
 * 订单详情
 * 作者：JHJ
 * 日期：2018/10/16 10:53
 * Q Q: 1320666709
 */
public class OrderDetailsAct extends BaseActivity {
    @ViewInject(R.id.actionBar)
    private View actionBar;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;

    @ViewInject(R.id.llContent)
    private LinearLayout llContent;

    private Activity mContext;
    private List<OrderBean> dataList;
    private List<Integer> intList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_details);
        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        actionBar.setVisibility(View.VISIBLE);
        imgBtnLeft.setVisibility(View.VISIBLE);
        txtTitle.setText("订单详情");
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        dataList = FalseDataUtil.getOrderDetailsData();
        initData();
    }

    /**
     * 初始化数据
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        if (dataList == null || dataList.size() == 0) {
            return;
        }

        llContent.removeAllViews();

        int txtColor = ContextCompat.getColor(this, R.color.dark_b); // 文字颜色
        Drawable bg = ContextCompat.getDrawable(this, R.drawable.gray_stroke_white_solid_bg); // 展开图标
        final Drawable rightDrawableOpen = ContextCompat.getDrawable(this, R.drawable.open); // 展开图标
        final Drawable rightDrawableShrink = ContextCompat.getDrawable(this, R.drawable.shrink); // 收缩图标
        rightDrawableOpen.setBounds(0, 0, rightDrawableOpen.getMinimumWidth(), rightDrawableOpen.getMinimumHeight());
        rightDrawableShrink.setBounds(0, 0, rightDrawableShrink.getMinimumWidth(), rightDrawableShrink.getMinimumHeight());
        int tenDp = DensityUtil.dip2px(10);

        // 外层样式
        LinearLayout.LayoutParams llMainLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llMainLp.setMargins(tenDp, tenDp, tenDp, 0);
        // 文本样式
        LinearLayout.LayoutParams llContentLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(40));
        // 展开收缩样式
        LinearLayout.LayoutParams llFoldLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams llFoldContentLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(25));
        LinearLayout.LayoutParams llEdtContentLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(60));
        llEdtContentLp.setMargins(tenDp * 6, tenDp, tenDp, tenDp);
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(40));
        btnLp.setMargins(tenDp * 6, 0, tenDp, 0);
        LinearLayout.LayoutParams ratingBarLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(32));
        ratingBarLp.gravity = Gravity.CENTER;
        for (final OrderBean orderBean : dataList) {
            LinearLayout llMain = new LinearLayout(this);
            llMain.setBackground(bg);
            llMain.setFocusable(true);
            llMain.setFocusableInTouchMode(true);
            llMain.setPadding(0, 0, 0, tenDp);
            llMain.setOrientation(LinearLayout.VERTICAL);
            // 标题
            final TextView txtTitle = new TextView(this);
            txtTitle.setText(orderBean.getReservationType() + " " + orderBean.getStepStatus());
            txtTitle.setTextSize(14);
            txtTitle.setGravity(Gravity.LEFT | Gravity.CENTER);
            txtTitle.setTextColor(txtColor);
            txtTitle.setCompoundDrawables(null, null, rightDrawableShrink, null);
            txtTitle.setPadding(tenDp, 0, tenDp, 0);
            llMain.addView(txtTitle, llContentLp);
            // 展开收缩内容
            final LinearLayout llFold = new LinearLayout(this);
            llFold.setOrientation(LinearLayout.VERTICAL);
            int llFoldHeight = 0;
            if (orderBean.getReservationStep() != null && orderBean.getReservationStep().size() != 0) {
                List<String> strList = orderBean.getReservationStep();
                for (int i = 0; i < strList.size(); i++) {
                    TextView txtContentTitle = new TextView(this);
                    txtContentTitle.setText(strList.get(i));
                    txtContentTitle.setTextSize(14);
                    txtContentTitle.setGravity(Gravity.CENTER);
                    txtContentTitle.setTextColor(txtColor);
                    txtContentTitle.setPadding(tenDp, 0, tenDp, 0);
                    llFold.addView(txtContentTitle, llFoldContentLp);
                    llFoldHeight+=DensityUtil.dip2px(25);

                    if (i < strList.size() - 1) {
                        TextView txtDownArrow = new TextView(this);
                        txtDownArrow.setText("↓");
                        txtDownArrow.setTextSize(20);
                        txtDownArrow.setGravity(Gravity.CENTER);
                        txtDownArrow.setTextColor(txtColor);
                        txtDownArrow.setPadding(tenDp, 0, tenDp, 0);
                        llFold.addView(txtDownArrow, llFoldContentLp);
                        llFoldHeight+=DensityUtil.dip2px(25);
                    }
                }
            }

            TextView evaluate = new TextView(this);
            evaluate.setText("评价：");
            evaluate.setTextSize(14);
            evaluate.setGravity(Gravity.LEFT | Gravity.CENTER);
            evaluate.setTextColor(txtColor);
            evaluate.setPadding(tenDp, 0, tenDp, 0);
            llFold.addView(evaluate, llFoldContentLp);
            llFoldHeight+=DensityUtil.dip2px(25);

            final StarBar starBar = new StarBar(this, null);
            starBar.setStarMark(orderBean.getEvaluateLevel());
            llFold.addView(starBar, ratingBarLp);
            llFoldHeight+=DensityUtil.dip2px(32);

            final EditText edtEvaluate = new EditText(this);
            edtEvaluate.setHint("请填写您的评价！");
            edtEvaluate.setText(orderBean.getEvaluateContent());
            edtEvaluate.setTextSize(14);
            edtEvaluate.setGravity(Gravity.LEFT | Gravity.TOP);
            edtEvaluate.setTextColor(txtColor);
            edtEvaluate.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
            edtEvaluate.setPadding(tenDp / 2, tenDp / 2, tenDp / 2, tenDp / 2);
            llFold.addView(edtEvaluate, llEdtContentLp);
            llFoldHeight+=DensityUtil.dip2px(80);

            Button btnSubmit = new Button(this);
            btnSubmit.setText("确认评价");
            btnSubmit.setTextSize(14);
            btnSubmit.setGravity(Gravity.CENTER);
            btnSubmit.setBackground(ContextCompat.getDrawable(mContext, R.drawable.btn_blue));
            btnSubmit.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            btnSubmit.setPadding(tenDp, tenDp / 2, tenDp, tenDp / 2);
            llFold.addView(btnSubmit, btnLp);
            llFoldHeight+=DensityUtil.dip2px(40);

            llMain.addView(llFold, llFoldLp);
            orderBean.setHeight(llFoldHeight);

            llFold.setVisibility(View.GONE);

            txtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if (orderBean.isAnimating()) return;
                    //orderBean.setAnimating(true);
                    if (orderBean.isOpen()) {
                        //animateClose(llFold, orderBean);
                        llFold.setVisibility(View.GONE);
                        txtTitle.setCompoundDrawables(null, null, rightDrawableShrink, null);
                    } else {
                        //animateOpen(llFold, orderBean.getHeight(), orderBean);
                        llFold.setVisibility(View.VISIBLE);
                        txtTitle.setCompoundDrawables(null, null, rightDrawableOpen, null);
                    }
                    orderBean.setOpen(!orderBean.isOpen());
                }
            });

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (starBar.getStarMark() == 0) {
                        ToastUtil.showShort(mContext, "请选择评星等级！");
                    } else if (StrUtil.isEmpty(edtEvaluate.getText().toString())) {
                        ToastUtil.showShort(mContext, "请填写评价内容！");
                    } else {
                        //ToastUtil.showShort(mContext, starBar.getStarMark() + edtEvaluate.getText().toString());
                        ToastUtil.showShort(mContext, "评价成功！");
                        finish();
                    }
                }
            });

            llContent.addView(llMain, llMainLp);
        }
    }

    private void animateOpen(LinearLayout view, int height, final OrderBean orderBean) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, height);
        /*animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                orderBean.setAnimating(false);
            }
        });*/
        animator.start();
    }

    private void animateClose(final LinearLayout view, final OrderBean orderBean) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        /*animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                orderBean.setAnimating(false);
            }
        });*/
        view.setVisibility(View.GONE);
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
