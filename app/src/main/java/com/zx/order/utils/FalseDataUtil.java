package com.zx.order.utils;

import com.zx.order.R;
import com.zx.order.bean.BoxStatusBean;
import com.zx.order.bean.ClearanceBean;
import com.zx.order.bean.EntrustingTheHarbourBean;
import com.zx.order.bean.FlightVoyageBean;
import com.zx.order.bean.IndividualBean;
import com.zx.order.bean.InspectionCommissionBean;
import com.zx.order.bean.OrderBean;
import com.zx.order.bean.ReservationBean;
import com.zx.order.bean.StatusBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 假数据
 * 作者：JHJ
 * 日期：2018/10/9 16:34
 * Q Q: 1320666709
 */
public class FalseDataUtil {

    /**
     * 首页通关状态信息
     *
     * @return
     */
    public static List<ClearanceBean> getHomePageTab1Data() {
        /*List<ClearanceBean> dataList = new ArrayList<>();

        ClearanceBean bean = new ClearanceBean();
        bean.setShipNameContext("ZCSU5846054");
        bean.setEstimatedBerthingDate(DateUtil.parseDate("2018-05-20").getTime());
        bean.setActualBerthingDate(DateUtil.parseDate("2018-06-25").getTime());
        bean.setPortOfDestination("大连");
        bean.setBoxNum("15");
        dataList.add(bean);

        ClearanceBean bean1 = new ClearanceBean();
        bean1.setShipNameContext("JXLU5842906");
        bean1.setEstimatedBerthingDate(DateUtil.parseDate("2018-04-12").getTime());
        bean1.setActualBerthingDate(DateUtil.parseDate("2018-05-15").getTime());
        bean1.setPortOfDestination("上海");
        bean1.setBoxNum("20");
        dataList.add(bean1);

        ClearanceBean bean2 = new ClearanceBean();
        bean2.setShipNameContext("OOLU6250128");
        bean2.setEstimatedBerthingDate(DateUtil.parseDate("2018-03-20").getTime());
        bean2.setActualBerthingDate(DateUtil.parseDate("2018-06-25").getTime());
        bean2.setPortOfDestination("武汉");
        bean2.setBoxNum("65");
        dataList.add(bean2);

        ClearanceBean bean3 = new ClearanceBean();
        bean3.setShipNameContext("ZCSU5846054");
        bean3.setEstimatedBerthingDate(DateUtil.parseDate("2018-05-20").getTime());
        bean3.setActualBerthingDate(DateUtil.parseDate("2018-06-25").getTime());
        bean3.setPortOfDestination("天津");
        bean3.setBoxNum("200");
        dataList.add(bean3);

        ClearanceBean bean4 = new ClearanceBean();
        bean4.setShipNameContext("JXLU5842906");
        bean4.setEstimatedBerthingDate(DateUtil.parseDate("2018-04-12").getTime());
        bean4.setActualBerthingDate(DateUtil.parseDate("2018-05-15").getTime());
        bean4.setPortOfDestination("深圳");
        bean4.setBoxNum("450");
        dataList.add(bean4);

        ClearanceBean bean5 = new ClearanceBean();
        bean5.setShipNameContext("OOLU6250128");
        bean5.setEstimatedBerthingDate(DateUtil.parseDate("2018-03-20").getTime());
        bean5.setActualBerthingDate(DateUtil.parseDate("2018-06-25").getTime());
        bean5.setPortOfDestination("广州");
        bean5.setBoxNum("750");
        dataList.add(bean5);*/

        return null;
    }

