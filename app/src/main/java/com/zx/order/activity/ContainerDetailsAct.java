package com.zx.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vivian.timelineitemdecoration.itemdecoration.SpanIndexListener;
import com.zx.order.R;
import com.zx.order.adapter.WaterfallFlowTimeLineAdapter;
import com.zx.order.base.BaseActivity;
import com.zx.order.base.VoyageFlowBean;
import com.zx.order.bean.TimeLineBean;
import com.zx.order.custom.DotItemDecoration;
import com.zx.order.model.VoyageFlowModel;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.DateUtils;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ScreenManagerUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 航班航次流程
 * 作者：JHJ
 * 日期：2018/10/30 15:30
 * Q Q: 1320666709
 */
public class ContainerDetailsAct extends BaseActivity {
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.rvTimeLineWaterfallFlow)
    private RecyclerView rvTimeLineWaterfallFlow;

    private Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_container_details);
        x.view().inject(this);

        ScreenManagerUtil.pushActivity(this);
        mContext = this;

        txtTitle.setText("集装箱信息流程图");
        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_btn));

        getData();
    }

    /**
     * 获取流程数据
     */
    private void getData() {
        LoadingUtils.showLoading(mContext);
        JSONObject obj = new JSONObject();
        obj.put("voyageId", getIntent().getStringExtra("voyageId"));
        Request request = ChildThreadUtil.getRequest(mContext, ConstantsUtil.YD_VOYAGE_FLOW_BY_ID, obj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ChildThreadUtil.toastMsgHidden(mContext, getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final VoyageFlowModel model = gson.fromJson(jsonData, VoyageFlowModel.class);
                    if (model.isSuccess()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setDate(model.getData());
                                LoadingUtils.hideLoading();
                            }
                        });
                    } else {
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.json_error));
                }
            }
        });
    }

    /**
     * 赋值
     *
     * @param dateBean 数据bean
     */
    private void setDate(VoyageFlowBean dateBean) {
        if (dateBean == null) {
            dateBean = new VoyageFlowBean();
        }

        List<String[]> strList = new ArrayList<>();
        strList.add(new String[]{"集装箱号：", dateBean.getContaNo()});
        strList.add(new String[]{"预计到港时间：", DateUtils.setDataToStr2(dateBean.getDecArriveTime())});
        strList.add(new String[]{"实际到港时间：", DateUtils.setDataToStr2(dateBean.getActualArriveTime())});
        strList.add(new String[]{"卸船时间：", DateUtils.setDataToStr2(dateBean.getDischargeDate())});
        strList.add(new String[]{"进场时间：", DateUtils.setDataToStr2(dateBean.getInDate())});
        strList.add(new String[]{"放行时间：", DateUtils.setDataToStr2(dateBean.getOutDate())});
        strList.add(new String[]{"提箱时间：", DateUtils.setDataToStr2(dateBean.getArriveDate())});
        strList.add(new String[]{"到仓库时间：", DateUtils.setDataToStr2(dateBean.getStorageDate())});
        strList.add(new String[]{"还箱时间：", DateUtils.setDataToStr2(dateBean.getRetDate())});

        List<TimeLineBean> timeLineBeans = new ArrayList<>();
        boolean isZero = true;
        for (String[] str : strList) {
            TimeLineBean bean = new TimeLineBean();
            bean.setStatus(str[0]);
            if (isZero) {
                bean.setTitle(str[1]);
                isZero = false;
            } else {
                bean.setActionTime(DateUtil.parse(str[1], "yyyy-MM-dd hh:mm").getTime());
            }
            timeLineBeans.add(bean);
        }
        initView(timeLineBeans);
    }

    /**
     * 设置显示数据
     *
     * @param timeLineBeans 数据集合
     */
    private void initView(List<TimeLineBean> timeLineBeans) {
        rvTimeLineWaterfallFlow.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        DotItemDecoration mItemDecoration = new DotItemDecoration
                .Builder(mContext)
                .setOrientation(DotItemDecoration.VERTICAL)//if you want a horizontal item decoration,remember to set horizontal orientation to your LayoutManager
                .setItemStyle(DotItemDecoration.STYLE_DRAW)
                .setTopDistance(30)//dp
                .setItemInterVal(10)//dp
                .setDotColor(ContextCompat.getColor(mContext, R.color.main_bg))
                .setDotRadius(5)//dp
                .setDotPaddingTop(0)
                .setDotInItemOrientationCenter(true)//set true if you want the dot align center
                .setLineColor(ContextCompat.getColor(mContext, R.color.main_bg))
                .setLineWidth(3)//dp
                .setEndText("END")
                .setBottomDistance(40)
                .create();
        mItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                view.setBackgroundResource(spanIndex == 0 ? R.drawable.left : R.drawable.right);
            }
        });
        rvTimeLineWaterfallFlow.addItemDecoration(mItemDecoration);
        WaterfallFlowTimeLineAdapter mAdapter = new WaterfallFlowTimeLineAdapter(mContext, timeLineBeans);
        rvTimeLineWaterfallFlow.setAdapter(mAdapter);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.imgBtnLeft})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnLeft:
                this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManagerUtil.pushActivity(this);
    }
}
