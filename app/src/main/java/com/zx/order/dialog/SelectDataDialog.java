package com.zx.order.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.listener.IntListener;
import com.zx.order.listener.StrListListener;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * 委托场站--疏港目的下拉框
 * 作者：JHJ
 * 日期：2018/10/17 16:21
 * Q Q: 1320666709
 */
public class SelectDataDialog extends Dialog {
    private String sTitle;
    private Context mContext;
    private StrListListener strListListener;

    /**
     */
    public SelectDataDialog(@NonNull Context context, String sTitle, StrListListener strListListener) {
        super(context, R.style.dialog);
        this.sTitle = sTitle;
        this.mContext = context;
        this.strListListener = strListListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        final TextView txtEntrustStation = (TextView) findViewById(R.id.txtEntrustStation);
        final TextView txtPortOfDredging = (TextView) findViewById(R.id.txtPortOfDredging);
        Button btnClose = (Button) findViewById(R.id.btnClose);
        Button btnQuery = (Button) findViewById(R.id.btnQuery);
        txtTitle.setText(sTitle);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StrUtil.isEmpty(txtEntrustStation.getText().toString())) {
                    ToastUtil.showShort(mContext, "请选择委托场站！");
                } else if (StrUtil.isEmpty(txtPortOfDredging.getText().toString())) {
                    ToastUtil.showShort(mContext, "请选择疏港目的！");
                } else {
                    List<String> strList = new ArrayList<>();
                    strList.add(txtEntrustStation.getText().toString());
                    strList.add(txtPortOfDredging.getText().toString());
                    strListListener.selectStrList(strList);
                    dismiss();
                }
            }
        });

        txtEntrustStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> strList = new ArrayList<>();
                strList.add("类型1");
                strList.add("类型2");
                strList.add("类型3");
                strList.add("类型4");
                strList.add("类型5");
                DateUtils.optionPicker((Activity) mContext, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        txtEntrustStation.setText(strList.get(point));
                    }
                });
            }
        });

        txtPortOfDredging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> strList = new ArrayList<>();
                strList.add("目的1");
                strList.add("目的2");
                strList.add("目的3");
                strList.add("目的4");
                strList.add("目的5");
                DateUtils.optionPicker((Activity) mContext, strList, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        txtPortOfDredging.setText(strList.get(point));
                    }
                });
            }
        });
    }

    @Override
    public void show() {
        super.show();
        getWindow().setWindowAnimations(R.style.DialogInAndOutAnim);
    }
}