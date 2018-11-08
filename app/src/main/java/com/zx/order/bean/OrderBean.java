package com.zx.order.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单
 * 作者：JHJ
 * 日期：2018/10/12 17:15
 * Q Q: 1320666709
 */
public class OrderBean {
    private String orderId;             // 主键ID
    private String otherId;             // 其它表主键ID
    private String otherIdName;         // 其它表主键名称
    private String customsId;           // 报关预约主键
    private String orderType;           // 类型
    private String handleState;         // 处理状态
    private List<String> orderList;     // 预约步骤
    private String collectFlag = "0";   // 是否收藏 0：未1：收藏
    private int evaluateLevel = 0;      // 评价等级
    private String evaluateContent;     // 评价内容
    private boolean isOpen = false;     // 是否展开

    public String getOrderId() {
        return orderId == null ? "" : orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOtherId() {
        return otherId == null ? "" : otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getOtherIdName() {
        return otherIdName == null ? "" : otherIdName;
    }

    public void setOtherIdName(String otherIdName) {
        this.otherIdName = otherIdName;
    }

    public String getCustomsId() {
        return customsId == null ? "" : customsId;
    }

    public void setCustomsId(String customsId) {
        this.customsId = customsId;
    }

    public String getOrderType() {
        return orderType == null ? "" : orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getHandleState() {
        return handleState == null ? "" : handleState;
    }

    public void setHandleState(String handleState) {
        this.handleState = handleState;
    }

    public List<String> getOrderList() {
        if (orderList == null) {
            return new ArrayList<>();
        }
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }

    public String getCollectFlag() {
        return collectFlag == null ? "" : collectFlag;
    }

    public void setCollectFlag(String collectFlag) {
        this.collectFlag = collectFlag;
    }

    public int getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(int evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getEvaluateContent() {
        return evaluateContent == null ? "" : evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