    /**
     * 获取航班航次数据
     *
     * @return
     */
    public static List<FlightVoyageBean> getFlightVoyageData() {
        /*List<FlightVoyageBean> dataList = new ArrayList<>();

        FlightVoyageBean bean = new FlightVoyageBean();
        bean.setBillOfLadingNumContext("ZCSU5846054");
        bean.setBillStatus("齐全");
        bean.setDeclareStatus("放行");
        bean.setInspectionStatus("待查");
        bean.setBoxNum("15");
        dataList.add(bean);

        FlightVoyageBean bean1 = new FlightVoyageBean();
        bean1.setBillOfLadingNumContext("JXLU5842906");
        bean1.setBillStatus("齐全");
        bean1.setDeclareStatus("放行");
        bean1.setInspectionStatus("待查");
        bean1.setBoxNum("20");
        dataList.add(bean1);

        FlightVoyageBean bean2 = new FlightVoyageBean();
        bean2.setBillOfLadingNumContext("OOLU6250128");
        bean2.setBillStatus("齐全");
        bean2.setDeclareStatus("放行");
        bean2.setInspectionStatus("待查");
        bean2.setBoxNum("65");
        dataList.add(bean2);

        FlightVoyageBean bean3 = new FlightVoyageBean();
        bean3.setBillOfLadingNumContext("ZCSU5846054");
        bean3.setBillStatus("齐全");
        bean3.setDeclareStatus("放行");
        bean3.setInspectionStatus("待查");
        bean3.setBoxNum("200");
        dataList.add(bean3);

        FlightVoyageBean bean4 = new FlightVoyageBean();
        bean4.setBillOfLadingNumContext("JXLU5842906");
        bean4.setBillStatus("齐全");
        bean4.setDeclareStatus("放行");
        bean4.setInspectionStatus("待查");
        bean4.setBoxNum("450");
        dataList.add(bean4);

        FlightVoyageBean bean5 = new FlightVoyageBean();
        bean5.setBillOfLadingNumContext("OOLU6250128");
        bean5.setBillStatus("齐全");
        bean5.setDeclareStatus("放行");
        bean5.setInspectionStatus("待查");
        bean5.setBoxNum("750");
        dataList.add(bean5);*/

        return null;
    }

    /**
     * 获取通关状态展示数据
     *
     * @return
     */
    public static List<String> getClearanceStatusData() {
        List<String> strList = new ArrayList<>();
        strList.add("疏港委托时间：2012-12-21 10:30:00");
        strList.add("分流触发时间：2013-01-10 11:30:00");
        strList.add("审核通过时间：2013-01-21 10:30:00");
        strList.add("换单时间：2013-02-12 10:30:00");
        strList.add("申报时间：2013-03-21 10:30:00");
        strList.add("海关放行时间：2013-03-22 10:30:00");
        strList.add("检疫放行时间：2013-04-21 10:30:00");
        return strList;
    }

    /**
     * 获取通关状态展示数据
     *
     * @return
     */
    public static List<String> getInspectionData() {
        List<String> strList = new ArrayList<>();
        strList.add("状态：待查");
        strList.add("是否检疫取样：是");
        strList.add("查验预约时间：2013-01-21 10:30:00");
        strList.add("靠台时间：2013-02-12 10:30:00");
        strList.add("开箱时间：2013-03-21 10:30:00");
        strList.add("查验结束时间：2013-03-22 10:30:00");
        strList.add("回装时间：2013-04-21 10:30:00");
        strList.add("离台时间：2013-05-21 10:30:00");
        return strList;
    }

