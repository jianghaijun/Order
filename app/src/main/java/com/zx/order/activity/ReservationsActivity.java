package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ReservationsActivity extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtMakeStates)
    private TextView txtMakeStates;
    @ViewInject(R.id.txtSafetyCode)
    private TextView txtSafetyCode;
    @ViewInject(R.id.imgQrCode)
    private ImageView imgQrCode;

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reservations);

        x.view().inject(this);

        ScreenManagerUtil.pushActivity(this);
        mContext = this;

        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        setData();
    }

    /**
     * 赋值
     */
    private void setData() {
        txtMakeStates.setText("您的预约'与昂雅安共'已预约成功，请您及时通知车队！");
        txtSafetyCode.setText("您的安全验证码为：123456！");
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537348006779&di=ca96b858287963585d8d2fca0da65a2e&imgtype=0&src=http%3A%2F%2Fimg.my.csdn.net%2Fuploads%2F201209%2F08%2F1347107324_8036.png").into(imgQrCode);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.btnYes, R.id.btnSendQrCode})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
            case R.id.btnYes:
                ScreenManagerUtil.popAllActivityExceptOne(MainAct.class);
                break;
            // 转发二维码
            case R.id.btnSendQrCode:
                ToastUtil.showShort(mContext, "待开发！");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
