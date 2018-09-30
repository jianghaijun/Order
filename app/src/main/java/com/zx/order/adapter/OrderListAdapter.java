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
import com.zx.order.activity.OrderDetailsActivity;
import com.zx.order.bean.OrderBean;
import com.zx.order.utils.DateUtils;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 适配器
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderHolder> {
    private List<OrderBean> mDataList;
    private int type;
    private Context mContext;

    public OrderListAdapter(List<OrderBean> mDataList, int type) {
        this.type = type;
        this.mDataList = mDataList;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_order, null);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        OrderBean orderBean = mDataList.get(position);

        if (type == 1 || type == 3) {
            holder.llStatus.setVisibility(View.GONE);
        }

        holder.txtTitle.setText(orderBean.getTitle());
        holder.txtCommodityName.setText("商品名称：" + orderBean.getCommodityName());
        holder.txtStart.setText(orderBean.getStates());
        holder.txtLadingNo.setText("提单号：" + orderBean.getBillOfLadingNo());
        holder.txtOrderNo.setText("订单号：" + orderBean.getOrderNo());
        holder.txtOrderDate.setText("订单日期：" + DateUtils.setDataToStr(orderBean.getOrderDate()));
        holder.txtPortDate.setText("到港日期：" + DateUtils.setDataToStr(orderBean.getToThePortDate()));
        //holder.txtTextLabel.setText("文本标签：" + orderBean.getTextLabel());

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 3) {
                    Intent intent = new Intent(mContext, ContainerDetailsAct.class);
                    intent.putExtra("type", type);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, OrderDetailsActivity.class);
                    intent.putExtra("type", type);
                    mContext.startActivity(intent);
                }
            }
        });
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
     * 构造器
     */
    public class OrderHolder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private TextView txtTitle;
        private TextView txtStart;
        private TextView txtCommodityName;
        private TextView txtLadingNo;
        private TextView txtOrderNo;
        private TextView txtOrderDate;
        private TextView txtPortDate;
        private TextView txtUncollected;
        private TextView txtNotConfirmed;
        private TextView txtConfirmed;
        private LinearLayout llStatus;

        public OrderHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtStart = (TextView) itemView.findViewById(R.id.txtStart);
            txtCommodityName = (TextView) itemView.findViewById(R.id.txtCommodityName);
            txtLadingNo = (TextView) itemView.findViewById(R.id.txtLadingNo);
            txtOrderNo = (TextView) itemView.findViewById(R.id.txtOrderNo);
            txtOrderDate = (TextView) itemView.findViewById(R.id.txtOrderDate);
            txtPortDate = (TextView) itemView.findViewById(R.id.txtPortDate);
            txtUncollected = (TextView) itemView.findViewById(R.id.txtUncollected);
            txtNotConfirmed = (TextView) itemView.findViewById(R.id.txtNotConfirmed);
            txtConfirmed = (TextView) itemView.findViewById(R.id.txtConfirmed);
            llStatus = (LinearLayout) itemView.findViewById(R.id.llStatus);
        }
    }

}
