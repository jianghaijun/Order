package com.zx.order.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.listener.PromptListener;

/**
 * 选择照片方式
 * 作者：JHJ
 * 日期：2018/11/8 14:01
 * Q Q: 1320666709
 */
public class SelectPhotoWayDialog extends Dialog implements View.OnClickListener {
    private PromptListener selectListener;

    public SelectPhotoWayDialog(Context context, PromptListener selectListener) {
        super(context);
        this.selectListener = selectListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_photos_way);

        Button btnCancel = (Button) findViewById(R.id.btnCancel);

        TextView txtAlbum = (TextView) findViewById(R.id.txtAlbum);
        TextView txtPhotograph = (TextView) findViewById(R.id.txtPhotograph);

        btnCancel.setOnClickListener(this);
        txtAlbum.setOnClickListener(this);
        txtPhotograph.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 取消
            case R.id.btnCancel:
                dismiss();
                break;
            // 拍照
            case R.id.txtPhotograph:
                dismiss();
                selectListener.isChoice(true);
                break;
            // 相册
            case R.id.txtAlbum:
                dismiss();
                selectListener.isChoice(false);
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        getWindow().setWindowAnimations(R.style.DialogInAndOutAnim);
    }
}
