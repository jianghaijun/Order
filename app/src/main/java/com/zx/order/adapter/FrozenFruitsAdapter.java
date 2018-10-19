package com.zx.order.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.bean.StatusBean;
import com.zx.order.dialog.PromptDialog;
import com.zx.order.listener.PromptListener;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 冻品、水果
 * 作者：JHJ
 * 日期：2018/10/12 11:20
 * Q Q: 1320666709
 */
public class FrozenFruitsAdapter extends RecyclerView.Adapter<FrozenFruitsAdapter.Holder> {
    private Context mContext;
    private List<StatusBean> mDataList;

    public FrozenFruitsAdapter(List<StatusBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_frozen_fruits, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final StatusBean bean = mDataList.get(position);

        holder.txtContainerNo.setText(bean.getContainerNo());

        FrozenFruitsStatusAdapter statusAdapter = new FrozenFruitsStatusAdapter(bean.getStatusList(), new PromptListener() {
            @Override
            public void isChoice(boolean trueOrFalse) {
                if (trueOrFalse) {
                    boolean isAllSelect = true;
                    for (StatusBean statusBean : bean.getStatusList()) {
                        if (!statusBean.isSelect()) {
                            isAllSelect = false;
                        }
                    }
                    if (isAllSelect) {
                        holder.cbSelect.setChecked(true);
                    }
                } else {
                    holder.cbSelect.setChecked(false);
                }
            }
        });
        holder.rvStatus.setLayoutManager(new GridLayoutManager(mContext, 4));
        holder.rvStatus.setAdapter(statusAdapter);

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.llMain.setOnClickListener(new TxtOnClickListener(bean, statusAdapter, holder));
        holder.cbSelect.setOnClickListener(new TxtOnClickListener(bean, statusAdapter, holder));
        holder.btnSubmit.setOnClickListener(new TxtOnClickListener(bean, statusAdapter, holder));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 点击事件
     */
    private class TxtOnClickListener implements View.OnClickListener {
        private Holder holder;
        private StatusBean statusBean;
        private FrozenFruitsStatusAdapter statusAdapter;

        TxtOnClickListener(StatusBean statusBean, FrozenFruitsStatusAdapter statusAdapter, Holder holder) {
            this.holder = holder;
            this.statusAdapter = statusAdapter;
            this.statusBean = statusBean;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 全选
                case R.id.cbSelect:
                    if (statusBean.getStatusList() != null) {
                        for (StatusBean bean : statusBean.getStatusList()) {
                            bean.setSelect(holder.cbSelect.isChecked());
                        }
                        statusAdapter.notifyDataSetChanged();
                    }
                    break;
                // 提交
                case R.id.btnSubmit:
                    new PromptDialog(mContext, new PromptListener() {
                        @Override
                        public void isChoice(boolean trueOrFalse) {
                            if (trueOrFalse) {
                                ToastUtil.showShort(mContext, "开发中...");
                            }
                        }
                    }, "提示", "确认提交么？", "取消", "确认").show();
                    break;
            }
        }
    }

    /**
     * 构造器
     */
    public class Holder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private TextView txtContainerNo;
        private CheckBox cbSelect;
        private RecyclerView rvStatus;
        private Button btnSubmit;

        public Holder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            txtContainerNo = (TextView) itemView.findViewById(R.id.txtContainerNo);
            cbSelect = (CheckBox) itemView.findViewById(R.id.cbSelect);
            rvStatus = (RecyclerView) itemView.findViewById(R.id.rvStatus);
            btnSubmit = (Button) itemView.findViewById(R.id.btnSubmit);
        }
    }

}
