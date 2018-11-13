package com.zx.order.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zx.order.R;
import com.zx.order.activity.ShowPhotoListAct;
import com.zx.order.dialog.PromptDialog;
import com.zx.order.listener.PromptListener;

import java.util.ArrayList;

/**
 * 照片列表适配器
 * 作者：JHJ
 * 日期：2018/11/9 15:54
 * Q Q: 1320666709
 */
public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.ImgInfoHolder> {
    private Activity mContext;
    private ArrayList<String> imgUrls;
    private RequestOptions options;

    public PhotosListAdapter(Context mContext, ArrayList<String> imgUrls) {
        this.mContext = (Activity) mContext;
        this.imgUrls = imgUrls;
        options = new RequestOptions()
                .placeholder(R.drawable.rotate_pro_loading)
                .error(R.drawable.error);
    }

    @Override
    public ImgInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImgInfoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_imgs_info, parent, false));
    }

    @Override
    public void onBindViewHolder(final ImgInfoHolder holder, final int position) {
        ObjectAnimator anim = ObjectAnimator.ofInt(holder.ivUpLoadPhone, "ImageLevel", 0, 10000);
        anim.setDuration(800);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();

        String fileUrl = imgUrls.get(position);
        Glide.with(mContext)
                .load(fileUrl)
                .apply(options)
                .thumbnail(0.1f)
                .into(holder.ivUpLoadPhone);

        // 图片点击事件
        holder.ivUpLoadPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 图片浏览方式
                Intent intent = new Intent(mContext, ShowPhotoListAct.class);
                intent.putExtra(ShowPhotoListAct.EXTRA_IMAGE_URLS, imgUrls);
                intent.putExtra(ShowPhotoListAct.EXTRA_IMAGE_INDEX, position);
                mContext.startActivity(intent);
            }
        });

        /**
         * 长按事件
         */
        holder.ivUpLoadPhone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PromptDialog promptDialog = new PromptDialog(mContext, new PromptListener() {
                    @Override
                    public void isChoice(boolean isChoice) {
                        if (isChoice) {
                            // 删除照片
                            imgUrls.remove(position);
                            PhotosListAdapter.this.notifyDataSetChanged();
                        }
                    }
                }, "提示", "是否删除此照片？", "否", "是");
                promptDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgUrls == null ? 0 : imgUrls.size();
    }

    public class ImgInfoHolder extends RecyclerView.ViewHolder {
        private ImageView ivUpLoadPhone;

        public ImgInfoHolder(View itemView) {
            super(itemView);
            ivUpLoadPhone = (ImageView) itemView.findViewById(R.id.ivUpLoadPhone);
        }
    }

}
