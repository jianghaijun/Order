package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;
import com.zx.order.R;
import com.zx.order.activity.WarehouseInfoActivity;
import com.zx.order.bean.BottomInfoBean;
import com.zx.order.bean.LogisticsBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * 适配器
 */
public class BottomInfoAdapter extends RecyclerView.Adapter<BottomInfoAdapter.BottomInfoHolder> {
    private List<BottomInfoBean> mDataList;
    private Context mContext;

    public BottomInfoAdapter(List<BottomInfoBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public BottomInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_bottom_info, null);
        return new BottomInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(BottomInfoHolder holder, final int position) {
        BottomInfoBean logisticsBean = mDataList.get(position);
        // 应用图标
        Glide.with(mContext).load(logisticsBean.getImgUrl()).into(holder.imgView);
        // 应用名称
        holder.txtTitle.setText(logisticsBean.getTitle());
        // 应用通知数量
        String unReadNum = logisticsBean.getCount();
        int num = Integer.valueOf(unReadNum);
        if (num != 0) {
            holder.txtSubmitPhoneNum.setVisibility(View.VISIBLE);
            if (num > 99) {
                holder.txtSubmitPhoneNum.setTextSize(6);
            }
            holder.txtSubmitPhoneNum.setText(num > 99 ? "99+" : num + "");
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(mContext, WarehouseInfoActivity.class);
                        mContext.startActivity(intent);
                        break;
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
    public class BottomInfoHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;
        private TextView txtTitle;
        private TextView txtSubmitPhoneNum;
        private LinearLayout llMain;

        public BottomInfoHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgInfo);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtSubmitPhoneNum = (TextView) itemView.findViewById(R.id.txtSubmitPhoneNum);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
        }
    }

}
