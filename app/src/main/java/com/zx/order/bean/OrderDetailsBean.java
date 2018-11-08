package com.zx.order.bean;

/**
 * 订单详情
 * 作者：JHJ
 * 日期：2018/11/5 10:06
 * Q Q: 1320666709
 */
public class OrderDetailsBean {
    private String orderType;                               // 类型

    // 报关预约
    private OrderDetailsBean customsDeclaration;
    private String customsId;                               // 主键ID
    private long acceptanceTime;                            // 受理时间
    private long handleTime;                                // 处理时间
    private long finishTime;                                // 完成时间

    // 看货预约---同报关预约
    private OrderDetailsBean lookGoods;
    private String lookGoodsId;                             // 主键ID

    // 对扒预约
    private OrderDetailsBean steak;
    private String steakId;                                 // 主键ID
    private long originalCarEntryTime;                      // 原车辆进场时间
    private long steakCarEntryTime;                         // 对扒车辆进场时间
    private long steakTime;                                 // 对扒时间
    private long steakFinishTime;                           // 对扒完成时间
    private long carDepartureTime;                          // 离台时间
    private long releaseTime;                               // 发行时间

    // 入库预约---同报关预约
    private OrderDetailsBean inStorage;
    private String inStorageId;                             // 主键ID

    // 取样委托
    private OrderDetailsBean sample;
    private String sampleId;                                // 主键ID
    private long carEntryTime;                              // 车辆进场时间
    private long carPlatformTime;                           // 车辆靠台时间
    private long loadingTime;                               // 装车时间
    private long loadingFinishTime;                         // 装车完成时间

    // 出库预约---同取样预约
    private OrderDetailsBean outStorage;
    private String outStorageId;                            // 主键ID

    // 配送预约
    private OrderDetailsBean delivery;
    private String deliveryId;                              // 主键ID
    private long carExitTime;                               // 车辆出场时间
    private long arriveTime;                                // 到达时间
    private long signInTime;                                // 签收时间

    // 拆提预约
    private OrderDetailsBean toAsk;
    private String toAskId;                                 // 主键ID
    private long dropBoxFinishTime;                         // 落箱完成时间
    private long unBoxTime;                                 // 拆箱时间
    private long returnBoxTime;                             // 返箱时间

    // 熏蒸预约
    private OrderDetailsBean fumigate;
    private String fumigateId;                              // 主键ID
    private long beginFumigateTime;                         // 开始熏蒸时间
    private long endFumigateTime;                           // 结束熏蒸时间
    private long shippingTime;                              // 发运时间

    // 疏港委托---同报关预约
    private OrderDetailsBean entrustHarbour;
    private String entrustHarbourId;                        // 主键ID

    // 查验委托---同报关预约
    private OrderDetailsBean check;
    private String checkId;                                 // 主键ID

    // 验箱预约---同报关预约
    private OrderDetailsBean tryoff;
    private String tryoffId;                                // 主键ID

    // 返箱预约---同报关预约
    private OrderDetailsBean backBox;
    private String backBoxId;                               // 主键ID

    // 共通
    private int evaluateLevel;                              // 评价等级
    private String evaluateContent;                         // 评价内容
    private String handleState;                             // 处理状态

