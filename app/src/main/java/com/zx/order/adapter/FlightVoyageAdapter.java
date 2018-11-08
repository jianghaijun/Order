package com.zx.order.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zx.order.R;
import com.zx.order.activity.BoxStatusAct;
import com.zx.order.activity.LoginAct;
import com.zx.order.activity.MainPageAct;
import com.zx.order.bean.ClearanceInspectionBean;
import com.zx.order.bean.FlightVoyageBean;
import com.zx.order.dialog.ShowDialog;
import com.zx.order.model.ClearanceInspectionModel;
import com.zx.order.model.LoginModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.FalseDataUtil;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.SpUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;

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
 * 航班航次适配器
 * 作者：JHJ
 * 日期：2018/10/9 18:51
 * Q Q: 1320666709
 */
public class FlightVoyageAdapter extends RecyclerView.Adapter<FlightVoyageAdapter.OrderHolder> {
    private List<FlightVoyageBean> mDataList;
    private Activity mContext;

    public FlightVoyageAdapter(List<FlightVoyageBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = (Activity) parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_flight_voyage, null);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        FlightVoyageBean flightVoyageBean = mDataList.get(position);

        holder.txtBillOfLadingNumContext.setText(flightVoyageBean.getCargoBillNo());
        switch (flightVoyageBean.getBillState()) {
            case "0":
                holder.txtBillStatus.setText("单据状态：齐全");
                break;
            case "1":
                holder.txtBillStatus.setText("单据状态：缺失");
                break;
        }
        switch (flightVoyageBean.getDeclarationState()) {
            case "0":
                holder.txtDeclareStatus.setText("申报状态：放行");
                break;
            case "1":
                holder.txtDeclareStatus.setText("申报状态：扣留");
                break;
        }
        switch (flightVoyageBean.getInspectionState()) {
            case "0":
                holder.txtInspectionStatus.setText("查验状态：待查");
                break;
            case "1":
                holder.txtInspectionStatus.setText("查验状态：已查");
                break;
        }
        holder.txtBoxNum.setText("箱量：" + flightVoyageBean.getPieces());

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new TxtOnClickListener(flightVoyageBean));
        holder.btnClearanceStatus.setOnClickListener(new TxtOnClickListener(flightVoyageBean));
        holder.btnInspection.setOnClickListener(new TxtOnClickListener(flightVoyageBean));
        holder.btnBoxStatus.setOnClickListener(new TxtOnClickListener(flightVoyageBean));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 点击事件
     */
    private class TxtOnClickListener implements View.OnClickListener {
        FlightVoyageBean flightVoyageBean;

        TxtOnClickListener(FlightVoyageBean flightVoyageBean) {
            this.flightVoyageBean = flightVoyageBean;
        }

        @Override
        public void onClick(View v) {
            List<String> strList;
            switch (v.getId()) {
                // 通关状态
                case R.id.btnClearanceStatus:
                    getData(ConstantsUtil.CLEARANCE_STATUS, flightVoyageBean.getCargoBillId());
                    break;
                // 查验
                case R.id.btnInspection:
                    getData(ConstantsUtil.INSPECTION, flightVoyageBean.getCargoBillId());
                    break;
                // 箱状态
                case R.id.btnBoxStatus:
                    Intent intent = new Intent(mContext, BoxStatusAct.class);
                    intent.putExtra("cargoBillId", flightVoyageBean.getCargoBillId());
                    mContext.startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 获取通关状态---查验数据
     * @param url
     * @param cargoBillId
     */
    private void getData(final String url, String cargoBillId) {
        LoadingUtils.showLoading(mContext);
        JSONObject boj = new JSONObject();
        boj.put("cargoBillId", cargoBillId);
        Request request = ChildThreadUtil.getRequest(mContext, url, boj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final ClearanceInspectionModel model = gson.fromJson(jsonData, ClearanceInspectionModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 赋值
                                List<String> strList = new ArrayList<>();
                                ClearanceInspectionBean inspectionBean = model.getData();
                                LoadingUtils.hideLoading();
                                if (inspectionBean != null) {
                                    if (StrUtil.equals(url, ConstantsUtil.CLEARANCE_STATUS)) {
                                        strList.add("疏港委托时间：" + DateUtils.setDataToStr3(inspectionBean.getEntrustingTheHarbourTime()));
                                        strList.add("分流触发时间：" + DateUtils.setDataToStr3(inspectionBean.getShuntTriggerTime()));
                                        strList.add("审核通过时间：" + DateUtils.setDataToStr3(inspectionBean.getReviewPassThroughTime()));
                                        strList.add("换单时间：" + DateUtils.setDataToStr3(inspectionBean.getChangeListTime()));
                                        strList.add("申报时间：" + DateUtils.setDataToStr3(inspectionBean.getDeclareTime()));
                                        strList.add("海关放行时间：" + DateUtils.setDataToStr3(inspectionBean.getCustomsReleaeTime()));
                                        strList.add("检疫放行时间：" + DateUtils.setDataToStr3(inspectionBean.getQuarantineReleaseTime()));
                                        new ShowDialog(mContext, "通关状态", strList).show();
                                    } else {
                                        strList.add("状态：" + (StrUtil.equals(inspectionBean.getCheckState(), "0") ? "查验" : "未查验"));
                                        strList.add("是否检疫取样：" + (StrUtil.equals(inspectionBean.getIsSampling(), "0") ? "是" : "否"));
                                        strList.add("查验预约时间：" + DateUtils.setDataToStr3(inspectionBean.getCheckAppointmentTime()));
                                        strList.add("靠台时间：" + DateUtils.setDataToStr3(inspectionBean.getPlatformTime()));
                                        strList.add("开箱时间：" + DateUtils.setDataToStr3(inspectionBean.getOpeningTime()));
                                        strList.add("查验结束时间：" + DateUtils.setDataToStr3(inspectionBean.getEndOfInspectionTime()));
                                        strList.add("回装时间：" + DateUtils.setDataToStr3(inspectionBean.getBackLoadingTime()));
                                        strList.add("离台时间：" + DateUtils.setDataToStr3(inspectionBean.getDepartureTime()));
                                        new ShowDialog(mContext, "查验", strList).show();
                                    }
                                } else {
                                    ToastUtil.showShort(mContext, "暂无流程数据！");
                                }
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
        private TextView txtBillOfLadingNumContext;
        private TextView txtBillStatus;
        private TextView txtDeclareStatus;
        private TextView txtInspectionStatus;
        private TextView txtBoxNum;
        private Button btnClearanceStatus;
        private Button btnInspection;
        private Button btnBoxStatus;

        public OrderHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtBillOfLadingNumContext = (TextView) itemView.findViewById(R.id.txtBillOfLadingNumContext);
            txtBillStatus = (TextView) itemView.findViewById(R.id.txtBillStatus);
            txtDeclareStatus = (TextView) itemView.findViewById(R.id.txtDeclareStatus);
            txtInspectionStatus = (TextView) itemView.findViewById(R.id.txtInspectionStatus);
            txtBoxNum = (TextView) itemView.findViewById(R.id.txtBoxNum);
            btnClearanceStatus = (Button) itemView.findViewById(R.id.btnClearanceStatus);
            btnInspection = (Button) itemView.findViewById(R.id.btnInspection);
            btnBoxStatus = (Button) itemView.findViewById(R.id.btnBoxStatus);
        }
    }

}
