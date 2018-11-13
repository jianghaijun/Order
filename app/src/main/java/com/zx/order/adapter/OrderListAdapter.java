package com.zx.order.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zx.order.R;
import com.zx.order.activity.OrderDetailsAct;
import com.zx.order.base.BaseModel;
import com.zx.order.bean.OrderBean;
import com.zx.order.listener.StrListener;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;

import java.io.IOException;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 订单列表
 * 作者：JHJ
 * 日期：2018/10/12 17:33
 * Q Q: 1320666709
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderHolder> {
    private Activity mContext;
    private List<OrderBean> mDataList;
    private Drawable leftSelect;
    private Drawable leftUnSelect;

    public OrderListAdapter(List<OrderBean> mDataList, Activity mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
        leftSelect = ContextCompat.getDrawable(mContext, R.drawable.collection_select);
        leftSelect.setBounds(0, 0, leftSelect.getMinimumWidth(), leftSelect.getMinimumHeight());
        leftUnSelect = ContextCompat.getDrawable(mContext, R.drawable.collection_un_select);
        leftUnSelect.setBounds(0, 0, leftUnSelect.getMinimumWidth(), leftUnSelect.getMinimumHeight());
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_order_list, null);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        OrderBean orderBean = mDataList.get(position);
        holder.txtStatus.setText(getStatusContent(orderBean));

        // 是否已收藏
        if (StrUtil.equals(orderBean.getCollectFlag(), "0")) {
            holder.txtCollection.setCompoundDrawables(leftUnSelect, null, null, null);
        } else {
            holder.txtCollection.setCompoundDrawables(leftSelect, null, null, null);
        }

        if (!StrUtil.equals(orderBean.getOrderType(), "0")) {
            holder.btnReservation.setVisibility(View.INVISIBLE);
        } else {
            holder.btnReservation.setVisibility(View.VISIBLE);
        }

        initView(orderBean, holder);

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new OnClick(orderBean));
        holder.txtCollection.setOnClickListener(new OnClick(orderBean));
        holder.btnReservation.setOnClickListener(new OnClick(orderBean));
        holder.btnMore.setOnClickListener(new OnClick(orderBean));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 动态添加显示内容
     *
     * @param orderBean
     * @param holder
     */
    private void initView(OrderBean orderBean, OrderHolder holder) {
        holder.llContent.removeAllViews();
        List<String> strList = orderBean.getOrderList();
        if (strList != null && strList.size() > 0) {
            LinearLayout.LayoutParams llLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(30));
            llLp.setMargins(DensityUtil.dip2px(5), 0, DensityUtil.dip2px(5), 0);
            LinearLayout.LayoutParams llcLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            llcLp.weight = 1;
            int size = strList.size() / 2;
            if (strList.size() % 2 != 0) {
                size++;
            }
            int color = ContextCompat.getColor(mContext, R.color.dark_b);
            for (int i = 0; i < size; i++) {
                int point = i * 2;
                LinearLayout content = new LinearLayout(mContext);
                content.setOrientation(LinearLayout.HORIZONTAL);
                if (point < strList.size()) {
                    String sLeft = strList.get(point);
                    TextView txtLeft = new TextView(mContext);
                    txtLeft.setText(sLeft);
                    txtLeft.setTextColor(color);
                    txtLeft.setTextSize(14);
                    txtLeft.setGravity(Gravity.LEFT | Gravity.CENTER);
                    txtLeft.setMaxLines(1);
                    txtLeft.setEllipsize(TextUtils.TruncateAt.END);
                    content.addView(txtLeft, llcLp);
                }

                if (point + 1 < strList.size()) {
                    String sRight = strList.get(point + 1);
                    TextView txtRight = new TextView(mContext);
                    txtRight.setText(sRight);
                    txtRight.setTextColor(color);
                    txtRight.setTextSize(14);
                    txtRight.setGravity(Gravity.LEFT | Gravity.CENTER);
                    txtRight.setMaxLines(1);
                    txtRight.setEllipsize(TextUtils.TruncateAt.END);
                    content.addView(txtRight, llcLp);
                }

                holder.llContent.addView(content, llLp);
            }
        }
    }

    /**
     * 获取状态文本
     *
     * @param orderBean 状态类型
     */
    private String getStatusContent(OrderBean orderBean) {
        String str = "";
        switch (orderBean.getOrderType()) {
            case "0":
                str = "报关预约 " + orderBean.getHandleState();
                break;
            case "1":
                str = "看货预约 " + orderBean.getHandleState();
                break;
            case "2":
                str = "对扒预约 " + orderBean.getHandleState();
                break;
            case "3":
                str = "入库预约 " + orderBean.getHandleState();
                break;
            case "4":
                str = "取样预约 " + orderBean.getHandleState();
                break;
            case "5":
                str = "出库预约 " + orderBean.getHandleState();
                break;
            case "6":
                str = "配送预约 " + orderBean.getHandleState();
                break;
            case "7":
                str = "诉提预约 " + orderBean.getHandleState();
                break;
            case "8":
                str = "熏蒸预约 " + orderBean.getHandleState();
                break;
            case "9":
                str = "疏港委托 " + orderBean.getHandleState();
                break;
            case "10":
                str = "查验委托 " + orderBean.getHandleState();
                break;
            case "11":
                str = "验箱预约 " + orderBean.getHandleState();
                break;
            case "12":
                str = "返箱预约 " + orderBean.getHandleState();
                break;
        }
        return str;
    }

    /**
     * 点击事件
     */
    private class OnClick implements View.OnClickListener {
        private OrderBean orderBean;

        OnClick(OrderBean orderBean) {
            this.orderBean = orderBean;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 收藏
                case R.id.txtCollection:
                    collection(orderBean);
                    break;
                // 报关预约
                case R.id.btnReservation:
                    DateUtils.yearMonthDayPicker(mContext, new StrListener() {
                        @Override
                        public void selectStr(String str) {
                            submitReservation(str, orderBean.getCustomsId(), orderBean);
                        }
                    });
                    break;
                // 评价---更多
                case R.id.llMain:
                case R.id.btnMore:
                    Intent intent = new Intent(mContext, OrderDetailsAct.class);
                    intent.putExtra("orderId", orderBean.getOrderId());
                    mContext.startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 报关预约
     *
     * @param declarationTime 预约时间
     * @param customsId       报关预约主键ID
     * @param orderBean       报关预约主键ID
     */
    private void submitReservation(final String declarationTime, String customsId, final OrderBean orderBean) {
        LoadingUtils.showLoading(mContext);
        JSONObject obj = new JSONObject();
        obj.put("declarationTime", DateUtil.parse(declarationTime, "yyyy-MM-dd").getTime());
        obj.put("customsId", customsId);
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.CUSTOMS_DECLARATION, obj.toString());
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
                                List<String> strList = orderBean.getOrderList();
                                if (strList != null) {
                                    for (int i = 0; i < strList.size(); i++) {
                                        if (StrUtil.containsAny(strList.get(i), "预约时间")) {
                                            strList.set(i, "预约时间:" + declarationTime);
                                            break;
                                        }
                                    }
                                }
                                OrderListAdapter.this.notifyDataSetChanged();
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
     * 收藏
     *
     * @param orderBean 数据
     */
    private void collection(final OrderBean orderBean) {
        LoadingUtils.showLoading(mContext);
        JSONObject obj = new JSONObject();
        obj.put("orderId", orderBean.getOrderId());
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.COLLECT, obj.toString());
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
                                if (StrUtil.equals(orderBean.getCollectFlag(), "0")) {
                                    orderBean.setCollectFlag("1");
                                } else {
                                    orderBean.setCollectFlag("0");
                                }
                                notifyDataSetChanged();
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
     * 构造器
     */
    public class OrderHolder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private TextView txtStatus;
        private TextView txtCollection;
        private LinearLayout llContent;
        private Button btnReservation;
        private Button btnMore;

        public OrderHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtCollection = (TextView) itemView.findViewById(R.id.txtCollection);
            llContent = (LinearLayout) itemView.findViewById(R.id.llContent);
            btnReservation = (Button) itemView.findViewById(R.id.btnReservation);
            btnMore = (Button) itemView.findViewById(R.id.btnMore);
        }
    }

}
