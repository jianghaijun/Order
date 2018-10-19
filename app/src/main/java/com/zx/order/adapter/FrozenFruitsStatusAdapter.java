package com.zx.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.zx.order.R;
import com.zx.order.bean.StatusBean;
import com.zx.order.listener.PromptListener;

import java.util.List;

/**
 * 冻品、水果状态
 * 作者：JHJ
 * 日期：2018/10/12 14:02
 * Q Q: 1320666709
 */
public class FrozenFruitsStatusAdapter extends RecyclerView.Adapter<FrozenFruitsStatusAdapter.Holder> {
    private List<StatusBean> mDataList;
    private PromptListener promptListener;

    public FrozenFruitsStatusAdapter(List<StatusBean> mDataList, PromptListener promptListener) {
        this.mDataList = mDataList;
        this.promptListener = promptListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_frozen_fruits_status, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        StatusBean bean = mDataList.get(position);
        holder.cbStatus.setChecked(bean.isSelect());
        holder.cbStatus.setText(bean.getTitle());
        holder.cbStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.get(position).setSelect(!mDataList.get(position).isSelect());
                promptListener.isChoice(mDataList.get(position).isSelect());
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
        private CheckBox cbStatus;

        public Holder(View itemView) {
            super(itemView);
            cbStatus = (CheckBox) itemView.findViewById(R.id.cbStatus);
        }
    }

}
