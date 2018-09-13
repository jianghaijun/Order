package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.zx.order.R;
import com.zx.order.activity.WarehouseDetailsActivity;
import com.zx.order.bean.OrderBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 仓库信息适配器
 */
public class WarehouseInfoAdapter extends RecyclerView.Adapter<WarehouseInfoAdapter.OrderHolder> {
    private List<OrderBean> mDataList;
    private Context mContext;

    public WarehouseInfoAdapter(List<OrderBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_warehouse, null);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        OrderBean orderBean = mDataList.get(position);

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        for (int i = 0; i < 3; i++) {
            // 添加一条
            LinearLayout.LayoutParams lpLl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(30));
            lpLl.setMargins(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), 0);

            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.touch_bg));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams lpTxt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lpTxt.weight = 1;

            TextView txtType = new TextView(mContext);
            txtType.setGravity(Gravity.CENTER);
            txtType.setText("肉类" + i);
            txtType.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            txtType.setTextSize(14);
            linearLayout.addView(txtType, lpTxt);

            TextView txtWeight = new TextView(mContext);
            txtWeight.setGravity(Gravity.CENTER);
            txtWeight.setText(i + 10 + "吨");
            txtWeight.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            txtWeight.setTextSize(14);
            linearLayout.addView(txtWeight, lpTxt);

            TextView txtNum = new TextView(mContext);
            txtNum.setGravity(Gravity.CENTER);
            txtNum.setText(i + 100 + "箱");
            txtNum.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            txtNum.setTextSize(14);
            linearLayout.addView(txtNum, lpTxt);

            holder.llContent.addView(linearLayout, lpLl);
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WarehouseDetailsActivity.class);
                mContext.startActivity(intent);
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
        private LinearLayout llContent;

        public OrderHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            llContent = (LinearLayout) itemView.findViewById(R.id.llContent);
        }
    }

}
