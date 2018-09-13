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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.zx.order.R;
import com.zx.order.activity.OrderListActivity;
import com.zx.order.bean.LogisticsBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * 适配器
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {
    private List<LogisticsBean> mDataList;
    private Context mContext;

    public TimeLineAdapter(List<LogisticsBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_time_line, null);
        return new TimeLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        LogisticsBean logisticsBean = mDataList.get(position);

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);
        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.plan_progress);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        holder.txtTitle.setCompoundDrawables(drawable, null, null, null);
        holder.txtTitle.setText(logisticsBean.getMainTitle());

        int height = 0;
        List<LogisticsBean> contentInfoList = logisticsBean.getContentInfo();
        if (contentInfoList != null && contentInfoList.size() > 0) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(40));
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(30));

            for (LogisticsBean bean : contentInfoList) {
                height += DensityUtil.dip2px(40);
                // 添加标题
                TextView contentTitle = new TextView(mContext);
                contentTitle.setText(bean.getContentTitle());
                contentTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                contentTitle.setTextSize(14);
                contentTitle.setGravity(Gravity.LEFT|Gravity.CENTER);
                Drawable drawableContentTitle = ContextCompat.getDrawable(mContext, R.drawable.plan_progress);
                drawableContentTitle.setBounds(0, 0, drawableContentTitle.getMinimumWidth(), drawableContentTitle.getMinimumHeight());
                contentTitle.setCompoundDrawables(drawableContentTitle, null, null, null);
                contentTitle.setCompoundDrawablePadding(DensityUtil.dip2px(10));
                contentTitle.setPadding(DensityUtil.dip2px(10), 0, 0, 0);
                holder.llTxt.addView(contentTitle, lp);

                List<LogisticsBean> contentList = bean.getContent();
                if (contentList != null && contentList.size() > 0) {
                    int n;
                    for (n = 0; n + 2 < contentList.size(); n+=2) {
                        height += DensityUtil.dip2px(30);
                        LinearLayout linearLayout = new LinearLayout(mContext);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setPadding(DensityUtil.dip2px(52), 0, 0, 0);

                        // 添加内容
                        TextView contentLeft = new TextView(mContext);
                        String str = "<font color=\"#0099FF\">" + contentList.get(n).getContentNum() + "</font>";
                        String strContent = StrUtil.replace(contentList.get(n).getContentText(), "&", str);
                        contentLeft.setText(Html.fromHtml(strContent));
                        contentLeft.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                        contentLeft.setTextSize(12);
                        lp2.weight = 1;
                        linearLayout.addView(contentLeft, lp2);

                        TextView contentRight = new TextView(mContext);
                        String str2 = "<font color=\"#0099FF\">" + contentList.get(n+1).getContentNum() + "</font>";
                        String strContent2 = StrUtil.replace(contentList.get(n+1).getContentText(), "&", str2);
                        contentRight.setText(Html.fromHtml(strContent2));
                        contentRight.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                        contentRight.setTextSize(12);
                        lp2.weight = 1;
                        linearLayout.addView(contentRight, lp2);
                        holder.llTxt.addView(linearLayout, lp2);
                    }

                    if (contentList.size() - n > 0) {
                        height += DensityUtil.dip2px(30);
                        TextView contentRight = new TextView(mContext);
                        String str2 = "<font color=\"#0099FF\">" + contentList.get(contentList.size()-1).getContentNum() + "</font>";
                        String strContent2 = StrUtil.replace(contentList.get(contentList.size()-1).getContentText(), "&", str2);
                        contentRight.setText(Html.fromHtml(strContent2));
                        contentRight.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                        contentRight.setTextSize(12);
                        contentRight.setPadding(DensityUtil.dip2px(52), 0, 0, 0);
                        holder.llTxt.addView(contentRight, lp2);
                    }
                }
            }
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderListActivity.class);
                mContext.startActivity(intent);
            }
        });

        ViewGroup.LayoutParams lpv = holder.view.getLayoutParams();
        lpv.height = height + DensityUtil.dip2px(2);
        lpv.width = DensityUtil.dip2px(1);
        holder.view.setLayoutParams(lpv);
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
    public class TimeLineViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private TextView txtTitle;
        private LinearLayout llTxt;
        private View view;

        public TimeLineViewHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            llTxt = (LinearLayout) itemView.findViewById(R.id.llTxt);
            view = (View) itemView.findViewById(R.id.view);
        }
    }

}
