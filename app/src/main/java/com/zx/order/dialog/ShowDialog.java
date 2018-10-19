package com.zx.order.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.adapter.ShowDataAdapter;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * 展示
 * 作者：JHJ
 * 日期：2018/10/10 9:30
 * Q Q: 1320666709
 */
public class ShowDialog extends Dialog {
    private String sTitle;
    private Context mContext;
    private List<String> strList;

    /**
     */
    public ShowDialog(@NonNull Context context, String sTitle, List<String> strList) {
        super(context, R.style.dialog);
        this.sTitle = sTitle;
        this.strList = strList;
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_show);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(sTitle);

        RecyclerView rvData = (RecyclerView) findViewById(R.id.rvData);
        ShowDataAdapter showDataAdapter = new ShowDataAdapter(mContext, strList);
        rvData.setLayoutManager(new GridLayoutManager(mContext, 1));
        rvData.setAdapter(showDataAdapter);
    }

    @Override
    public void show() {
        super.show();
        // 设置全屏展示---必须先设置super(context, R.style.dialog);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = (int) (DensityUtil.getScreenHeight() * 0.4);
        getWindow().setAttributes(layoutParams);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setWindowAnimations(R.style.DialogInAndOutBottom);
        getWindow().setGravity(Gravity.BOTTOM);

    }
}