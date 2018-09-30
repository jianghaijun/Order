package com.zx.order.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zx.order.R;
import com.zx.order.bean.MainPageBean;
import com.zx.order.utils.ToastUtil;

import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * Created by HaiJun on 2018/6/11 17:01
 * 应用信息适配器
 */
public class AddAdapter extends RecyclerView.Adapter<AddAdapter.AppInfoHold> {
    private Activity mContext;
    private List<MainPageBean> appInfoBeanList;

    public AddAdapter(Context mContext, List<MainPageBean> appInfoBeanList) {
        this.mContext = (Activity) mContext;
        this.appInfoBeanList = appInfoBeanList;
    }

    @Override
    public AppInfoHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppInfoHold(LayoutInflater.from(mContext).inflate(R.layout.item_add, parent, false));
    }

    @Override
    public void onBindViewHolder(AppInfoHold holder, final int position) {
        // 应用图标
        Glide.with(mContext).load(appInfoBeanList.get(position).getSmallModuleIcon()).into(holder.imgView);
        // 应用名称
        holder.txtTitle.setText(appInfoBeanList.get(position).getSmallModuleTitle());
        // 应用通知数量
        String unReadNum = appInfoBeanList.get(position).getSmallModuleCount();
        if (StrUtil.isNotEmpty(unReadNum)) {
            int num = Integer.valueOf(unReadNum);
            if (num != 0) {
                holder.txtSubmitPhoneNum.setVisibility(View.VISIBLE);
                if (num > 99) {
                    holder.txtSubmitPhoneNum.setTextSize(6);
                }
                holder.txtSubmitPhoneNum.setText(num > 99 ? "99+" : num + "");
            }
        }

        // 图标点击事件
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext, "该功能正在开发中，敬请期待!");
            }
        });
    }

    @Override
    public int getItemCount() {
        return appInfoBeanList == null ? 0 : appInfoBeanList.size();
    }

    /**
     * 容纳器
     */
    public class AppInfoHold extends RecyclerView.ViewHolder {
        private ImageView imgView;
        private TextView txtTitle;
        private TextView txtSubmitPhoneNum;
        private LinearLayout llMain;

        public AppInfoHold(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgInfo);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtSubmitPhoneNum = (TextView) itemView.findViewById(R.id.txtSubmitPhoneNum);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
        }
    }
}
