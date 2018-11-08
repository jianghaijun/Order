package com.zx.order.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISCameraConfig;
import com.yuyh.library.imgsel.config.ISListConfig;
import com.zx.order.R;
import com.zx.order.dialog.SelectPhotoWayDialog;
import com.zx.order.listener.PromptListener;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.SpUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.hutool.core.util.StrUtil;

/**
 * 个人中心
 * 作者：JHJ
 * 日期：2018/10/12 17:36
 * Q Q: 1320666709
 */
public class PersonalCenterCla {
    private LatestInfoHolder holder;
    private Activity mContext;

    public PersonalCenterCla(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new LatestInfoHolder();
        x.view().inject(holder, layout);

        String userName = (String) SpUtil.get(mContext, ConstantsUtil.USER_NAME, "");
        if (StrUtil.isEmpty(userName)) {
            holder.txtUserName.setText("管理员");
        } else {
            holder.txtUserName.setText(userName);
        }

        // 自定义图片加载器
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }

    /**
     * 赋值
     */
    public void setDate() {
        RequestOptions options = new RequestOptions().circleCrop();
        String userHead = (String) SpUtil.get(mContext, ConstantsUtil.USER_HEAD_URL, "");
        if (StrUtil.isEmpty(userHead)) {
            Glide.with(mContext).load(R.drawable.user_avatar).apply(options).into(holder.ivHead);
        } else {
            Glide.with(mContext).load(userHead).apply(options).into(holder.ivHead);
        }

        RequestOptions options2 = new RequestOptions();
        options2.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539259480260&di=d8ee2142db01d0bb10c02814bc51cf19&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c34a58ff0dfba801214550d6d5cb.gif").apply(options2).into(holder.ivBg);

        holder.btnUpDatePassword.setOnClickListener(new OnClick());
        holder.btnUpDateHead.setOnClickListener(new OnClick());
        holder.btnLogout.setOnClickListener(new OnClick());
        holder.ivHead.setOnClickListener(new OnClick());
    }

    /**
     * 更换头像
     */
    private void uploadUserAvatar() {
        if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
            SelectPhotoWayDialog selectPhotoWayDialog = new SelectPhotoWayDialog(mContext, new PromptListener() {
                @Override
                public void isChoice(boolean isChoice) {
                    if (isChoice) {
                        // 拍照
                        ISCameraConfig config = new ISCameraConfig.Builder()
                                .needCrop(true) // 裁剪
                                .cropSize(1, 1, 1200, 1200)
                                .build();
                        ISNav.getInstance().toCameraActivity(mContext, config, 1001);
                    } else {
                        // 相册
                        ISListConfig config = new ISListConfig.Builder()
                                // 是否多选, 默认true
                                .multiSelect(false)
                                // 使用沉浸式状态栏
                                .statusBarColor(ContextCompat.getColor(mContext, R.color.main_check_bg))
                                // 返回图标ResId
                                .backResId(R.drawable.back_btn)
                                // 标题
                                .title("照片")
                                // 标题文字颜色
                                .titleColor(Color.WHITE)
                                // TitleBar背景色
                                .titleBgColor(ContextCompat.getColor(mContext, R.color.main_bg))
                                // 裁剪大小。needCrop为true的时候配置
                                .cropSize(1, 1, 1200, 1200)
                                .needCrop(true)
                                // 第一个是否显示相机，默认true
                                .needCamera(false)
                                // 最大选择图片数量，默认9
                                .maxNum(1)
                                .build();
                        // 跳转到图片选择器
                        ISNav.getInstance().toListActivity(mContext, config, 1002);
                    }
                }
            });
            selectPhotoWayDialog.show();
        } else {
            ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
        }
    }

    /**
     * 点击事件
     */
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                // 修改密码
                case R.id.btnUpDatePassword:
                    intent = new Intent(mContext, UpdatePassWordAct.class);
                    mContext.startActivity(intent);
                    break;
                // 修改头像
                case R.id.btnUpDateHead:
                    uploadUserAvatar();
                    break;
                // 注销登录
                case R.id.btnLogout:
                    SpUtil.put(mContext, ConstantsUtil.IS_LOGIN_SUCCESSFUL, false);
                    ScreenManagerUtil.popAllActivityExceptOne();
                    intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);
                    mContext.finish();
                    break;
                // 查看头像
                case R.id.ivHead:
                    intent = new Intent(mContext, ShowPhotosAct.class);
                    String userHead = (String) SpUtil.get(mContext, ConstantsUtil.USER_HEAD_URL, "");
                    intent.putExtra("imgUrl", userHead);
                    mContext.startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 容纳器
     */
    private class LatestInfoHolder {
        @ViewInject(R.id.ivBg)
        private ImageView ivBg;
        @ViewInject(R.id.ivHead)
        private ImageView ivHead;
        @ViewInject(R.id.txtUserName)
        private TextView txtUserName;
        @ViewInject(R.id.btnUpDatePassword)
        private Button btnUpDatePassword;
        @ViewInject(R.id.btnUpDateHead)
        private Button btnUpDateHead;
        @ViewInject(R.id.btnLogout)
        private Button btnLogout;
    }
}
