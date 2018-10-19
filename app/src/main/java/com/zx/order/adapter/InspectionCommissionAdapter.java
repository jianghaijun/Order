package com.zx.order.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.bean.InspectionCommissionBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 查验委托
 * 作者：JHJ
 * 日期：2018/10/17 15:07
 * Q Q: 1320666709
 */
public class InspectionCommissionAdapter extends RecyclerView.Adapter<InspectionCommissionAdapter.ReservationHolder> {
    private Context mContext;
    private Drawable selectDrawable;
    private Drawable unSelectDrawable;
    private List<InspectionCommissionBean> mDataList;

    public InspectionCommissionAdapter(List<InspectionCommissionBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public ReservationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        selectDrawable = ContextCompat.getDrawable(mContext, R.drawable.r_check);
        selectDrawable.setBounds(0, 0, selectDrawable.getMinimumWidth(), selectDrawable.getMinimumHeight());
        unSelectDrawable = ContextCompat.getDrawable(mContext, R.drawable.r_un_check);
        unSelectDrawable.setBounds(0, 0, unSelectDrawable.getMinimumWidth(), unSelectDrawable.getMinimumHeight());

        View view = View.inflate(parent.getContext(), R.layout.item_inspection_commission, null);
        return new ReservationHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReservationHolder holder, final int position) {
        final InspectionCommissionBean dataBean = mDataList.get(position);

        holder.txtNavigationalName.setText("航名：" + dataBean.getNavigationalName());
        holder.txtVoyageNum.setText("航次：" + dataBean.getVoyageNum());
        holder.txtWharf.setText("码头：" + dataBean.getWharf());

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        if (dataBean.isSelect()) {
            holder.txtNavigationalName.setCompoundDrawables(selectDrawable, null, null, null);
        } else {
            holder.txtNavigationalName.setCompoundDrawables(unSelectDrawable, null, null, null);
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (InspectionCommissionBean bean : mDataList) {
                    bean.setSelect(false);
                }

                dataBean.setSelect(true);
                notifyDataSetChanged();
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
    public class ReservationHolder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private TextView txtNavigationalName;
        private TextView txtVoyageNum;
        private TextView txtWharf;

        public ReservationHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtNavigationalName = (TextView) itemView.findViewById(R.id.txtNavigationalName);
            txtVoyageNum = (TextView) itemView.findViewById(R.id.txtVoyageNum);
            txtWharf = (TextView) itemView.findViewById(R.id.txtWharf);
        }
    }

}
