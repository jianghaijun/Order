package com.zx.order.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.zx.order.R;
import com.zx.order.base.BaseActivity;
import com.zx.order.utils.ScreenManagerUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 使用ViewPager展示照片
 * 作者：JHJ
 * 日期：2018/11/8 17:32
 * Q Q: 1320666709
 */
public class ShowPhotosAct extends BaseActivity {
    @ViewInject(R.id.imgViewPhoto)
    private PhotoView imgViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_show_photos);
        x.view().inject(this);
        ScreenManagerUtil.pushActivity(this);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.rotate_pro_loading)
                .error(R.drawable.load_error);

        imgViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPhotosAct.this.finish();
            }
        });

        ObjectAnimator anim = ObjectAnimator.ofInt(imgViewPhoto, "ImageLevel", 0, 10000);
        anim.setDuration(800);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();

        Glide.with(this)
                .load(getIntent().getStringExtra("imgUrl"))
                .apply(options)
                .thumbnail(0.1f)
                .into(imgViewPhoto);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.popActivity(this);
    }
}
