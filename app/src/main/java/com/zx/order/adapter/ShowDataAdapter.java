package com.zx.order.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;

import java.util.List;

/**
 * 数据展示
 * 作者：JHJ
 * 日期：2018/10/10 9:41
 * Q Q: 1320666709
 */
public class ShowDataAdapter extends RecyclerView.Adapter<ShowDataAdapter.ShowDataHold> {
    private Activity mContext;
    private List<String> dataList;

    public ShowDataAdapter(Context mContext, List<String> dataList) {
        this.mContext = (Activity) mContext;
        this.dataList = dataList;
    }

    @Override
    public ShowDataHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowDataHold(LayoutInflater.from(mContext).inflate(R.layout.item_show_data, parent, false));
    }

    @Override
    public void onBindViewHolder(ShowDataHold holder, int position) {
        holder.txtContext.setText(dataList.get(position));
        if (position == dataList.size() - 1) {
            holder.txtDownArrow.setVisibility(View.GONE);
        } else {
            holder.txtDownArrow.setVisibility(View.VISIBLE);
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    /**
     * 容纳器
     */
    public class ShowDataHold extends RecyclerView.ViewHolder {
        private TextView txtContext;
        private TextView txtDownArrow;
        private LinearLayout llMain;

        public ShowDataHold(View itemView) {
            super(itemView);
            txtContext = (TextView) itemView.findViewById(R.id.txtContext);
            txtDownArrow = (TextView) itemView.findViewById(R.id.txtDownArrow);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
        }
    }
}