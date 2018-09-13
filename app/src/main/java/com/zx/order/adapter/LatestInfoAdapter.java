package com.zx.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;
import com.zx.order.R;
import com.zx.order.bean.LatestInfoBean;

import java.util.List;

import cn.hutool.core.date.DateUtil;

/**
 * 适配器
 */
public class LatestInfoAdapter extends RecyclerView.Adapter<LatestInfoAdapter.BottomInfoHolder> {
    private List<LatestInfoBean> mDataList;
    private Context mContext;

    public LatestInfoAdapter(List<LatestInfoBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public BottomInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_latest_info, null);
        return new BottomInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(BottomInfoHolder holder, final int position) {
        LatestInfoBean bean = mDataList.get(position);

        Glide.with(mContext).load(bean.getImgUrl()).into(holder.ivNewsImg);
        holder.txtTitle.setText(bean.getTitle());
        holder.txtNewsDate.setText(DateUtil.format(DateUtil.date(bean.getNewsDate()), "yyyy-MM-dd"));
        holder.txtContent.setText(bean.getContent());
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
        private ImageView ivNewsImg;
        private TextView txtTitle;
        private TextView txtNewsDate;
        private TextView txtContent;

        public BottomInfoHolder(View itemView) {
            super(itemView);
            ivNewsImg = (ImageView) itemView.findViewById(R.id.ivNewsImg);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtNewsDate = (TextView) itemView.findViewById(R.id.txtNewsDate);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        }
    }

}