    /**
     * 获取航班航次--->箱状态数据
     *
     * @return
     */
    public static List<BoxStatusBean> getBoxStatusData() {
        List<BoxStatusBean> dataList = new ArrayList<>();

        /*BoxStatusBean bean = new BoxStatusBean();
        bean.setContainerNo("ZCSU5846054");
        bean.setSuitcaseCarNo("辽B12345");
        bean.setReturnBoxDate(DateUtil.parseDate("2018-05-20").getTime());
        bean.setBoxEntryTime(DateUtil.parseDate("2018-05-21").getTime());
        bean.setArrivalTime(DateUtil.parseDate("2018-05-22").getTime());
        bean.setApproachTimeOfSuitcaseVehicle(DateUtil.parseDate("2018-05-23").getTime());
        bean.setBoxDrivingTime(DateUtil.parseDate("2018-05-24").getTime());
        dataList.add(bean);

        BoxStatusBean bean1 = new BoxStatusBean();
        bean1.setContainerNo("ZCSU5846054");
        bean1.setSuitcaseCarNo("辽B12345");
        bean1.setReturnBoxDate(DateUtil.parseDate("2018-05-20").getTime());
        bean1.setBoxEntryTime(DateUtil.parseDate("2018-05-21").getTime());
        bean1.setArrivalTime(DateUtil.parseDate("2018-05-22").getTime());
        bean1.setApproachTimeOfSuitcaseVehicle(DateUtil.parseDate("2018-05-23").getTime());
        bean1.setBoxDrivingTime(DateUtil.parseDate("2018-05-24").getTime());
        dataList.add(bean1);

        BoxStatusBean bean2 = new BoxStatusBean();
        bean2.setContainerNo("ZCSU5846054");
        bean2.setSuitcaseCarNo("辽B12345");
        bean2.setReturnBoxDate(DateUtil.parseDate("2018-05-20").getTime());
        bean2.setBoxEntryTime(DateUtil.parseDate("2018-05-21").getTime());
        bean2.setArrivalTime(DateUtil.parseDate("2018-05-22").getTime());
        bean2.setApproachTimeOfSuitcaseVehicle(DateUtil.parseDate("2018-05-23").getTime());
        bean2.setBoxDrivingTime(DateUtil.parseDate("2018-05-24").getTime());
        dataList.add(bean2);

        dataList.add(bean);
        dataList.add(bean1);
        dataList.add(bean2);*/

        return dataList;
    }

    /**
     * 获取预约首页列表数据
     *
     * @return
     */
    public static List<ReservationBean> getReservationData() {
        List<ReservationBean> dataList = new ArrayList<>();
        /*ReservationBean bean = new ReservationBean();
        bean.setBillLadingNo("456456ASDF");
        bean.setNumber("12");
        dataList.add(bean);
        ReservationBean bean1 = new ReservationBean();
        bean1.setBillLadingNo("AEWRQAWER");
        bean1.setNumber("32");
        dataList.add(bean1);
        ReservationBean bean2 = new ReservationBean();
        bean2.setBillLadingNo("FASDFASDFSAFD");
        bean2.setNumber("789");
        dataList.add(bean2);
        ReservationBean bean3 = new ReservationBean();
        bean3.setBillLadingNo("QWERASDFASDF");
        bean3.setNumber("75");
        dataList.add(bean3);
        ReservationBean bean4 = new ReservationBean();
        bean4.setBillLadingNo("ASDFASFS");
        bean4.setNumber("52");
        dataList.add(bean4);
        ReservationBean bean5 = new ReservationBean();
        bean5.setBillLadingNo("YUFRYTUTYU");
        bean5.setNumber("555");
        dataList.add(bean5);*/
        return dataList;
    }

    /**
     * 获取冻品数据
     *
     * @param type
     * @return
     */
    public static List<StatusBean> getFrozenFruitsData(String type) {
        String[] str;
        /*if (StrUtil.equals(type, "1")) {
            str = new String[]{"报关", "看货", "对扒", "入库", "取样", "出库", "配送"};
        } else {
            str = new String[]{"报关", "看货", "诉提", "入库", "熏蒸", "取样", "出库"};
        }
        List<StatusBean> statusBeans = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            StatusBean statusBean = new StatusBean();
            statusBean.setContainerNo("STATUS" + i);
            List<StatusBean> statusList = new ArrayList<>();
            for (String s : str) {
                StatusBean bean = new StatusBean();
                bean.setTitle(s);
                statusList.add(bean);
            }
            statusBean.setStatusList(statusList);
            statusBeans.add(statusBean);
        }*/
        return null;
    }

