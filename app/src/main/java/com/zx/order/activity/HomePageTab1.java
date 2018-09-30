package com.zx.order.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zx.order.R;
import com.zx.order.adapter.OrderListAdapter;
import com.zx.order.bean.LogisticsBean;
import com.zx.order.bean.OrderBean;
import com.zx.order.loader.GlideImageLoader;
import com.zx.order.utils.ChildThreadUtil;
import com.zx.order.utils.ConstantsUtil;
import com.zx.order.utils.JudgeNetworkIsAvailable;
import com.zx.order.utils.LoadingUtils;
import com.zx.order.utils.ToastUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 我的
 */
public class HomePageTab1 {
    private TabHolder holder;
    private Activity mContext;
    private int dataSize = 0, loadType = 1, pagePosition = 1;
    private List<OrderBean> orderList = new ArrayList<>();

    public HomePageTab1(Activity mContext, View layoutMe) {
        this.mContext = mContext;
        holder = new TabHolder();
        x.view().inject(holder, layoutMe);
    }

    /**
     * 赋值
     *
     * @param logisticsList
     */
    public void setDate(List<LogisticsBean> logisticsList) {
        //设置banner样式
        holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        holder.banner.setImageLoader(new GlideImageLoader());
        List<String> objList = new ArrayList<>();
        objList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536837723054&di=606251b4a7291ab59d2b2f1d91785a76&imgtype=0&src=http%3A%2F%2Fwww.dajinmagroup.com%2FimageRepository%2F1f0bd5a6-58a2-45c3-bc4f-fb7c3f1aee76.jpg");
        objList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536837810136&di=d29fa65d82ee7323d213683247787b14&imgtype=0&src=http%3A%2F%2Fwww.cscec2bne.com.cn%2Fpicup%2F201532694944.jpg");
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        holder.banner.setBannerTitles(strings);

        //设置banner动画效果
        holder.banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        holder.banner.isAutoPlay(true);
        //设置轮播时间文件对象
        holder.banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        holder.banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片集合
        holder.banner.setImages(objList);
        holder.banner.start();

        OrderBean bean = new OrderBean();
        bean.setTitle("集装箱号：ZCSU5846054");
        bean.setCommodityName("2650 M/CTNS FROZEN RIBBON FISH");
        bean.setOrderDate(DateUtil.parseDate("2018-05-20").getTime());
        bean.setToThePortDate(DateUtil.parseDate("2018-06-25").getTime());
        bean.setOrderNo("JXLU5842906");
        bean.setBillOfLadingNo("GOSUPAV6070307");
        bean.setTextLabel("未预约");
        bean.setStates("已到港");
        orderList.add(bean);
        OrderBean bean1 = new OrderBean();
        bean1.setTitle("集装箱号：JXLU5842906");
        bean1.setCommodityName("1 X 40 FCL 2600 CARTONS FROZEN");
        bean1.setOrderDate(DateUtil.parseDate("2018-04-12").getTime());
        bean1.setToThePortDate(DateUtil.parseDate("2018-05-15").getTime());
        bean1.setOrderNo("GOSUPAV6070322");
        bean1.setBillOfLadingNo("COFCO CORPORATION");
        bean1.setTextLabel("未预约");
        bean1.setStates("已到港");
        orderList.add(bean1);
        OrderBean bean2 = new OrderBean();
        bean2.setTitle("集装箱号：OOLU6250128");
        bean2.setCommodityName("FROZEN PAKISTAN PEELED UNDEVEI");
        bean2.setOrderDate(DateUtil.parseDate("2018-03-20").getTime());
        bean2.setToThePortDate(DateUtil.parseDate("2018-06-25").getTime());
        bean2.setOrderNo("TO ORDER");
        bean2.setBillOfLadingNo("GOSUPAV6070307");
        bean2.setTextLabel("未预约");
        bean2.setStates("已到港");
        orderList.add(bean2);
        OrderBean bean3 = new OrderBean();
        bean3.setTitle("集装箱号：ZCSU5846054");
        bean3.setCommodityName("2650 M/CTNS FROZEN RIBBON FISH");
        bean3.setOrderDate(DateUtil.parseDate("2018-05-20").getTime());
        bean3.setToThePortDate(DateUtil.parseDate("2018-06-25").getTime());
        bean3.setOrderNo("JXLU5842906");
        bean3.setBillOfLadingNo("GOSUPAV6070307");
        bean3.setTextLabel("未预约");
        bean3.setStates("已到港");
        orderList.add(bean3);
        OrderBean bean4 = new OrderBean();
        bean4.setTitle("集装箱号：JXLU5842906");
        bean4.setCommodityName("1 X 40 FCL 2600 CARTONS FROZEN");
        bean4.setOrderDate(DateUtil.parseDate("2018-04-12").getTime());
        bean4.setToThePortDate(DateUtil.parseDate("2018-05-15").getTime());
        bean4.setOrderNo("GOSUPAV6070322");
        bean4.setBillOfLadingNo("COFCO CORPORATION");
        bean4.setTextLabel("未预约");
        bean4.setStates("已到港");
        orderList.add(bean4);
        OrderBean bean5 = new OrderBean();
        bean5.setTitle("集装箱号：OOLU6250128");
        bean5.setCommodityName("FROZEN PAKISTAN PEELED UNDEVEI");
        bean5.setOrderDate(DateUtil.parseDate("2018-03-20").getTime());
        bean5.setToThePortDate(DateUtil.parseDate("2018-06-25").getTime());
        bean5.setOrderNo("TO ORDER");
        bean5.setBillOfLadingNo("GOSUPAV6070307");
        bean5.setTextLabel("未预约");
        bean5.setStates("已到港");
        orderList.add(bean5);

        // 设置主题颜色
        holder.refreshLayout.setPrimaryColorsId(R.color.main_bg, android.R.color.white);
        holder.refreshLayout.setFooterTriggerRate(1);
        holder.refreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        holder.refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        holder.refreshLayout.setEnableScrollContentWhenRefreshed(true);
        // 通过多功能监听接口实现 在第一次加载完成之后 自动刷新
        holder.refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadType = 1;
                if (orderList.size() < dataSize) {
                    pagePosition++;
                    if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                        //getData("", false);
                    } else {
                        ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
                    }
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(1000);
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadType = 2;
                pagePosition = 1;
                orderList.clear();
                if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
                    //getData("", false);
                } else {
                    ToastUtil.showShort(mContext, "没有更多数据了！");
                    refreshLayout.finishLoadMore(1000);
                }
            }
        });

        if (JudgeNetworkIsAvailable.isNetworkAvailable(mContext)) {
            //getData("", true);
        } else {
            ToastUtil.showShort(mContext, mContext.getString(R.string.not_network));
        }

        // 时间轴
        OrderListAdapter orderAdapter = new OrderListAdapter(orderList, 3);
        holder.rvMyOrderList.setLayoutManager(new GridLayoutManager(mContext, 1));
        holder.rvMyOrderList.setAdapter(orderAdapter);
    }

    /**
     * 获取工序列表
     *
     * @param searchContext
     */
    private void getData(final String searchContext, final boolean isLoading) {
        if (isLoading) {
            LoadingUtils.showLoading(mContext);
        }
        JSONObject obj = new JSONObject();
        obj.put("page", pagePosition);
        obj.put("limit", 10);
        if (!StrUtil.isEmpty(searchContext)) {
            obj.put("keyword", searchContext);
        }
        Request request = ChildThreadUtil.getRequest(mContext, "", obj.toString());
        ConstantsUtil.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                stopLoad();
                ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.server_exception));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string().toString();
                /*if (JSONUtil.isJson(jsonData)) {
                    Gson gson = new Gson();
                    final WorkingListModel model = gson.fromJson(jsonData, WorkingListModel.class);
                    if (model.isSuccess()) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stopLoad();
                                LoadingUtils.hideLoading();
                            }
                        });
                    } else {
                        stopLoad();
                        ChildThreadUtil.checkTokenHidden(mContext, model.getMessage(), model.getCode());
                    }
                } else {
                    stopLoad();
                    ChildThreadUtil.toastMsgHidden(mContext, mContext.getString(R.string.json_error));
                }*/
            }
        });
    }

    /**
     * 停止加载
     */
    private void stopLoad() {
        if (loadType == 1) {
            holder.refreshLayout.finishLoadMore(1000);
        } else if (loadType == 2) {
            holder.refreshLayout.finishRefresh(1000);
        }
    }

    /**
     * 容纳器
     */
    private class TabHolder {
        @ViewInject(R.id.banner)
        private Banner banner;
        @ViewInject(R.id.refreshLayout)
        private RefreshLayout refreshLayout;
        @ViewInject(R.id.rvMyOrderList)
        private RecyclerView rvMyOrderList;
    }
}
