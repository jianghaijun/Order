package com.zx.order.bean;

import java.util.List;

public class LogisticsBean {
    private String mainTitle;
    private String LogisticTitle;
    private List<LogisticsBean> contentInfo;
    private String imgUrl;
    private String contentTitle;
    private List<LogisticsBean> content;
    private String contentText;
    private String contentNum;

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getLogisticTitle() {
        return LogisticTitle;
    }

    public void setLogisticTitle(String logisticTitle) {
        LogisticTitle = logisticTitle;
    }

    public List<LogisticsBean> getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(List<LogisticsBean> contentInfo) {
        this.contentInfo = contentInfo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public List<LogisticsBean> getContent() {
        return content;
    }

    public void setContent(List<LogisticsBean> content) {
        this.content = content;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentNum() {
        return contentNum;
    }

    public void setContentNum(String contentNum) {
        this.contentNum = contentNum;
    }
}
