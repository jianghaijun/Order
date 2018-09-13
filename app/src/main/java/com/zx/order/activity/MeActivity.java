package com.zx.order.activity;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zx.order.R;
import com.zx.order.adapter.BottomInfoAdapter;
import com.zx.order.adapter.TimeLineAdapter;
import com.zx.order.bean.BottomInfoBean;
import com.zx.order.bean.LogisticsBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的
 */
public class MeActivity {
    private MeHolder holder;
    private Activity mContext;

    public MeActivity(Activity mContext, View layoutMe) {
        this.mContext = mContext;
        holder = new MeHolder();
        x.view().inject(holder, layoutMe);
    }

    /**
     * 赋值
     * @param logisticsList
     */
    public void setDate(List<LogisticsBean> logisticsList) {
        if (logisticsList == null) {
            logisticsList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                LogisticsBean mainBean = new LogisticsBean();
                mainBean.setMainTitle("标题" + i);
                List<LogisticsBean> contentInfo = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    LogisticsBean contentBean = new LogisticsBean();
                    contentBean.setContentTitle("附标题" + j);
                    List<LogisticsBean> content = new ArrayList<>();
                    for (int m = 0; m < 3; m++) {
                        LogisticsBean bean = new LogisticsBean();
                        bean.setContentText("子标题&柜" + m);
                        bean.setContentNum(i + "");
                        content.add(bean);
                    }
                    contentBean.setContent(content);
                    contentInfo.add(contentBean);
                }
                mainBean.setContentInfo(contentInfo);
                logisticsList.add(mainBean);
            }
        }

        // 时间轴
        TimeLineAdapter timeLineAdapter = new TimeLineAdapter(logisticsList);
        holder.rvTimeMarker.setLayoutManager(new GridLayoutManager(mContext, 1));
        holder.rvTimeMarker.setAdapter(timeLineAdapter);

        // 物流信息
        List<BottomInfoBean> bottomList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BottomInfoBean bean = new BottomInfoBean();
            bean.setTitle("物流" + i);
            bean.setImgUrl("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=70079684,1216259765&fm=26&gp=0.jpg");
            bean.setCount((i+1) + "");
            bottomList.add(bean);
        }
        BottomInfoAdapter bottomAdapter = new BottomInfoAdapter(bottomList);
        holder.rvOrderInfo.setLayoutManager(new GridLayoutManager(mContext, 5));
        holder.rvOrderInfo.setAdapter(bottomAdapter);
    }

    /**
     * 容纳器
     */
    private class MeHolder {
        @ViewInject(R.id.rvTimeMarker)
        private RecyclerView rvTimeMarker;
        @ViewInject(R.id.rvOrderInfo)
        private RecyclerView rvOrderInfo;
    }
}