    /**
     * 获取单选预约数据
     *
     * @return
     */
    public static List<IndividualBean> getIndividualData() {
        String[] strData = new String[]{"疏港委托", "查验委托 ", "出库预约", "取样预约"};
        long[] lTime = new long[]{1453145729000L, 1455824129000L, 1456342529000L, 1457120129000L};
        List<IndividualBean> dataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            IndividualBean individualBean = new IndividualBean();
            individualBean.setContainerNo("international" + i);
            List<IndividualBean> individualBeans = new ArrayList<>();
            for (int j = 0; j < strData.length; j++) {
                IndividualBean bean = new IndividualBean();
                bean.setTypeName(strData[j]);
                bean.setIndividualTime(lTime[j]);
                individualBeans.add(bean);
            }
            individualBean.setTypeList(individualBeans);
            dataList.add(individualBean);
        }
        return dataList;
    }

    /**
     * 获取预约类型
     *
     * @return
     */
    public static List<String> getIndividualTypeData() {
        List<String> strList = new ArrayList<>();
        strList.add("疏港委托");
        strList.add("查验委托");
        strList.add("入库预约");
        strList.add("出库预约");
        strList.add("取样委托");
        strList.add("对扒预约");
        strList.add("拆提预约");
        strList.add("熏蒸预约");
        strList.add("验箱预约");
        strList.add("返箱预约");
        strList.add("配送预约");
        return strList;
    }

    /**
     * 获取预约类型
     *
     * @return
     */
    public static List<IndividualBean> getIndividualTypeData2() {
        List<IndividualBean> strList = new ArrayList<>();
        IndividualBean bean = new IndividualBean();
        bean.setTypeName("疏港委托");
        bean.setImgUrl("http://static.apih5.cn/icon/concealment_project.png");
        bean.setImgPath(R.drawable.one);
        strList.add(bean);
        IndividualBean bean1 = new IndividualBean();
        bean1.setTypeName("查验委托");
        bean1.setImgUrl("http://static.apih5.cn/icon/audit_management.png");
        bean1.setImgPath(R.drawable.two);
        strList.add(bean1);
        IndividualBean bean2 = new IndividualBean();
        bean2.setTypeName("入库预约");
        bean2.setImgUrl("http://static.apih5.cn/icon/data_report.png");
        bean2.setImgPath(R.drawable.three);
        strList.add(bean2);
        IndividualBean bean3 = new IndividualBean();
        bean3.setTypeName("出库预约");
        bean3.setImgUrl("http://static.apih5.cn/icon/hidden_trouble_investigation.png");
        bean3.setImgPath(R.drawable.four);
        strList.add(bean3);
        IndividualBean bean4 = new IndividualBean();
        bean4.setTypeName("取样委托");
        bean4.setImgUrl("http://static.apih5.cn/icon/qr_code.png");
        bean4.setImgPath(R.drawable.five);
        strList.add(bean4);
        IndividualBean bean5 = new IndividualBean();
        bean5.setTypeName("对扒预约");
        bean5.setImgUrl("http://static.apih5.cn/icon/lao_wu.png");
        bean5.setImgPath(R.drawable.six);
        strList.add(bean5);
        IndividualBean bean6 = new IndividualBean();
        bean6.setTypeName("拆提预约");
        bean6.setImgUrl("http://static.apih5.cn/icon/lao_wu_shu_ju.png");
        bean6.setImgPath(R.drawable.seven);
        strList.add(bean6);
        IndividualBean bean7 = new IndividualBean();
        bean7.setTypeName("熏蒸预约");
        bean7.setImgUrl("http://static.apih5.cn/icon/bang_zhu.png");
        bean7.setImgPath(R.drawable.eight);
        strList.add(bean7);
        IndividualBean bean8 = new IndividualBean();
        bean8.setTypeName("验箱预约");
        bean8.setImgUrl("http://static.apih5.cn/icon/quality_testing.png");
        bean8.setImgPath(R.drawable.nine);
        strList.add(bean8);
        IndividualBean bean9 = new IndividualBean();
        bean9.setTypeName("返箱预约");
        bean9.setImgUrl("http://static.apih5.cn/icon/shi_pin.png");
        bean9.setImgPath(R.drawable.ten);
        strList.add(bean9);
        IndividualBean bean10 = new IndividualBean();
        bean10.setTypeName("配送预约");
        bean10.setImgUrl("http://static.apih5.cn/icon/pin_zhi.png");
        bean10.setImgPath(R.drawable.eleven);
        strList.add(bean10);
        return strList;
    }

    /**
     * 获取订单数据
     *
     * @return
     */
    public static List<OrderBean> getOrderData() {
        List<OrderBean> dataList = new ArrayList<>();
        /*for (int i = 0; i < 10; i++) {
            OrderBean bean = new OrderBean();
            bean.setBillOfLadingNo("asdfajksldf");
            bean.setBillStatus("齐全");
            bean.setDeclareStatus("放行");
            bean.setInspectionStatus("已查");
            bean.setBoxNum("20" + i);
            dataList.add(bean);
        }*/
        return dataList;
    }

    /**
     * 获取订单数据
     *
     * @return
     */
    public static List<OrderBean> getOrderListData() {
        String date = DateUtil.formatDateTime(new Date());
        String[] strCurrentState = new String[]{"疏港委托：已受理 2018-12-12 12:20:00", "查验委托：已受理 2018-12-12 12:20:00",
                "报关委托：已受理 2018-12-12 12:20:00", "入库委托：已受理 2018-12-12 12:20:00", "出库委托：已受理 2018-12-12 12:20:00",
                "取样委托：已受理 2018-12-12 12:20:00", "对扒预约：已受理 2018-12-12 12:20:00", "诉提预约：已受理 2018-12-12 12:20:00",
                "熏蒸预约：已受理 2018-12-12 12:20:00", "验箱预约：已受理 2018-12-12 12:20:00",
                "返箱预约：已受理 2018-12-12 12:20:00", "配送预约：已受理 2018-12-12 12:20:00"};
        String[] strStep1 = new String[]{"船名航次：12131", "提单号：123123", "箱号：111111", "预计提箱时间：" + date};
        String[] strStep2 = new String[]{"提单号：12131", "箱号：222222", "报检号：456456", "品名：多多少少"};
        String[] strStep3 = new String[]{"提单号：12131", "箱号：222222", "件数：456456", "品名：多多少少", "规格：100*100", "件重：100KG"};
        String[] strStep4 = new String[]{"车号：辽B12345", "提单号：12131", "箱号：222222", "件数：456456", "品名：多多少少", "规格：100*100", "件重：100KG", "是否需要配车：是"};
        String[] strStep5 = new String[]{"原箱号：12345", "目标箱号：454465", "提单号：12131", "品名：多多少少", "规格：100*100", "件重：100KG", "件数：12", "是否需要配车：是"};
        String[] strStep6 = new String[]{"原箱号：12345", "目标车号：辽F65432", "目标箱号：454465", "提单号：12131", "品名：多多少少", "规格：100*100", "件重：100KG", "件数：12", "是否需要配车：是"};
        String[] strStep7 = new String[]{"提单号：12131", "箱号：222222", "件数：20", "熏蒸时间：" + date, "品名：多多少少", "规格：100*100", "件重：100KG", "色号：12"};
        String[] strStep8 = new String[]{"提单号：12131", "船公司：大连船舶公司", "验箱要求：无"};
        String[] strStep9 = new String[]{"提单号：12131", "箱号：222222", "车号：辽H11111", "车队：敢死队"};
        String[] strStep10 = new String[]{"箱号：222222", "品名：222222", "总重：111KG", "目的地：美国", "配送时间：" + date, "配送要求：无"};
        String[] strStep11 = new String[]{"船名航次：12131", "报单号：222222", "品名：222222"};
        List<String[]> strList = new ArrayList<>();
        strList.add(strStep1); // 疏港
        strList.add(strStep2); // 查验
        strList.add(strStep11); // 报关
        strList.add(strStep3); // 入库
        strList.add(strStep4); // 出库
        strList.add(strStep4); // 取样
        strList.add(strStep5);// 对扒
        strList.add(strStep6); // 诉提
        strList.add(strStep7); // 熏蒸
        strList.add(strStep8);
        strList.add(strStep9);
        strList.add(strStep10);
        List<OrderBean> dataList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            OrderBean bean = new OrderBean();
            /*if (i == 2) {
                bean.setCollection(true);
            }
            bean.setCurrentState(strCurrentState[i]);
            bean.setReservationStep(Arrays.asList(strList.get(i)));*/
            dataList.add(bean);
        }
        return dataList;
    }

    /**
     * 获取订单详情
     *
     * @return
     */
    public static List<OrderBean> getOrderDetailsData() {
        String[] reservationType = new String[]{"疏港委托", "报关预约", "查验委托", "入库预约", "出库预约",
                "取样委托", "拆提预约", "熏蒸预约", "对扒预约", "验箱预约", "返箱预约", "配送预约"};
        String date = DateUtil.formatDateTime(new Date());
        String[] strStep = new String[]{"已受理 " + date, "处理中 " + date, "已完成 " + date};
        String[] strStep1 = new String[]{"已受理 " + date, "车辆进场 " + date, "车辆靠台 " + date,
                "装车中 " + date, "装车完成 " + date, "已离台 " + date, "已发行 " + date};
        String[] strStep2 = new String[]{"已受理 " + date, "落箱完成 " + date, "车辆进场 " + date,
                "拆箱 " + date, "返箱 " + date};
        String[] strStep3 = new String[]{"已受理 " + date, "车辆进场 " + date, "靠台时间 " + date,
                "开始熏蒸 " + date, "结束熏蒸 " + date, "装车中 " + date, "装车完成 " + date,
                "已离台 " + date, "已发运 " + date};
        String[] strStep4 = new String[]{"已受理 " + date, "原车辆进场 " + date, "对扒中 " + date,
                "对扒完成 " + date, "已离台 " + date, "已发运 " + date};
        String[] strStep5 = new String[]{"已受理 " + date, "车辆进场 " + date, "车辆出场 " + date,
                "已到达 " + date, "已签收 " + date, "车辆位置图 " + date};
        List<String[]> reservationStepList = new ArrayList<>();
        reservationStepList.add(strStep);
        reservationStepList.add(strStep);
        reservationStepList.add(strStep);
        reservationStepList.add(strStep);
        reservationStepList.add(strStep1);
        reservationStepList.add(strStep1);
        reservationStepList.add(strStep2);
        reservationStepList.add(strStep3);
        reservationStepList.add(strStep4);
        reservationStepList.add(strStep);
        reservationStepList.add(strStep);
        reservationStepList.add(strStep5);
        String[] stepStatus = new String[]{"已处理 " + date, "未处理 " + date};
        List<OrderBean> dataList = new ArrayList<>();
        /*for (int i = 0; i < reservationType.length; i++) {
            OrderBean bean = new OrderBean();
            bean.setReservationType(reservationType[i]);
            bean.setReservationStep(Arrays.asList(reservationStepList.get(i)));
            if (i % 2 == 0) {
                bean.setStepStatus(stepStatus[0]);
            } else {
                bean.setStepStatus(stepStatus[1]);
            }
            dataList.add(bean);
        }*/
        return dataList;
    }

    /**
     * 获取疏港委托数据
     *
     * @return
     */
    public static List<EntrustingTheHarbourBean> getEntrustingData() {
        List<EntrustingTheHarbourBean> dataList = new ArrayList<>();
        /*for (int i = 0; i < 6; i++) {
            EntrustingTheHarbourBean bean = new EntrustingTheHarbourBean();
            bean.setBillOfLadingNo("123456789");
            bean.setEnglishShipName("EnglishShipName");
            bean.setBoxNum("123456");
            bean.setBoxType("整箱");
            bean.setBoxSize("100cm*100cm*100cm");
            bean.setImportVoyage("111111111");
            bean.setHarbourDredge("大连");
            dataList.add(bean);
        }*/
        return dataList;
    }

    /**
     * 获取查验数据
     *
     * @return
     */
    public static List<InspectionCommissionBean> getInspectionCommissionData() {
        List<InspectionCommissionBean> dataList = new ArrayList<>();
        /*for (int i = 0; i < 6; i++) {
            InspectionCommissionBean bean = new InspectionCommissionBean();
            bean.setNavigationalName("美国至大连");
            bean.setVoyageNum("K123456");
            bean.setWharf("珍珠港");
            dataList.add(bean);
        }*/
        return dataList;
    }

    /**
     * 获取查验--下一步数据
     *
     * @return
     */
    public static List<InspectionCommissionBean> getInspectionCommissionNextData() {
        String[] txtNameList = new String[]{"箱号", "箱持有人", "预约查验时间", "查验方式", "尺寸", "箱型", "空/重"};
        String[] controlTypeList = new String[]{"1", "1", "3", "2", "2", "2", "2"};
        String[] options = new String[]{"条件1", "条件2", "条件3", "条件4", "条件5", "条件6"};
        List<InspectionCommissionBean> optionList = new ArrayList<>();
        for (int m = 0; m < options.length; m++) {
            InspectionCommissionBean optionBean = new InspectionCommissionBean();
            optionBean.setOption(options[m]);
            optionBean.setOptionId(m + "");
            optionList.add(optionBean);
        }

        List<InspectionCommissionBean> dataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            InspectionCommissionBean bean = new InspectionCommissionBean();
            List<InspectionCommissionBean> beans = new ArrayList<>();
            for (int j = 0; j < txtNameList.length; j++) {
                InspectionCommissionBean beanContent = new InspectionCommissionBean();
                beanContent.setTxtName(txtNameList[j]);
                beanContent.setControlType(controlTypeList[j]);
                beanContent.setDateTimeType("3");
                beanContent.setOptions(optionList);
                beans.add(beanContent);
            }
            bean.setNextDataList(beans);
            dataList.add(bean);
        }
        return dataList;
    }

    /**
     * 获取查验--提交数据
     *
     * @return
     */
    public static InspectionCommissionBean getInspectionCommissionSubmitData() {
        String[] txtNameList = new String[]{"委托类型", "检查类型", "委托方", "受理方", "复查复验", "查验箱所在地",
                "货物名称", "包装", "件数", "报关单号", "危品类型", "危品等级", "联系人", "联系电话", "发票头名信息"};
        String[] submitFieldNameList = new String[]{"entrustType", "inspectionType", "entrust", "handSide", "recheckFlg", "locationOfInspectionBox",
                "cargoName", "packaging", "pieces", "customNo", "lsdType", "lsdLevel", "linkName", "linkTel", "invoiceTitle"};
        boolean[] isMust = new boolean[]{true, true, true, true, true, true,
                true, true, true, true, false, false, true, true, false};
        String[] controlTypeList = new String[]{"1", "1", "1", "2", "2", "2", "1",
                "2", "1", "1", "2", "2", "1", "1", "1"};
        String[] options = new String[]{"条件1", "条件2", "条件3", "条件4", "条件5", "条件6"};
        List<InspectionCommissionBean> optionList = new ArrayList<>();
        for (int m = 0; m < options.length; m++) {
            InspectionCommissionBean optionBean = new InspectionCommissionBean();
            optionBean.setOption(options[m]);
            optionBean.setOptionId(m + "");
            optionList.add(optionBean);
        }

        InspectionCommissionBean bean = new InspectionCommissionBean();
        List<InspectionCommissionBean> beans = new ArrayList<>();
        for (int j = 0; j < txtNameList.length; j++) {
            InspectionCommissionBean beanContent = new InspectionCommissionBean();
            beanContent.setTxtName(txtNameList[j]);
            beanContent.setControlType(controlTypeList[j]);
            beanContent.setOptions(optionList);
            beanContent.setSubmitFieldName(submitFieldNameList[j]);
            beanContent.setMust(isMust[j]);
            beans.add(beanContent);
        }
        bean.setNextDataList(beans);
        return bean;
    }
}
