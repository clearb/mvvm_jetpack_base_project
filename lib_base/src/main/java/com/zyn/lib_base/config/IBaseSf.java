package com.zyn.lib_base.config;

import java.io.File;

/**
 * ***********************************************
 * 包路径:com.zfkj.lib_base.config
 * 类描述:
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/3/23 13:40
 * 项目名称:BaseTsenf20220220
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ***********************************************
 */
public interface IBaseSf {
    long TIME_1S = 1000L;
    long TIME_2S = 2000L;
    long TIME_3S = 3000L;
    long TIME_4S = 4000L;
    long TIME_5S = 5000L;
    long TIME_10S = 10000L;
    long TIME_20S = 20000L;
    long TIME_30S = 30000L;
    long TIME_40S = 40000L;
    long TIME_50S = 50000L;
    long TIME_1M = 60000L;
    long TIME_2M = 120000L;
    long TIME_3M = 180000L;
    long TIME_4M = 240000L;
    long TIME_5M = 300000L;
    long TIME_10M = 600000L;
    long TIME_20M = 1200000L;
    long TIME_30M = 1800000L;
    long TIME_40M = 2400000L;
    long TIME_50M = 3000000L;
    long TIME_1H = 3600000L;
    long TIME_2H = 7200000L;
    long TIME_3H = 10800000L;
    long TIME_4H = 14400000L;
    long TIME_5H = 18000000L;
    long TIME_6H = 21600000L;
    long TIME_7H = 25200000L;
    long TIME_8H = 28800000L;
    long TIME_9H = 32400000L;
    long TIME_10H = 36000000L;
    long TIME_11H = 39600000L;
    long TIME_12H = 43200000L;
    long TIME_13H = 46800000L;
    long TIME_14H = 50400000L;
    long TIME_15H = 54000000L;
    long TIME_16H = 57600000L;
    long TIME_17H = 61200000L;
    long TIME_18H = 64800000L;
    long TIME_19H = 68400000L;
    long TIME_20H = 72000000L;
    long TIME_21H = 75600000L;
    long TIME_22H = 79200000L;
    long TIME_23H = 82800000L;
    long TIME_1D = 86400000L;
    long TIME_2D = 172800000L;
    long TIME_3D = 259200000L;
    long TIME_4D = 345600000L;
    long TIME_5D = 432000000L;
    long TIME_6D = 518400000L;
    long TIME_7D = 604800000L;
    long TIME_8D = 691200000L;
    long TIME_9D = 777600000L;
    long TIME_10D = 864000000L;
    long TIME_20D = 1728000000L;
    long TIME_30D = 2592000000L;
    long TIME_40D = 3456000000L;
    long TIME_50D = 4320000000L;
    long TIME_60D = 5184000000L;
    long TIME_70D = 6048000000L;
    long TIME_80D = 6912000000L;
    long TIME_90D = 7776000000L;
    long TIME_100D = 8640000000L;
    String DT_001 = "yyyy-MM-dd HH:mm:ss";
    String DT_002 = "yyyy-MM-dd HH:mm";
    String DT_003 = "yyyy-MM-dd";
    String DT_004 = "HH:mm:ss";
    String DT_005 = "yyyy-MM";
    String DT_006 = "MM-dd";
    String DT_007 = "HH:mm";
    String DT_008 = "mm:ss";
    String DT_009 = "yyyy";
    String DT_010 = "MM";
    String DT_011 = "dd";
    String DT_012 = "HH";
    String DT_013 = "mm";
    String DT_014 = "ss";
    String DT_015 = "yyyy年MM月dd日 HH时mm分ss秒";
    String DT_016 = "yyyy年MM月dd日 HH时mm分";
    String DT_017 = "yyyy年MM月dd日";
    String DT_018 = "HH时mm分ss秒";
    String DT_019 = "yyyy年MM月";
    String DT_020 = "MM月dd日";
    String DT_021 = "HH时mm分";
    String DT_022 = "mm分ss秒";
    String DT_023 = "yyyy年";
    String DT_024 = "MM月";
    String DT_025 = "dd日";
    String DT_026 = "HH时";
    String DT_027 = "mm分";
    String DT_028 = "ss秒";
    String DT_029 = "yyyyMMddHHmmss";
    String DT_030 = "yyyyMMddHHmm";
    String DT_031 = "yyyyMMddHH";
    String DT_032 = "yyyyMMdd";
    String DT_033 = "yyyyMM";
    String NF_001 = "#0";
    String NF_002 = "#0.0";
    String NF_003 = "#0.00";
    String NF_004 = "#0.000";
    String NF_005 = "#0.0000";
    String NF_006 = "#,##0";
    String NF_007 = "#,##0.0";
    String NF_008 = "#,##0.00";
    String NF_009 = "#,##0.000";
    String NF_010 = "#,##0.0000";
    String NF_011 = "00000000";

    long DL_1B = 1L;
    long DL_1KB = 1024L;
    long DL_1MB = 1048576L;
    long DL_10MB = 10485760L;
    long DL_1GB = 1073741824L;
    long DL_1TB = 1099511627776L;

    String S_FS = File.separator;

    /** App 容器activity */
    String KEY_APP_CENTER_ACTIVITY = "ContainerFmActivity";

    /**
     * 以下fragment页面自己处理蓝牙断开回调，而不用统一跳回到魔盒列表页面
     */
    String[] noOutFragmentClassName = {"ScanAndLoginBoxFragment"};

    /** 蓝牙服务保护code */
    int BLUE_REQUEST_CODE = 100;
    /** WiFi服务保护code */
    int WIFI_REQUEST_CODE = 200;


    /** KEY APP 包名 ID */
    public static final String KEY_APP_PACKAGE= "package:";

    /** NumberFormat 存储空间大小 */
    String NF_101 = "#,##0.#";
}
