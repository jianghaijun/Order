package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.activity.ReservationTabAct;
import com.zx.order.bean.ReservationBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 预约首页列表
 * 作者：JHJ
 * 日期：2018/10/12 9:32
 * Q Q: 1320666709
 */
public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {
    private Context mContext;
    private String frozenFruitsType;
    private List<ReservationBean> mDataList;

    public ReservationAdapter(List<ReservationBean> mDataList, String frozenFruitsType) {
        this.mDataList = mDataList;
        this.frozenFruitsType = frozenFruitsType;
    }

    @Override
    public ReservationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_reservation, null);
        return new ReservationHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationHolder holder, final int position) {
        final ReservationBean bean = mDataList.get(position);
        holder.txtBillLadingNo.setText("提单号：" + bean.getCargoBillNo());
        holder.txtNumber.setText("集装箱数量：" + bean.getPieces());

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReservationTabAct.class);
                intent.putExtra("frozenFruitsType", frozenFruitsType);
                intent.putExtra("cargoBillId", bean.getCargoBillId());
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
    public class ReservationHolder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private TextView txtBillLadingNo;
        private TextView txtNumber;

        public ReservationHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtBillLadingNo = (TextView) itemView.findViewById(R.id.txtBillLadingNo);
            txtNumber = (TextView) itemView.findViewById(R.id.txtNumber);
        }
    }

}
