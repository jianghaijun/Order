package com.zx.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.bean.BoxStatusBean;
import com.zx.order.utils.DateUtils;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 箱状态
 * 作者：JHJ
 * 日期：2018/10/10 11:33
 * Q Q: 1320666709
 */
public class BoxStatusAdapter extends RecyclerView.Adapter<BoxStatusAdapter.BoxStatusHolder> {
    private List<BoxStatusBean> mDataList;
    private Context mContext;

    public BoxStatusAdapter(List<BoxStatusBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public BoxStatusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_box_status, null);
        return new BoxStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(BoxStatusHolder holder, int position) {
        BoxStatusBean bean = mDataList.get(position);

        holder.txtContainerNo.setText(bean.getContainerNo());
        holder.txtSuitcaseCarNo.setText("提箱车号：" + bean.getSuitcaseCarNo());
        holder.txtReturnBoxDate.setText("返箱日期：" + DateUtils.setDataToStr(bean.getReturnBoxDate()));
        holder.txtBoxEntryTime.setText("箱进场时间：" + DateUtils.setDataToStr2(bean.getBoxEntryTime()));
        holder.txtArrivalTime.setText("运抵发送时间时间：" + DateUtils.setDataToStr2(bean.getArrivalTime()));
        holder.txtApproachTimeOfSuitcaseVehicle.setText("提箱车辆进场时间：" + DateUtils.setDataToStr2(bean.getApproachTimeOfSuitcaseVehicle()));
        holder.txtBoxDrivingTime.setText("箱出场时间：" + DateUtils.setDataToStr2(bean.getBoxDrivingTime()));

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 构造器
     */
    public class BoxStatusHolder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private TextView txtContainerNo;
        private TextView txtSuitcaseCarNo;
        private TextView txtReturnBoxDate;
        private TextView txtBoxEntryTime;
        private TextView txtArrivalTime;
        private TextView txtApproachTimeOfSuitcaseVehicle;
        private TextView txtBoxDrivingTime;

        public BoxStatusHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtContainerNo = (TextView) itemView.findViewById(R.id.txtContainerNo);
            txtSuitcaseCarNo = (TextView) itemView.findViewById(R.id.txtSuitcaseCarNo);
            txtReturnBoxDate = (TextView) itemView.findViewById(R.id.txtReturnBoxDate);
            txtBoxEntryTime = (TextView) itemView.findViewById(R.id.txtBoxEntryTime);
            txtArrivalTime = (TextView) itemView.findViewById(R.id.txtArrivalTime);
            txtApproachTimeOfSuitcaseVehicle = (TextView) itemView.findViewById(R.id.txtApproachTimeOfSuitcaseVehicle);
            txtBoxDrivingTime = (TextView) itemView.findViewById(R.id.txtBoxDrivingTime);
        }
    }

}
