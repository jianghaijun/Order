package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zx.order.R;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.ToastUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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

        holder.collapsingToolbar.setTitle("管理员");
    }

    /**
     * 赋值
     */
    public void setDate() {
        Paint pFont = new Paint();
        pFont.setTextSize(20);
        Rect rect = new Rect();
        pFont.getTextBounds(holder.collapsingToolbar.getTitle().toString(), 0, holder.collapsingToolbar.getTitle().length(), rect);
        holder.collapsingToolbar.setExpandedTitleMarginStart(DensityUtil.dip2px(60 - (rect.width() / 2)));
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539258514126&di=e1d394e0049715c1a6a5aca71975cb80&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F86%2F29%2F11D58PICQiI_1024.jpg").apply(options).into(holder.ivHead);

        RequestOptions options2 = new RequestOptions();
        options2.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539259480260&di=d8ee2142db01d0bb10c02814bc51cf19&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c34a58ff0dfba801214550d6d5cb.gif").apply(options2).into(holder.ivBg);

        holder.btnUpDatePassword.setOnClickListener(new OnClick());
        holder.btnUpDateHead.setOnClickListener(new OnClick());
    }

    /**
     * 更换头像
     */
    private void uploadUserAvatar() {
        /*if (JudgeNetworkIsAvailable.isNetworkAvailable(mActivity)) {
            SelectPhotoWayDialog selectPhotoWayDialog = new SelectPhotoWayDialog(mActivity, new PromptListener() {
                @Override
                public void returnTrueOrFalse(boolean trueOrFalse) {
                    if (trueOrFalse) {
                        // 拍照
                        ISCameraConfig config = new ISCameraConfig.Builder()
                                .needCrop(true) // 裁剪
                                .cropSize(1, 1, 1200, 1200)
                                .build();
                        ISNav.getInstance().toCameraActivity(mActivity, config, 1001);
                    } else {
                        // 相册
                        ISListConfig config = new ISListConfig.Builder()
                                // 是否多选, 默认true
                                .multiSelect(false)
                                // 使用沉浸式状态栏
                                .statusBarColor(ContextCompat.getColor(mActivity, R.color.main_check_bg))
                                // 返回图标ResId
                                .backResId(R.drawable.back_btn)
                                // 标题
                                .title("照片")
                                // 标题文字颜色
                                .titleColor(Color.WHITE)
                                // TitleBar背景色
                                .titleBgColor(ContextCompat.getColor(mActivity, R.color.main_bg))
                                // 裁剪大小。needCrop为true的时候配置
                                .cropSize(1, 1, 1200, 1200)
                                .needCrop(true)
                                // 第一个是否显示相机，默认true
                                .needCamera(false)
                                // 最大选择图片数量，默认9
                                .maxNum(1)
                                .build();
                        // 跳转到图片选择器
                        ISNav.getInstance().toListActivity(mActivity, config, 1002);
                    }
                }
            });
            selectPhotoWayDialog.show();
        } else {
            ToastUtil.showShort(mActivity, mActivity.getString(R.string.not_network));
        }*/
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
        @ViewInject(R.id.btnUpDatePassword)
        private Button btnUpDatePassword;
        @ViewInject(R.id.btnUpDateHead)
        private Button btnUpDateHead;

        @ViewInject(R.id.collapsingToolbar)
        private CollapsingToolbarLayout collapsingToolbar;
    }
}
