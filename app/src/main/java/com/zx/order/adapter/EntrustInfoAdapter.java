package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;
import com.zx.order.R;
import com.zx.order.activity.WarehouseInfoActivity;
import com.zx.order.bean.BottomInfoBean;

import java.util.List;

/**
 * 适配器
 */
public class EntrustInfoAdapter extends RecyclerView.Adapter<EntrustInfoAdapter.BottomInfoHolder> {
    private List<String> mDataList;
    private Context mContext;

    public EntrustInfoAdapter(List<String> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public BottomInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_entrust_info, null);
        return new BottomInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(BottomInfoHolder holder, final int position) {
        holder.btnTitle.setText(mDataList.get(position));
        holder.btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for ()*/
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
    public class BottomInfoHolder extends RecyclerView.ViewHolder {
        private Button btnTitle;

        public BottomInfoHolder(View itemView) {
            super(itemView);
            btnTitle = (Button) itemView.findViewById(R.id.btnTitle);
        }
    }

}
