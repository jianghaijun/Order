package com.zx.order.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.bean.InspectionCommissionBean;
import com.zx.order.listener.IntListener;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.InputFilterUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;

/**
 * 查验委托---下一步列表
 * 作者：JHJ
 * 日期：2018/10/18 10:37
 * Q Q: 1320666709
 */
public class InspectionCommissionListAdapter extends RecyclerView.Adapter<InspectionCommissionListAdapter.ReservationHolder> {
    private Context mContext;
    private List<InspectionCommissionBean> mDataList;

    public InspectionCommissionListAdapter(List<InspectionCommissionBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public ReservationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(), R.layout.item_inspection_commission_list, null);
        return new ReservationHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReservationHolder holder, final int position) {
        final InspectionCommissionBean dataBean = mDataList.get(position);
        initData(dataBean, holder);

        LinearLayout.LayoutParams lvp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lvp.setMargins(0, DensityUtil.dip2px(10), 0, 0);
        holder.llMain.setLayoutParams(lvp);

        holder.cbBoxNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setSelect(holder.cbBoxNo.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 赋值
     *
     * @param dataBean
     * @param holder
     */
    private void initData(InspectionCommissionBean dataBean, ReservationHolder holder) {
        holder.llContent.removeAllViews();

        List<InspectionCommissionBean> beanList = dataBean.getNextDataList();
        if (beanList != null && beanList.size() > 0) {
            int size = beanList.size();
            int tenDp = DensityUtil.dip2px(10);
            LinearLayout.LayoutParams titleKeyLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams titleValLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            titleValLp.weight = 1;
            titleValLp.setMargins(tenDp, 0, tenDp, 0);
            LinearLayout.LayoutParams titleValLp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            titleValLp2.weight = 1;
            titleValLp2.setMargins(tenDp, 0, DensityUtil.dip2px(5), 0);
            LinearLayout.LayoutParams llContentLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(30));
            llContentLp.setMargins(0, 0, 0, DensityUtil.dip2px(5));
            LinearLayout.LayoutParams mmw = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            mmw.weight = 1;

            InspectionCommissionBean titleBean = beanList.get(0);

            holder.llTitle.addView(addNewTitleTxt(titleBean), titleKeyLp);
            if (StrUtil.equals(titleBean.getControlType(), "1")) {
                holder.llTitle.addView(addNewEdt(titleBean, dataBean), titleValLp);
            } else if (StrUtil.equals(titleBean.getControlType(), "2")) {
                holder.llTitle.addView(addNewSelect(titleBean, dataBean), titleValLp);
            } else if (StrUtil.equals(titleBean.getControlType(), "3")) {
                holder.llTitle.addView(addNewDate(titleBean, dataBean), titleValLp);
            }

            int contentSize = (size - 1) / 2;
            if ((size - 1) % 2 != 0) contentSize++;
            for (int i = 0; i < contentSize; i++) {
                int point = i * 2 + 1;
                LinearLayout llContent = new LinearLayout(mContext);
                llContent.setOrientation(LinearLayout.HORIZONTAL);
                if (point < size) {
                    InspectionCommissionBean leftBean = beanList.get(point);
                    LinearLayout llLeft = new LinearLayout(mContext);
                    llLeft.setOrientation(LinearLayout.HORIZONTAL);
                    llLeft.addView(addNewTitleTxt(leftBean), titleKeyLp);
                    if (StrUtil.equals(leftBean.getControlType(), "1")) {
                        llLeft.addView(addNewEdt(leftBean, dataBean), titleValLp2);
                    } else if (StrUtil.equals(leftBean.getControlType(), "2")) {
                        llLeft.addView(addNewSelect(leftBean, dataBean), titleValLp2);
                    } else if (StrUtil.equals(leftBean.getControlType(), "3")) {
                        llLeft.addView(addNewDate(leftBean, dataBean), titleValLp2);
                    }
                    llContent.addView(llLeft, mmw);
                }

                if (point + 1 < size) {
                    InspectionCommissionBean rightBean = beanList.get(point + 1);
                    LinearLayout llRight = new LinearLayout(mContext);
                    llRight.setOrientation(LinearLayout.HORIZONTAL);
                    llRight.addView(addNewTitleTxt(rightBean), titleKeyLp);
                    if (StrUtil.equals(rightBean.getControlType(), "1")) {
                        llRight.addView(addNewEdt(rightBean, dataBean), titleValLp2);
                    } else if (StrUtil.equals(rightBean.getControlType(), "2")) {
                        llRight.addView(addNewSelect(rightBean, dataBean), titleValLp2);
                    } else if (StrUtil.equals(rightBean.getControlType(), "3")) {
                        llRight.addView(addNewDate(rightBean, dataBean), titleValLp2);
                    }
                    llContent.addView(llRight, mmw);
                }
                holder.llContent.addView(llContent, llContentLp);
            }
        }
    }

    /**
     * 添加一个TextView
     *
     * @param bean 文本样式
     * @return
     */
    private TextView addNewTitleTxt(InspectionCommissionBean bean) {
        TextView txt = new TextView(mContext);
        txt.setText(bean.getTxtName());
        txt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        txt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        txt.setGravity(Gravity.LEFT | Gravity.CENTER);
        return txt;
    }

    /**
     * 添加一个EditText
     *
     * @param bean 文本样式
     * @param dataBean 文本样式
     * @return
     */
    private EditText addNewEdt(InspectionCommissionBean bean, InspectionCommissionBean dataBean) {
        EditText edt = new EditText(mContext);
        edt.setText(bean.getTxtContent());
        edt.setFilters(InputFilterUtil.inputFilter((Activity) mContext));
        edt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        edt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        edt.setGravity(Gravity.LEFT | Gravity.CENTER);
        edt.setHint(bean.getHint());
        edt.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
        edt.setPadding(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5));

        Map<String, Object> objMap = new HashMap<>();
        objMap.put("controlType", "1");
        objMap.put("control", edt);
        objMap.put("submitFieldName", bean.getSubmitFieldName());
        dataBean.getSubmitList().add(objMap);
        return edt;
    }

    /**
     * 添加一个下拉框
     *
     * @param bean 文本样式
     * @param dataBean 文本样式
     * @return
     */
    private TextView addNewSelect(final InspectionCommissionBean bean, InspectionCommissionBean dataBean) {
        final TextView txt = new TextView(mContext);
        txt.setText(bean.getSelectOption());
        txt.setHint(bean.getSelectOptionId());
        txt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        txt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        txt.setGravity(Gravity.LEFT | Gravity.CENTER);
        txt.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
        txt.setPadding(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5));
        Drawable rightDrawable = ContextCompat.getDrawable(mContext, R.drawable.drop_down);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        txt.setCompoundDrawables(null, null, rightDrawable, null);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<InspectionCommissionBean> beans = bean.getOptions();
                if (beans == null || beans.size() == 0) {
                    ToastUtil.showShort(mContext, "暂无数据！");
                } else {
                    final List<String> strList = new ArrayList<>();
                    for (InspectionCommissionBean commissionBean : beans) {
                        strList.add(commissionBean.getOption());
                    }
                    DateUtils.optionPicker((Activity) mContext, strList, new IntListener() {
                        @Override
                        public void selectPoint(int point) {
                            txt.setText(strList.get(point));
                            txt.setHint(beans.get(point).getOptionId());
                        }
                    });
                }
            }
        });

        Map<String, Object> objMap = new HashMap<>();
        objMap.put("controlType", "2");
        objMap.put("control", txt);
        objMap.put("submitFieldName", bean.getSubmitFieldName());
        dataBean.getSubmitList().add(objMap);

        return txt;
    }

    /**
     * 添加一个日期选择控件
     *
     * @param bean 文本样式
     * @param dataBean 文本样式
     * @return
     */
    private TextView addNewDate(final InspectionCommissionBean bean, InspectionCommissionBean dataBean) {
        final TextView txt = new TextView(mContext);
        txt.setText(bean.getTxtContent());
        txt.setTextColor(ContextCompat.getColor(mContext, R.color.dark_b));
        txt.setTextSize(StrUtil.isEmpty(bean.getTxtSize()) ? 14 : Integer.parseInt(bean.getTxtSize()));
        txt.setGravity(Gravity.LEFT | Gravity.CENTER);
        txt.setHint(bean.getHint());
        txt.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gray_stroke_white_solid_bg));
        txt.setPadding(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5));
        Drawable rightDrawable = ContextCompat.getDrawable(mContext, R.drawable.date);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        txt.setCompoundDrawables(null, null, rightDrawable, null);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StrUtil.equals(bean.getDateTimeType(), "1")) {
                    DateUtils.onYearMonthDayPicker((Activity) mContext, txt);
                } else if (StrUtil.equals(bean.getDateTimeType(), "2")) {
                    DateUtils.onTimePicker((Activity) mContext, txt);
                } else if (StrUtil.equals(bean.getDateTimeType(), "3")) {
                    DateUtils.onYearMonthDayTimePicker((Activity) mContext, txt);
                } else {
                    ToastUtil.showShort(mContext, "传入日期类型有误！");
                }
            }
        });

        Map<String, Object> objMap = new HashMap<>();
        objMap.put("controlType", "3");
        objMap.put("control", txt);
        objMap.put("submitFieldName", bean.getSubmitFieldName());
        dataBean.getSubmitList().add(objMap);

        return txt;
    }

    /**
     * 构造器
     */
    public class ReservationHolder extends RecyclerView.ViewHolder {
        private LinearLayout llMain;
        private CheckBox cbBoxNo;
        private LinearLayout llTitle;
        private LinearLayout llContent;

        public ReservationHolder(View itemView) {
            super(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.llMain);
            cbBoxNo = (CheckBox) itemView.findViewById(R.id.cbBoxNo);
            llTitle = (LinearLayout) itemView.findViewById(R.id.llTitle);
            llContent = (LinearLayout) itemView.findViewById(R.id.llContent);
        }
    }
}
