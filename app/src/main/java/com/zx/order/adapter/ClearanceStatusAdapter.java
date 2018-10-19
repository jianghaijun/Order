package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.zx.order.R;
import com.zx.order.activity.ContainerDetailsAct;
import com.zx.order.activity.FlightVoyageAct;
import com.zx.order.bean.ClearanceBean;
import com.zx.order.utils.DateUtils;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 通关状态适配器
 * 作者：JHJ
 * 日期：2018/10/9 16:47
 * Q Q: 1320666709
 */
public class ClearanceStatusAdapter extends RecyclerView.Adapter<ClearanceStatusAdapter.OrderHolder> {
    private List<ClearanceBean> mDataList;
    private Context mContext;

    public ClearanceStatusAdapter(List<ClearanceBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_clearance_status, null);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        ClearanceBean bean = mDataList.get(position);

        holder.txtShipName.setText("航名航次：" + bean.getShipNameContext());
        holder.txtPortOfDestination.setText("目的港：" + bean.getPortOfDestination());
        holder.txtEstimatedBerthingDate.setText("预计靠泊日期：" + DateUtils.setDataToStr(bean.getEstimatedBerthingDate()));
        holder.txtActualBerthingDate.setText("实际靠泊日期：" + DateUtils.setDataToStr(bean.getActualBerthingDate()));
        holder.txtBoxNum.setText("箱量：" + bean.getBoxNum());

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new TxtOnClickListener(bean));
        holder.txtSearch.setOnClickListener(new TxtOnClickListener(bean));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    /**
     * 点击事件
     */
    private class TxtOnClickListener implements View.OnClickListener {
        ClearanceBean clearanceBean;

        TxtOnClickListener(ClearanceBean clearanceBean) {
            this.clearanceBean = clearanceBean;
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                // 流程
                case R.id.txtSearch:
                    intent = new Intent(mContext, ContainerDetailsAct.class);
                    mContext.startActivity(intent);
                    break;
                // 航班航次
                case R.id.llMain:
                    intent = new Intent(mContext, FlightVoyageAct.class);
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
        private TextView txtShipName;
        private TextView txtSearch;
        private TextView txtPortOfDestination;
        private TextView txtEstimatedBerthingDate;
        private TextView txtActualBerthingDate;
        private TextView txtBoxNum;

        public OrderHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtShipName = (TextView) itemView.findViewById(R.id.txtShipName);
            txtSearch = (TextView) itemView.findViewById(R.id.txtSearch);
            txtPortOfDestination = (TextView) itemView.findViewById(R.id.txtPortOfDestination);
            txtEstimatedBerthingDate = (TextView) itemView.findViewById(R.id.txtEstimatedBerthingDate);
            txtActualBerthingDate = (TextView) itemView.findViewById(R.id.txtActualBerthingDate);
            txtBoxNum = (TextView) itemView.findViewById(R.id.txtBoxNum);
        }
    }

}
