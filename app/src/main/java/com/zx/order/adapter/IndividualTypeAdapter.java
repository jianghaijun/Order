package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zx.order.R;
import com.zx.order.activity.AddIndividualAct;
import com.zx.order.activity.EntrustingTheHarbourAct;
import com.zx.order.activity.InspectionCommissionAct;
import com.zx.order.bean.IndividualBean;

import java.util.List;

/**
 * 预约
 * 作者：JHJ
 * 日期：2018/10/12 14:58
 * Q Q: 1320666709
 */
public class IndividualTypeAdapter extends RecyclerView.Adapter<IndividualTypeAdapter.Holder> {
    private Context mContext;
    private List<IndividualBean> mDataList;

    public IndividualTypeAdapter(List<IndividualBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_individual_type, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final IndividualBean individualBean = mDataList.get(position);
        // 应用图标
        Drawable drawable = ContextCompat.getDrawable(mContext, individualBean.getImgPath());
        Glide.with(mContext).load(drawable).into(holder.imgView);
        // 应用名称
        holder.txtTitle.setText(individualBean.getTypeName());

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (position == 0) {
                    // 疏港委托
                    intent = new Intent(mContext, EntrustingTheHarbourAct.class);
                } else if (position == 1) {
                    // 查验委托
                    intent = new Intent(mContext, InspectionCommissionAct.class);
                } else {
                    // 其它预约
                    intent = new Intent(mContext, AddIndividualAct.class);
                }
                intent.putExtra("clickPoint", position);
                intent.putExtra("title", individualBean.getTypeName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 构造器
     */
    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imgView;
        private TextView txtTitle;
        private TextView txtSubmitPhoneNum;
        private LinearLayout llMain;

        public Holder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgInfo);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtSubmitPhoneNum = (TextView) itemView.findViewById(R.id.txtSubmitPhoneNum);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
        }
    }

}
