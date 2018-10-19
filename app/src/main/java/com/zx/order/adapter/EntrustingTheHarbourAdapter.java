package com.zx.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.activity.ReservationTabAct;
import com.zx.order.bean.EntrustingTheHarbourBean;
import com.zx.order.bean.ReservationBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 疏港委托
 * 作者：JHJ
 * 日期：2018/10/17 15:07
 * Q Q: 1320666709
 */
public class EntrustingTheHarbourAdapter extends RecyclerView.Adapter<EntrustingTheHarbourAdapter.ReservationHolder> {
    private Context mContext;
    private List<EntrustingTheHarbourBean> mDataList;

    public EntrustingTheHarbourAdapter(List<EntrustingTheHarbourBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public ReservationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_entrusting_the_harbour, null);
        return new ReservationHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReservationHolder holder, final int position) {
        final EntrustingTheHarbourBean dataBean = mDataList.get(position);

        holder.cbBillLadingNo.setText("提单号：" + dataBean.getBillOfLadingNo());
        holder.txtEnglishShipName.setText("英文船名：" + dataBean.getEnglishShipName());
        holder.txtImportVoyage.setText("进口航次：" + dataBean.getImportVoyage());
        holder.txtBoxNum.setText("箱号：" + dataBean.getBoxNum());
        holder.txtBoxType.setText("箱型：" + dataBean.getBoxType());
        holder.txtBoxSize.setText("尺寸：" + dataBean.getBoxSize());
        holder.txtHarbourDredge.setText("疏港码头：" + dataBean.getHarbourDredge());

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setSelect(!holder.cbBillLadingNo.isChecked());
                holder.cbBillLadingNo.setChecked(!holder.cbBillLadingNo.isChecked());
            }
        });

        holder.cbBillLadingNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setSelect(!holder.cbBillLadingNo.isChecked());
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
        private CheckBox cbBillLadingNo;
        private TextView txtEnglishShipName;
        private TextView txtImportVoyage;
        private TextView txtBoxNum;
        private TextView txtBoxType;
        private TextView txtBoxSize;
        private TextView txtHarbourDredge;

        public ReservationHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            cbBillLadingNo = (CheckBox) itemView.findViewById(R.id.cbBillLadingNo);
            txtEnglishShipName = (TextView) itemView.findViewById(R.id.txtEnglishShipName);
            txtImportVoyage = (TextView) itemView.findViewById(R.id.txtImportVoyage);
            txtBoxNum = (TextView) itemView.findViewById(R.id.txtBoxNum);
            txtBoxType = (TextView) itemView.findViewById(R.id.txtBoxType);
            txtBoxSize = (TextView) itemView.findViewById(R.id.txtBoxSize);
            txtHarbourDredge = (TextView) itemView.findViewById(R.id.txtHarbourDredge);
        }
    }

}
