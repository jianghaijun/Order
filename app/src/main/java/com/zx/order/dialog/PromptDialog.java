package com.zx.order.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.listener.PromptListener;
import com.zx.order.utils.SpUtil;

import cn.hutool.core.util.StrUtil;

/**
 * 常用提示dialog
 */
public class PromptDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private PromptListener promptListener;
    private String sTitle, sContext, sLeftText, sRightText;

    /**
     * @param context
     * @param promptListener
     * @param sTitle         提示框标题
     * @param sContext       提示内容
     * @param sLeftText      左侧白色按钮文本
     * @param sRightText     右侧黄色按钮文本
     */
    public PromptDialog(@NonNull Context context, PromptListener promptListener, String sTitle, String sContext, String sLeftText, String sRightText) {
        super(context);
        this.mContext = context;
        this.sTitle = sTitle;
        this.sContext = sContext;
        this.sLeftText = sLeftText;
        this.sRightText = sRightText;
        this.promptListener = promptListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_prompt);

        Button btnRight = (Button) findViewById(R.id.query_setting_btn);
        Button btnLeft = (Button) findViewById(R.id.close_setting_btn);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        TextView txtContext = (TextView) findViewById(R.id.txtContext);

        txtTitle.setText(sTitle);
        txtContext.setText(sContext);
        if (StrUtil.isEmpty(sLeftText)) {
            btnLeft.setVisibility(View.GONE);
        }
        btnLeft.setText(sLeftText);
        btnRight.setText(sRightText);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 右侧
            case R.id.query_setting_btn:
                dismiss();
                SpUtil.put(mContext, "isShowDialog", false);
                promptListener.isChoice(true);
                break;
            // 左侧
            case R.id.close_setting_btn:
                dismiss();
                SpUtil.put(mContext, "isShowDialog", false);
                promptListener.isChoice(false);
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        getWindow().setWindowAnimations(R.style.DialogInAndOutAnim);
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            boolean isShowDialog = (boolean) SpUtil.get(mContext, "isShowDialog", false);
            if (isShowDialog) {

            } else {
                this.dismiss();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}