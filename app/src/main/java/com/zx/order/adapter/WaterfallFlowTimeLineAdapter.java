package com.zx.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.bean.TimeLineBean;
import com.zx.order.utils.DateUtils;

import java.util.List;

/**
 * 瀑布流格式时间轴
 * 作者：JHJ
 * 日期：2018/10/12 17:42
 * Q Q: 1320666709
 */
public class WaterfallFlowTimeLineAdapter extends RecyclerView.Adapter<WaterfallFlowTimeLineAdapter.ViewHolder> {
    private Context mContext;
    private List<TimeLineBean> mList;

    public WaterfallFlowTimeLineAdapter(Context context, List<TimeLineBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resource;
        if (mList.size() == 1) {
            resource = R.layout.item_one_dot_time_line;
        } else {
            resource = R.layout.item_dot_time_line;
        }
        View view = LayoutInflater.from(mContext).inflate(resource, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TimeLineBean timeLineBean = mList.get(position);
        holder.txtStatus.setText(timeLineBean.getStatus());
        if (position == 0) {
            holder.txtName.setText(timeLineBean.getTitle());
        } else {
            holder.txtName.setText(DateUtils.setDataToStr2(timeLineBean.getActionTime()));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStatus;
        TextView txtName;

        public ViewHolder(View view) {
            super(view);
            txtStatus = (TextView) view.findViewById(R.id.txtStatus);
            txtName = (TextView) view.findViewById(R.id.txtName);
        }
    }
}
