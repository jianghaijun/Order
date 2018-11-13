package com.zx.order.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.base.BaseModel;
import com.zx.order.bean.OrderBean;
import com.zx.order.bean.OrderDetailsBean;
import com.zx.order.custom.StarBar;
import com.zx.order.model.OrderDetailsModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

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

        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        LoadingUtils.showLoading(mContext);
        JSONObject obj = new JSONObject();
        obj.put("orderId", getIntent().getStringExtra("orderId"));
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.ORDER_DETAIL, obj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final OrderDetailsModel model = gson.fromJson(jsonData, OrderDetailsModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List<OrderBean> orderBeans = arrangementData(model.getData());
                                initData(orderBeans);
                                LoadingUtils.hideLoading();
                            }
                        });
                    } else {
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.json_error));
                }
            }
        });
    }

    /**
     * 数据整理
     *
     * @param orderDetailsBean 待整理
     * @return
     */
    private List<OrderBean> arrangementData(OrderDetailsBean orderDetailsBean) {
        List<OrderBean> orderBeans = new ArrayList<>();
        if (orderDetailsBean == null) {
            return orderBeans;
        }

        // 报关预约
        if (orderDetailsBean.getCustomsDeclaration() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getCustomsDeclaration();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getCustomsId());
            orderBean.setOtherIdName("customsId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("报关预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("处理时间 " + DateUtils.setDataToStr4(detailsBean.getHandleTime()));
            strList.add("完成时间 " + DateUtils.setDataToStr4(detailsBean.getFinishTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 看货预约
        if (orderDetailsBean.getLookGoods() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getLookGoods();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getLookGoodsId());
            orderBean.setOtherIdName("lookGoodsId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("看货预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("处理时间 " + DateUtils.setDataToStr4(detailsBean.getHandleTime()));
            strList.add("完成时间 " + DateUtils.setDataToStr4(detailsBean.getFinishTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 对扒预约
        if (orderDetailsBean.getSteak() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getSteak();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getSteakId());
            orderBean.setOtherIdName("steakId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("对扒预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("原车辆进场时间 " + DateUtils.setDataToStr4(detailsBean.getOriginalCarEntryTime()));
            strList.add("对扒车辆进场时间 " + DateUtils.setDataToStr4(detailsBean.getSteakCarEntryTime()));
            strList.add("对扒时间 " + DateUtils.setDataToStr4(detailsBean.getSteakTime()));
            strList.add("对扒完成时间 " + DateUtils.setDataToStr4(detailsBean.getSteakFinishTime()));
            strList.add("离台时间 " + DateUtils.setDataToStr4(detailsBean.getCarDepartureTime()));
            strList.add("发行时间 " + DateUtils.setDataToStr4(detailsBean.getReleaseTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 入库预约
        if (orderDetailsBean.getInStorage() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getInStorage();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getInStorageId());
            orderBean.setOtherIdName("inStorageId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("入库预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("处理时间 " + DateUtils.setDataToStr4(detailsBean.getHandleTime()));
            strList.add("完成时间 " + DateUtils.setDataToStr4(detailsBean.getFinishTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 取样预约
        if (orderDetailsBean.getSample() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getSample();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getSampleId());
            orderBean.setOtherIdName("sampleId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("取样委托 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("车辆进场时间 " + DateUtils.setDataToStr4(detailsBean.getCarEntryTime()));
            strList.add("车辆靠台时间 " + DateUtils.setDataToStr4(detailsBean.getCarPlatformTime()));
            strList.add("装车时间 " + DateUtils.setDataToStr4(detailsBean.getLoadingTime()));
            strList.add("装车完成时间 " + DateUtils.setDataToStr4(detailsBean.getLoadingFinishTime()));
            strList.add("离台时间 " + DateUtils.setDataToStr4(detailsBean.getCarDepartureTime()));
            strList.add("发行时间 " + DateUtils.setDataToStr4(detailsBean.getReleaseTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 出库预约
        if (orderDetailsBean.getOutStorage() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getOutStorage();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getOutStorageId());
            orderBean.setOtherIdName("outStorageId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("出库预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("车辆进场时间 " + DateUtils.setDataToStr4(detailsBean.getCarEntryTime()));
            strList.add("车辆靠台时间 " + DateUtils.setDataToStr4(detailsBean.getCarPlatformTime()));
            strList.add("装车时间 " + DateUtils.setDataToStr4(detailsBean.getLoadingTime()));
            strList.add("装车完成时间 " + DateUtils.setDataToStr4(detailsBean.getLoadingFinishTime()));
            strList.add("离台时间 " + DateUtils.setDataToStr4(detailsBean.getCarDepartureTime()));
            strList.add("发行时间 " + DateUtils.setDataToStr4(detailsBean.getReleaseTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 配送预约
        if (orderDetailsBean.getDelivery() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getDelivery();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getDeliveryId());
            orderBean.setOtherIdName("deliveryId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("配送预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("车辆进场时间 " + DateUtils.setDataToStr4(detailsBean.getCarEntryTime()));
            strList.add("车辆出场时间 " + DateUtils.setDataToStr4(detailsBean.getCarExitTime()));
            strList.add("到达时间 " + DateUtils.setDataToStr4(detailsBean.getArriveTime()));
            strList.add("签收时间 " + DateUtils.setDataToStr4(detailsBean.getSignInTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 拆提预约
        if (orderDetailsBean.getToAsk() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getToAsk();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getToAskId());
            orderBean.setOtherIdName("toAskId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("拆提预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("落箱完成时间 " + DateUtils.setDataToStr4(detailsBean.getDropBoxFinishTime()));
            strList.add("车辆进场时间 " + DateUtils.setDataToStr4(detailsBean.getCarEntryTime()));
            strList.add("拆箱时间 " + DateUtils.setDataToStr4(detailsBean.getUnBoxTime()));
            strList.add("返箱时间 " + DateUtils.setDataToStr4(detailsBean.getReturnBoxTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 熏蒸预约
        if (orderDetailsBean.getFumigate() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getFumigate();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getFumigateId());
            orderBean.setOtherIdName("fumigateId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("熏蒸预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("车辆进场时间 " + DateUtils.setDataToStr4(detailsBean.getCarEntryTime()));
            strList.add("车辆靠台时间 " + DateUtils.setDataToStr4(detailsBean.getCarPlatformTime()));
            strList.add("开始熏蒸时间 " + DateUtils.setDataToStr4(detailsBean.getBeginFumigateTime()));
            strList.add("结束熏蒸时间 " + DateUtils.setDataToStr4(detailsBean.getEndFumigateTime()));
            strList.add("装车时间 " + DateUtils.setDataToStr4(detailsBean.getLoadingTime()));
            strList.add("装车完成时间 " + DateUtils.setDataToStr4(detailsBean.getLoadingFinishTime()));
            strList.add("离台时间 " + DateUtils.setDataToStr4(detailsBean.getCarDepartureTime()));
            strList.add("发运时间 " + DateUtils.setDataToStr4(detailsBean.getShippingTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 疏港委托
        if (orderDetailsBean.getEntrustHarbour() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getEntrustHarbour();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getEntrustHarbourId());
            orderBean.setOtherIdName("entrustHarbourId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("疏港委托 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("处理时间 " + DateUtils.setDataToStr4(detailsBean.getHandleTime()));
            strList.add("完成时间 " + DateUtils.setDataToStr4(detailsBean.getFinishTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 查验委托
        if (orderDetailsBean.getCheck() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getCheck();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getCheckId());
            orderBean.setOtherIdName("checkId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("查验委托 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("处理时间 " + DateUtils.setDataToStr4(detailsBean.getHandleTime()));
            strList.add("完成时间 " + DateUtils.setDataToStr4(detailsBean.getFinishTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 验箱预约
        if (orderDetailsBean.getTryoff() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getTryoff();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getTryoffId());
            orderBean.setOtherIdName("tryoffId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("验箱预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("处理时间 " + DateUtils.setDataToStr4(detailsBean.getHandleTime()));
            strList.add("完成时间 " + DateUtils.setDataToStr4(detailsBean.getFinishTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        // 返箱预约
        if (orderDetailsBean.getBackBox() != null) {
            OrderDetailsBean detailsBean = orderDetailsBean.getBackBox();
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderType(detailsBean.getOrderType());
            orderBean.setOtherId(detailsBean.getBackBoxId());
            orderBean.setOtherIdName("backBoxId");
            List<String> strList = new ArrayList<>();
            orderBean.setHandleState("返箱预约 " + detailsBean.getHandleState());
            strList.add("受理时间 " + DateUtils.setDataToStr4(detailsBean.getAcceptanceTime()));
            strList.add("处理时间 " + DateUtils.setDataToStr4(detailsBean.getHandleTime()));
            strList.add("完成时间 " + DateUtils.setDataToStr4(detailsBean.getFinishTime()));
            orderBean.setOrderList(strList);
            orderBean.setEvaluateLevel(detailsBean.getEvaluateLevel());
            orderBean.setEvaluateContent(detailsBean.getEvaluateContent());
            orderBeans.add(orderBean);
        }

        return orderBeans;
    }

    /**
     * 初始化数据
     *
     * @param orderBeans 数据
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initData(List<OrderBean> orderBeans) {
        if (orderBeans == null || orderBeans.size() == 0) {
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

        for (final OrderBean orderBean : orderBeans) {
            LinearLayout llMain = new LinearLayout(this);
            llMain.setBackground(bg);
            llMain.setFocusable(true);
            llMain.setFocusableInTouchMode(true);
            llMain.setPadding(0, 0, 0, tenDp);
            llMain.setOrientation(LinearLayout.VERTICAL);
            // 标题
            final TextView txtTitle = new TextView(this);
            txtTitle.setText(orderBean.getHandleState());
            txtTitle.setTextSize(14);
            txtTitle.setGravity(Gravity.LEFT | Gravity.CENTER);
            txtTitle.setTextColor(txtColor);
            txtTitle.setCompoundDrawables(null, null, rightDrawableShrink, null);
            txtTitle.setPadding(tenDp, 0, tenDp, 0);
            llMain.addView(txtTitle, llContentLp);
            // 展开收缩内容
            final LinearLayout llFold = new LinearLayout(this);
            llFold.setOrientation(LinearLayout.VERTICAL);
            if (orderBean.getOrderList() != null && orderBean.getOrderList().size() != 0) {
                List<String> strList = orderBean.getOrderList();
                for (int i = 0; i < strList.size(); i++) {
                    TextView txtContentTitle = new TextView(this);
                    txtContentTitle.setText(strList.get(i));
                    txtContentTitle.setTextSize(14);
                    txtContentTitle.setGravity(Gravity.CENTER);
                    txtContentTitle.setTextColor(txtColor);
                    txtContentTitle.setPadding(tenDp, 0, tenDp, 0);
                    llFold.addView(txtContentTitle, llFoldContentLp);

                    if (i < strList.size() - 1) {
                        TextView txtDownArrow = new TextView(this);
                        txtDownArrow.setText("↓");
                        txtDownArrow.setTextSize(20);
                        txtDownArrow.setGravity(Gravity.CENTER);
                        txtDownArrow.setTextColor(txtColor);
                        txtDownArrow.setPadding(tenDp, 0, tenDp, 0);
                        llFold.addView(txtDownArrow, llFoldContentLp);
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

            final StarBar starBar = new StarBar(this, null);
            starBar.setStarMark(orderBean.getEvaluateLevel());
            llFold.addView(starBar, ratingBarLp);

            final EditText edtEvaluate = new EditText(this);
            edtEvaluate.setHint("请填写您的评价！");
            edtEvaluate.setText(orderBean.getEvaluateContent());
            edtEvaluate.setTextSize(14);
            edtEvaluate.setGravity(Gravity.LEFT | Gravity.TOP);
            edtEvaluate.setFocusable(false);
            edtEvaluate.setFocusableInTouchMode(true);
            edtEvaluate.setTextColor(txtColor);
            edtEvaluate.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
            edtEvaluate.setPadding(tenDp / 2, tenDp / 2, tenDp / 2, tenDp / 2);
            llFold.addView(edtEvaluate, llEdtContentLp);

            Button btnSubmit = new Button(this);
            btnSubmit.setText("确认评价");
            btnSubmit.setTextSize(14);
            btnSubmit.setGravity(Gravity.CENTER);
            btnSubmit.setBackground(ContextCompat.getDrawable(mContext, R.drawable.btn_blue));
            btnSubmit.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            btnSubmit.setPadding(tenDp, tenDp / 2, tenDp, tenDp / 2);
            llFold.addView(btnSubmit, btnLp);

            llMain.addView(llFold, llFoldLp);

            llFold.setVisibility(View.GONE);

            txtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orderBean.isOpen()) {
                        llFold.setVisibility(View.GONE);
                        txtTitle.setCompoundDrawables(null, null, rightDrawableShrink, null);
                    } else {
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
                        evaluateOrder((int) starBar.getStarMark(), edtEvaluate.getText().toString(), orderBean.getOrderType(), orderBean.getOtherId(), orderBean.getOtherIdName());
                    }
                }
            });

            llContent.addView(llMain, llMainLp);
        }
    }

    /**
     * 提交评论
     *
     * @param starMark    星星数量
     * @param evaluate    评价内容
     * @param orderType   类型
     * @param otherId     主键id
     * @param otherIdName 主键Key
     */
    private void evaluateOrder(int starMark, String evaluate, String orderType, String otherId, String otherIdName) {
        LoadingUtils.showLoading(mContext);
        JSONObject obj = new JSONObject();
        obj.put(otherIdName, otherId);
        obj.put("orderType", orderType);
        obj.put("evaluateContent", evaluate);
        obj.put("evaluateLevel", starMark);
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.DEAL_ORDER_DETAIL, obj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final BaseModel model = gson.fromJson(jsonData, BaseModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LoadingUtils.hideLoading();
                                ToastUtil.showShort(mContext, model.getMessage());
                            }
                        });
                    } else {
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.json_error));
                }
            }
        });
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
