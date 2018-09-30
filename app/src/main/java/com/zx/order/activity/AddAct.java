package com.zx.order.activity;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.order.R;
import com.zx.order.adapter.AddAdapter;
import com.zx.order.bean.MainPageBean;
import com.zx.order.model.MainPageModel;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的
 */
public class AddAct {
    private AddHolder holder;
    private Activity mContext;

    public AddAct(Activity mContext, View layout) {
        this.mContext = mContext;
        holder = new AddHolder();
        x.view().inject(holder, layout);
    }

    /**
     * 赋值
     */
    public void setDate() {
        // 清除所有标签
        holder.llMain.removeAllViews();
        MainPageModel model = new MainPageModel();
        List<MainPageBean> mainPageBeanList = new ArrayList<>();
        MainPageBean bean1 = new MainPageBean();
        bean1.setSmallModuleIcon("http://static.apih5.cn/icon/concealment_project.png");
        bean1.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean1);
        MainPageBean bean2 = new MainPageBean();
        bean2.setSmallModuleIcon("http://static.apih5.cn/icon/quality_testing.png");
        bean2.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean2);
        MainPageBean bean3 = new MainPageBean();
        bean3.setSmallModuleIcon("http://static.apih5.cn/icon/hidden_trouble_investigation.png");
        bean3.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean3);
        MainPageBean bean4 = new MainPageBean();
        bean4.setSmallModuleIcon("http://static.apih5.cn/icon/audit_management.png");
        bean4.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean4);
        MainPageBean bean5 = new MainPageBean();
        bean5.setSmallModuleIcon("http://static.apih5.cn/icon/data_report.png");
        bean5.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean5);
        MainPageBean bean6 = new MainPageBean();
        bean6.setSmallModuleIcon("http://static.apih5.cn/icon/qr_code.png");
        bean6.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean6);
        MainPageBean bean7 = new MainPageBean();
        bean7.setSmallModuleIcon("http://static.apih5.cn/icon/bid_management.png");
        bean7.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean7);
        MainPageBean bean8 = new MainPageBean();
        bean8.setSmallModuleIcon("http://static.apih5.cn/icon/lao_wu.png");
        bean8.setSmallModuleTitle("预留模块");
        mainPageBeanList.add(bean8);
        model.setHomeSmallModuleList(mainPageBeanList);
        setAuditModuleData(model);
    }

    /**
     * 审核模块
     *
     * @param model
     */
    private void setAuditModuleData(MainPageModel model) {
        if (model == null || model.getHomeSmallModuleList() == null || model.getHomeSmallModuleList().size() == 0) {
            return;
        }

        /*// 1、动态添加布局文件
        LinearLayout llTitle = new LinearLayout(mContext);
        llTitle.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams llLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(35));
        // (1) 添加竖线
        ImageView ivBg = new ImageView(mContext);
        LinearLayout.LayoutParams ivLp = new LinearLayout.LayoutParams(DensityUtil.dip2px(4), LinearLayout.LayoutParams.MATCH_PARENT);
        ivLp.setMargins(DensityUtil.dip2px(15), DensityUtil.dip2px(10), DensityUtil.dip2px(10), DensityUtil.dip2px(10));
        ivBg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.main_bg));
        llTitle.addView(ivBg, ivLp);

        // (2) 添加标题
        TextView txtTitle = new TextView(mContext);
        LinearLayout.LayoutParams txtLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        txtTitle.setGravity(Gravity.CENTER);
        txtTitle.setText(model.getLargeModuleTitle());
        txtTitle.setTextSize(14);
        txtTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        llTitle.addView(txtTitle, txtLp);
        holder.llMain.addView(llTitle, llLp);*/

        // (3) 添加RecyclerView
        RecyclerView rvAudit = new RecyclerView(mContext);
        rvAudit.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        rvAudit.setScrollbarFadingEnabled(true);
        rvAudit.setPadding(0, 0, 0, DensityUtil.dip2px(10));
        RecyclerView.LayoutParams rvLp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        holder.llMain.addView(rvAudit, rvLp);

        // (4) 添加分割线
        /*View view = new View(mContext);
        view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_white));
        LinearLayout.LayoutParams vLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(5));
        holder.llMain.addView(view, vLp);*/

        // 2、设置数据
        AddAdapter addAdapter = new AddAdapter(mContext, model.getHomeSmallModuleList());
        rvAudit.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvAudit.setAdapter(addAdapter);
    }

    /**
     * 容纳器
     */
    private class AddHolder {
        @ViewInject(R.id.llMain)
        private LinearLayout llMain;
    }
}
