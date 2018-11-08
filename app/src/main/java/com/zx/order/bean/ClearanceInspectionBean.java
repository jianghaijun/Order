package com.zx.order.bean;

/**
 * 通关状态 查验
 * 作者：JHJ
 * 日期：2018/11/1 11:27
 * Q Q: 1320666709
 */
public class ClearanceInspectionBean {
    private long entrustingTheHarbourTime;  // 疏港委托时间
    private long shuntTriggerTime;          // 分流触发时间
    private long reviewPassThroughTime;     // 审核通关时间
    private long changeListTime;            // 换单时间
    private long declareTime;               // 申报时间
    private long customsReleaeTime;         // 海关放行时间
    private long quarantineReleaseTime;     // 检疫放行时间
    private String checkState;              // 查验状态
    private String isSampling;              // 是否检疫取样
    private long checkAppointmentTime;      // 查验预约时间
    private long platformTime;              // 靠台时间
    private long openingTime;               // 开箱时间
    private long endOfInspectionTime;       // 查验结束时间
    private long backLoadingTime;           // 回装时间
    private long departureTime;             // 离台时间
    private String imageUrl;

    public long getEntrustingTheHarbourTime() {
        return entrustingTheHarbourTime;
    }

    public void setEntrustingTheHarbourTime(long entrustingTheHarbourTime) {
        this.entrustingTheHarbourTime = entrustingTheHarbourTime;
    }

    public long getShuntTriggerTime() {
        return shuntTriggerTime;
    }

    public void setShuntTriggerTime(long shuntTriggerTime) {
        this.shuntTriggerTime = shuntTriggerTime;
    }

    public long getReviewPassThroughTime() {
        return reviewPassThroughTime;
    }

    public void setReviewPassThroughTime(long reviewPassThroughTime) {
        this.reviewPassThroughTime = reviewPassThroughTime;
    }

    public long getChangeListTime() {
        return changeListTime;
    }

    public void setChangeListTime(long changeListTime) {
        this.changeListTime = changeListTime;
    }

    public long getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(long declareTime) {
        this.declareTime = declareTime;
    }

    public long getCustomsReleaeTime() {
        return customsReleaeTime;
    }

    public void setCustomsReleaeTime(long customsReleaeTime) {
        this.customsReleaeTime = customsReleaeTime;
    }

    public long getQuarantineReleaseTime() {
        return quarantineReleaseTime;
    }

    public void setQuarantineReleaseTime(long quarantineReleaseTime) {
        this.quarantineReleaseTime = quarantineReleaseTime;
    }

    public String getCheckState() {
        return checkState == null ? "" : checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getIsSampling() {
        return isSampling == null ? "" : isSampling;
    }

    public void setIsSampling(String isSampling) {
        this.isSampling = isSampling;
    }

    public long getCheckAppointmentTime() {
        return checkAppointmentTime;
    }

    public void setCheckAppointmentTime(long checkAppointmentTime) {
        this.checkAppointmentTime = checkAppointmentTime;
    }

    public long getPlatformTime() {
        return platformTime;
    }

    public void setPlatformTime(long platformTime) {
        this.platformTime = platformTime;
    }

    public long getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(long openingTime) {
        this.openingTime = openingTime;
    }

    public long getEndOfInspectionTime() {
        return endOfInspectionTime;
    }

    public void setEndOfInspectionTime(long endOfInspectionTime) {
        this.endOfInspectionTime = endOfInspectionTime;
    }

    public long getBackLoadingTime() {
        return backLoadingTime;
    }

    public void setBackLoadingTime(long backLoadingTime) {
        this.backLoadingTime = backLoadingTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
