package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.activity.BoxStatusAct;
import com.zx.order.bean.FlightVoyageBean;
import com.zx.order.dialog.ShowDialog;
import com.zx.order.utils.FalseDataUtil;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 航班航次适配器
 * 作者：JHJ
 * 日期：2018/10/9 18:51
 * Q Q: 1320666709
 */
public class FlightVoyageAdapter extends RecyclerView.Adapter<FlightVoyageAdapter.OrderHolder> {
    private List<FlightVoyageBean> mDataList;
    private Context mContext;

    public FlightVoyageAdapter(List<FlightVoyageBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_flight_voyage, null);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        FlightVoyageBean bean = mDataList.get(position);

        holder.txtBillOfLadingNumContext.setText(bean.getBillOfLadingNumContext());
        holder.txtBillStatus.setText("单据状态：" + bean.getBillStatus());
        holder.txtDeclareStatus.setText("申报状态：" + bean.getDeclareStatus());
        holder.txtInspectionStatus.setText("查验状态：" + bean.getInspectionStatus());
        holder.txtBoxNum.setText("箱量：" + bean.getBoxNum());

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new TxtOnClickListener(bean));
        holder.btnClearanceStatus.setOnClickListener(new TxtOnClickListener(bean));
        holder.btnInspection.setOnClickListener(new TxtOnClickListener(bean));
        holder.btnBoxStatus.setOnClickListener(new TxtOnClickListener(bean));
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
                    strList = FalseDataUtil.getClearanceStatusData();
                    new ShowDialog(mContext, "通关状态", strList).show();
                    break;
                // 查验
                case R.id.btnInspection:
                    strList = FalseDataUtil.getInspectionData();
                    new ShowDialog(mContext, "查验", strList).show();
                    break;
                // 箱状态
                case R.id.btnBoxStatus:
                    Intent intent = new Intent(mContext, BoxStatusAct.class);
                    mContext.startActivity(intent);
                    break;
            }
        }
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
