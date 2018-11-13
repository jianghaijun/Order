package com.zx.order.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;
import com.zx.order.R;
import com.zx.order.adapter.PhotosListAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.dialog.SelectPhotoWayDialog;
import com.zx.order.listener.IntListener;
import com.zx.order.listener.PermissionListener;
import com.zx.order.listener.PromptListener;
import com.zx.order.model.ClearanceInspectionModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ProviderUtil;
import com.zx.order.utils.ScreenManagerUtil;
import com.zx.order.utils.SpUtil;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 投诉反馈
 * 作者：JHJ
 * 日期：2018/11/9 14:31
 * Q Q: 1320666709
 */
public class ComplaintFeedbackAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.txtQuestionType) // 问题类型
    private TextView txtQuestionType;
    @ViewInject(R.id.llPeopleOfComplaint) // 投诉人员
    private LinearLayout llPeopleOfComplaint;
    @ViewInject(R.id.vPeopleOfComplaint) // 投诉人员
    private View vPeopleOfComplaint;
    @ViewInject(R.id.edtPeopleOfComplaint) // 投诉人员
    private EditText edtPeopleOfComplaint;
    @ViewInject(R.id.llCauseOfComplaint) // 投诉原因
    private LinearLayout llCauseOfComplaint;
    @ViewInject(R.id.vCauseOfComplaint) // 投诉原因
    private View vCauseOfComplaint;
    @ViewInject(R.id.edtCauseOfComplaint) // 投诉原因
    private EditText edtCauseOfComplaint;
    @ViewInject(R.id.edtProblemDescription) // 描述
    private EditText edtProblemDescription;
    @ViewInject(R.id.rvImgList) // 文件
    private RecyclerView rvImgList;

    private Activity mContext;
    private String fileUrlName;
    private PhotosListAdapter photosAdapter;
    private ArrayList<String> photosList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_complaint_feedback);
        x.view().inject(this);
        mContext = this;
        ScreenManagerUtil.pushActivity(this);

        txtTitle.setText("投诉及反馈");
        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        initFilePath();

        photosAdapter = new PhotosListAdapter(mContext, photosList);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvImgList.setLayoutManager(ms);
        rvImgList.setAdapter(photosAdapter);
    }

    /**
     * 初始化照片存储位置
     */
    private void initFilePath() {
        File imgFile = new File(ConstantsUtil.SAVE_PATH);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }
    }

    /**
     * 检查拍照权限
     */
    private void checkPhotosPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            requestAuthority(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, new PermissionListener() {
                @Override
                public void agree() {
                    takePictures();
                }

                @Override
                public void refuse(List<String> refusePermission) {
                    ToastUtil.showLong(mContext, "您已拒绝拍照权限!");
                }
            });
        } else {
            takePictures();
        }
    }

    /**
     * 拍照
     */
    private void takePictures() {
        new SelectPhotoWayDialog(mContext, new PromptListener() {
            @Override
            public void isChoice(boolean trueOrFalse) {
                if (trueOrFalse) {
                    cameraWay();
                } else {
                    albumWay();
                }
            }
        }).show();
    }

    /**
     * 调用相机拍照
     */
    private void cameraWay() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUrlName = System.currentTimeMillis() + ".png";
        Uri photoUri = FileProvider.getUriForFile(mContext, ProviderUtil.getFileProviderName(mContext), new File(ConstantsUtil.SAVE_PATH + fileUrlName));
        openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(openCameraIntent, 1);
    }

    /**
     * 调用相册
     */
    private void albumWay() {
        // 自由配置选项
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(true)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(ContextCompat.getColor(mContext, R.color.gray))
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(ContextCompat.getColor(mContext, R.color.main_check_bg))
                // 返回图标ResId
                .backResId(R.drawable.back_btn)
                // 标题
                .title("图片选择")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(ContextCompat.getColor(mContext, R.color.main_bg))
                // 裁剪大小。needCrop为true的时候配置
                //.cropSize(1, 1, 200, 200)
                .needCrop(false)
                // 第一个是否显示相机，默认true
                .needCamera(false)
                // 最大选择图片数量，默认9
                .maxNum(9)
                .build();

        // 跳转到图片选择器
        ISNav.getInstance().toListActivity(this, config, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    photosList.add(ConstantsUtil.SAVE_PATH + fileUrlName);
                    photosAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    List<String> pathList = data.getStringArrayListExtra("result");
                    for (String str : pathList) {
                        if (!fileIsExists(str)) {
                            pathList.remove(str);
                        }
                    }
                    if (pathList == null || pathList.size() == 0) {
                        ToastUtil.showShort(mContext, "选择照片不存在！");
                        return;
                    }
                    photosList.addAll(pathList);
                    photosAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param strFile
     * @return
     */
    private boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 提交
     */
    private void submit() {
        LoadingUtils.showLoading(mContext);
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (photosList != null && photosList.size() > 0) {
            // MediaType.parse() 里面是上传的文件类型。
            for (String sUrl : photosList) {
                File file = new File(sUrl);
                RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream; charset=utf-8"), file);
                String filename = file.getName();
                // 参数分别为， 请求key ，文件名称 ， RequestBody
                requestBody.addFormDataPart("fileName", filename, fileBody);
            }
        }

        requestBody.addFormDataPart("feedbackType", StrUtil.equals("投诉", txtQuestionType.getText().toString()) ? "0" : "1");
        if (llCauseOfComplaint.getVisibility() == View.VISIBLE) {
            requestBody.addFormDataPart("complaint", edtPeopleOfComplaint.getText().toString());
        }
        if (llCauseOfComplaint.getVisibility() == View.VISIBLE) {
            requestBody.addFormDataPart("reason", edtCauseOfComplaint.getText().toString());
        }
        requestBody.addFormDataPart("descr", edtProblemDescription.getText().toString());

        Request request = new Request.Builder()
                .url(ConstantsUtil.BASE_URL + ConstantsUtil.UPLOAD_FILES)
                .addHeader("token", (String) SpUtil.get(mContext, ConstantsUtil.TOKEN, ""))
                .post(requestBody.build())
                .build();

        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, "提交失败！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final ClearanceInspectionModel model = gson.fromJson(jsonData, ClearanceInspectionModel.class);
                    if (model.isSuccess()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ChildThreadUtil.toastMsgHidden(mContext, "提交成功！");
                                mContext.finish();
                            }
                        });
                    } else {
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    ChildThreadUtil.toastMsgHidden(mContext, getString(R.string.json_error));
                }
            }
        });
    }


    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft, R.id.txtQuestionType, R.id.imgBtnAdd, R.id.btnSubmit})
    private void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.imgBtnLeft:
                this.finish();
                break;
            // 问题类型
            case R.id.txtQuestionType:
                final List<String> option = new ArrayList<>();
                option.add("投诉");
                option.add("反馈");
                DateUtils.optionPicker(mContext, option, new IntListener() {
                    @Override
                    public void selectPoint(int point) {
                        if (point == 0) {
                            llPeopleOfComplaint.setVisibility(View.VISIBLE);
                            vPeopleOfComplaint.setVisibility(View.VISIBLE);
                            llCauseOfComplaint.setVisibility(View.VISIBLE);
                            vCauseOfComplaint.setVisibility(View.VISIBLE);
                        } else {
                            llPeopleOfComplaint.setVisibility(View.GONE);
                            vPeopleOfComplaint.setVisibility(View.GONE);
                            llCauseOfComplaint.setVisibility(View.GONE);
                            vCauseOfComplaint.setVisibility(View.GONE);
                        }
                        txtQuestionType.setText(option.get(point));
                    }
                });
                break;
            // 添加照片
            case R.id.imgBtnAdd:
                checkPhotosPermission();
                break;
            // 提交
            case R.id.btnSubmit:
                if (StrUtil.isEmpty(txtQuestionType.getText().toString())) {
                    ToastUtil.showShort(mContext, "请选择问题类型！");
                } else if (llCauseOfComplaint.getVisibility() == View.VISIBLE && StrUtil.isEmpty(edtPeopleOfComplaint.getText().toString())) {
                    ToastUtil.showShort(mContext, "请输入投诉对象！");
                } else if (llCauseOfComplaint.getVisibility() == View.VISIBLE && StrUtil.isEmpty(edtCauseOfComplaint.getText().toString())) {
                    ToastUtil.showShort(mContext, "请输入投诉原因！");
                } else {
                    submit();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
