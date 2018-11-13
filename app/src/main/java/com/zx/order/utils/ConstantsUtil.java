package com.zx.order.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * 常量类
 * 作者：JHJ
 * 日期：2018/10/30 14:32
 * Q Q: 1320666709
 */
public class ConstantsUtil {
    // 基础路径
    public static final String BASE_URL = "http://192.168.1.223:8080/web";
    // ACCOUNT_ID
    public static String ACCOUNT_ID = "zj_qyh_woa_id";

    // 参数格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    // OkHttpClient
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30000L, TimeUnit.MILLISECONDS)
            .readTimeout(30000L, TimeUnit.MILLISECONDS)
            .build();
    // 刷新等待时间
    public static final int REFRESH_WAITING_TIME = 500;

    // token
    public static String TOKEN = "TOKEN";
    // 是否登录成功
    public static final String IS_LOGIN_SUCCESSFUL = "IS_LOGIN_SUCCESSFUL";
    // 用户名
    public static final String USER_NAME = "USER_NAME";
    // 用户ID
    public static final String USER_ID = "USER_ID";
    // 用户头像
    public static final String USER_HEAD_URL = "USER_HEAD_URL";
    // 查验委托提交数据
    public static final String INSPECTION_COMMISSION = "INSPECTION_COMMISSION";

    // 登录
    public static final String LOGIN = "/user/login";
    // 上传头像
    public static final String SUBMIT_HEAD = "/updateHead";
    // 修改密码
    public static final String UPDATE_PASSWORD = "/user/updateUserPwd";
    // 获取航次列表
    public static final String YD_VOYAGE_LIST = "/getZzYdVoyageList";
    // 获取关注信息列表
    public static final String COLLECT_LIST = "/getZzYdCollectList";
    // 获取流程数据
    public static final String YD_VOYAGE_FLOW_BY_ID = "/getZzYdVoyageFlowById";
    // 获取提单列表
    public static final String YD_CARGO_BILL_LIST = "/getZzYdCargoBillList";
    // 通关状态
    public static final String CLEARANCE_STATUS = "/getZzYdCustomsStateDetailBYCargoBillId";
    // 查验
    public static final String INSPECTION = "/getZzYdInspectionDetailByCargoBillId";
    // 箱状态
    public static final String BOX_STATUS = "/getZzYdCntrList";
    // 冻品、水果列表
    public static final String BILL_LIST = "/getZzYdCargoBillList";
    // 冻品、水果详情
    public static final String CNTR_LIST = "/getZzYdCntrList";
    // 疏港委托列表
    public static final String ENTRUST_HARBOUR_LIST = "/getZzYdEntrustHarbourList";
    // 疏港委托预约
    public static final String ENTRUST_HARBOUR = "/updateZzYdEntrustHarbour";
    // 查验委托下一步
    public static final String CNTR_DEATAIL_LIST = "/getZzYdCntrDeatailListByVoyageId";
    // 查验委托下一步
    public static final String CNTR_AND_ADD_ORDER = "/batchUpdateZzYdCntrAndAddOrder";
    // 提交详情
    public static final String UPDATE_CNTR = "/updateZzYdCntr";
    // 订单列表
    public static final String ORDER_LIST = "/getZzYdOrderList";
    // 添加单项预约
    public static final String ADD_CNTR_FOR_SINGLE = "/updateZzYdCntrForSingle";
    // 收藏
    public static final String COLLECT = "/addZzYdCollect";
    // 订单详情
    public static final String ORDER_DETAIL = "/getZzYdOrderDetail";
    // 报关预约
    public static final String CUSTOMS_DECLARATION = "/updateZzYdCustomsDeclaration";
    // 评价订单详情
    public static final String DEAL_ORDER_DETAIL = "/dealZzYdOrderDetail";
    // 反馈新增并上传附件
    public static final String UPLOAD_FILES = "/addZzYdFeedbackAndUploadFiles";

    // 文件存储路径
    public static final String SAVE_PATH = "/mnt/sdcard/zxOrder/";
    // 下载apk文件名称
    public static final String APK_NAME = "zxOrder.apk";
}