    public String getOrderType() {
        return orderType == null ? "" : orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public OrderDetailsBean getCustomsDeclaration() {
        return customsDeclaration;
    }

    public void setCustomsDeclaration(OrderDetailsBean customsDeclaration) {
        this.customsDeclaration = customsDeclaration;
    }

    public String getCustomsId() {
        return customsId == null ? "" : customsId;
    }

    public void setCustomsId(String customsId) {
        this.customsId = customsId;
    }

    public long getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(long acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public long getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(long handleTime) {
        this.handleTime = handleTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public OrderDetailsBean getLookGoods() {
        return lookGoods;
    }

    public void setLookGoods(OrderDetailsBean lookGoods) {
        this.lookGoods = lookGoods;
    }

    public String getLookGoodsId() {
        return lookGoodsId == null ? "" : lookGoodsId;
    }

    public void setLookGoodsId(String lookGoodsId) {
        this.lookGoodsId = lookGoodsId;
    }

    public OrderDetailsBean getSteak() {
        return steak;
    }

    public void setSteak(OrderDetailsBean steak) {
        this.steak = steak;
    }

    public String getSteakId() {
        return steakId == null ? "" : steakId;
    }

    public void setSteakId(String steakId) {
        this.steakId = steakId;
    }

    public long getOriginalCarEntryTime() {
        return originalCarEntryTime;
    }

    public void setOriginalCarEntryTime(long originalCarEntryTime) {
        this.originalCarEntryTime = originalCarEntryTime;
    }

    public long getSteakCarEntryTime() {
        return steakCarEntryTime;
    }

    public void setSteakCarEntryTime(long steakCarEntryTime) {
        this.steakCarEntryTime = steakCarEntryTime;
    }

    public long getSteakTime() {
        return steakTime;
    }

    public void setSteakTime(long steakTime) {
        this.steakTime = steakTime;
    }

    public long getSteakFinishTime() {
        return steakFinishTime;
    }

    public void setSteakFinishTime(long steakFinishTime) {
        this.steakFinishTime = steakFinishTime;
    }

    public long getCarDepartureTime() {
        return carDepartureTime;
    }

    public void setCarDepartureTime(long carDepartureTime) {
        this.carDepartureTime = carDepartureTime;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public OrderDetailsBean getInStorage() {
        return inStorage;
    }

    public void setInStorage(OrderDetailsBean inStorage) {
        this.inStorage = inStorage;
    }

    public String getInStorageId() {
        return inStorageId == null ? "" : inStorageId;
    }

    public void setInStorageId(String inStorageId) {
        this.inStorageId = inStorageId;
    }

    public OrderDetailsBean getSample() {
        return sample;
    }

    public void setSample(OrderDetailsBean sample) {
        this.sample = sample;
    }

    public String getSampleId() {
        return sampleId == null ? "" : sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public long getCarEntryTime() {
        return carEntryTime;
    }

    public void setCarEntryTime(long carEntryTime) {
        this.carEntryTime = carEntryTime;
    }

    public long getCarPlatformTime() {
        return carPlatformTime;
    }

    public void setCarPlatformTime(long carPlatformTime) {
        this.carPlatformTime = carPlatformTime;
    }

    public long getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(long loadingTime) {
        this.loadingTime = loadingTime;
    }

    public long getLoadingFinishTime() {
        return loadingFinishTime;
    }

    public void setLoadingFinishTime(long loadingFinishTime) {
        this.loadingFinishTime = loadingFinishTime;
    }

    public OrderDetailsBean getOutStorage() {
        return outStorage;
    }

    public void setOutStorage(OrderDetailsBean outStorage) {
        this.outStorage = outStorage;
    }

    public String getOutStorageId() {
        return outStorageId == null ? "" : outStorageId;
    }

    public void setOutStorageId(String outStorageId) {
        this.outStorageId = outStorageId;
    }

    public OrderDetailsBean getDelivery() {
        return delivery;
    }

    public void setDelivery(OrderDetailsBean delivery) {
        this.delivery = delivery;
    }

    public String getDeliveryId() {
        return deliveryId == null ? "" : deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public long getCarExitTime() {
        return carExitTime;
    }

    public void setCarExitTime(long carExitTime) {
        this.carExitTime = carExitTime;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public long getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(long signInTime) {
        this.signInTime = signInTime;
    }

    public OrderDetailsBean getToAsk() {
        return toAsk;
    }

    public void setToAsk(OrderDetailsBean toAsk) {
        this.toAsk = toAsk;
    }

    public String getToAskId() {
        return toAskId == null ? "" : toAskId;
    }

    public void setToAskId(String toAskId) {
        this.toAskId = toAskId;
    }

    public long getDropBoxFinishTime() {
        return dropBoxFinishTime;
    }

    public void setDropBoxFinishTime(long dropBoxFinishTime) {
        this.dropBoxFinishTime = dropBoxFinishTime;
    }

    public long getUnBoxTime() {
        return unBoxTime;
    }

    public void setUnBoxTime(long unBoxTime) {
        this.unBoxTime = unBoxTime;
    }

    public long getReturnBoxTime() {
        return returnBoxTime;
    }

    public void setReturnBoxTime(long returnBoxTime) {
        this.returnBoxTime = returnBoxTime;
    }

    public OrderDetailsBean getFumigate() {
        return fumigate;
    }

    public void setFumigate(OrderDetailsBean fumigate) {
        this.fumigate = fumigate;
    }

    public String getFumigateId() {
        return fumigateId == null ? "" : fumigateId;
    }

    public void setFumigateId(String fumigateId) {
        this.fumigateId = fumigateId;
    }

    public long getBeginFumigateTime() {
        return beginFumigateTime;
    }

    public void setBeginFumigateTime(long beginFumigateTime) {
        this.beginFumigateTime = beginFumigateTime;
    }

    public long getEndFumigateTime() {
        return endFumigateTime;
    }

    public void setEndFumigateTime(long endFumigateTime) {
        this.endFumigateTime = endFumigateTime;
    }

    public long getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(long shippingTime) {
        this.shippingTime = shippingTime;
    }

    public OrderDetailsBean getEntrustHarbour() {
        return entrustHarbour;
    }

    public void setEntrustHarbour(OrderDetailsBean entrustHarbour) {
        this.entrustHarbour = entrustHarbour;
    }

    public String getEntrustHarbourId() {
        return entrustHarbourId == null ? "" : entrustHarbourId;
    }

    public void setEntrustHarbourId(String entrustHarbourId) {
        this.entrustHarbourId = entrustHarbourId;
    }

    public OrderDetailsBean getCheck() {
        return check;
    }

    public void setCheck(OrderDetailsBean check) {
        this.check = check;
    }

    public String getCheckId() {
        return checkId == null ? "" : checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public OrderDetailsBean getTryoff() {
        return tryoff;
    }

    public void setTryoff(OrderDetailsBean tryoff) {
        this.tryoff = tryoff;
    }

    public String getTryoffId() {
        return tryoffId == null ? "" : tryoffId;
    }

    public void setTryoffId(String tryoffId) {
        this.tryoffId = tryoffId;
    }

    public OrderDetailsBean getBackBox() {
        return backBox;
    }

    public void setBackBox(OrderDetailsBean backBox) {
        this.backBox = backBox;
    }

    public String getBackBoxId() {
        return backBoxId == null ? "" : backBoxId;
    }

    public void setBackBoxId(String backBoxId) {
        this.backBoxId = backBoxId;
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

    public String getHandleState() {
        return handleState == null ? "" : handleState;
    }

    public void setHandleState(String handleState) {
        this.handleState = handleState;
    }
}
