package com.zx.order.activity;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zx.order.R;

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
    }

    /**
     * 容纳器
     */
    private class LatestInfoHolder {
        @ViewInject(R.id.ivBg)
        private ImageView ivBg;
        @ViewInject(R.id.ivHead)
        private ImageView ivHead;
        @ViewInject(R.id.collapsingToolbar)
        private CollapsingToolbarLayout collapsingToolbar;
    }
}
