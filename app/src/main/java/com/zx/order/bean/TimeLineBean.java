package com.zx.order.bean;
/**
 * 时间轴
 * 作者：JHJ
 * 日期：2018/10/15 16:58
 * Q Q: 1320666709
 */
public class TimeLineBean {
    private String status;
    private String title;
    private long actionTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }
}
