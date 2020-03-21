package com.corp.basic;

import com.corp.frame.core.base.SystemProperty;
import com.corp.oa.util.G;

public class Constant {

	private final static String SYSTEM_DEBUG = "system.debug";

	// 返回系统是否是debug模式
	public static boolean isSystemDebug() {
		return ("true".equals(SystemProperty.getProperty(SYSTEM_DEBUG)));
	}

	private static String WEB_SERVICE_URL = null;

	// 返回系统调用接口的url地址
	public static String getWebServiceUrl() {
		if (G.isNvl(WEB_SERVICE_URL)) {
			WEB_SERVICE_URL = SystemProperty.getProperty("ywdWebService");
		}
		return WEB_SERVICE_URL;
	}

	public final static String AJAX_PARAMETER_KEY_NAME = "data";

	// 前端生成的新纪录标志
	public final static String AJAX_NEW_RECORD_ID = "_newId_";

	// 后台管理中查看图片的url地址
	private final static String MIS_URL_BASE = "misUrlBase";
	private static String MIS_URL_BASE_USE = null;

	public static String getBaseUrl() {
		if (MIS_URL_BASE_USE == null || "".equals(MIS_URL_BASE_USE)) {
			MIS_URL_BASE_USE = SystemProperty.getProperty(MIS_URL_BASE);
		}
		return MIS_URL_BASE_USE;
	}

	// 直接指向图片服务器
	private final static String IMAGE_URL_BASE = "imageUrlBase";
	private static String IMAGE_URL_BASE_USE = null;

	public static String getImageBaseUrl() {
		if (IMAGE_URL_BASE_USE == null || "".equals(IMAGE_URL_BASE_USE)) {
			IMAGE_URL_BASE_USE = SystemProperty.getProperty(IMAGE_URL_BASE);
		}
		return IMAGE_URL_BASE_USE;
	}

	public final static String UPLOAD_IMG_FILE_PATH_WEB = "/upload/photo/";

	// 排班 的城市uuid
	public final static String SCHEDULE_CITY_ROOT_UUID = "-1";

	// 排班 的城市 上海 uuid
	public final static String SCHEDULE_CITY_SH_UUID = "-100";
	// 太保独享的渠道
	public final static String CHANNEL_OUTER_TBM = "601601chechannel";

	// 太保E养车联合渠道
	public final static String CHANNEL_OUTER_TBMEYANGCHE = "commonchannel";

	// 数据库中用来表示工时的字符串
	public final static String MA_TYPE_MAN_HOUR = "man-hour";

	// 数据库中用来表示辅料的字符串
	public final static String MA_TYPE_ACCE = "car-acces";

	// 匹配所有车型的字符串
	public final static String ALL_CAR_TYPE_MATCH = "all-cars-match";

	// 匹配所有配件的字符串
	public final static String ALL_BOMS_MATCH = "all-boms-match";

	private final static String WEB_FILE_PATH = "webFilePath";
	private final static String BU_WEB_FILE_PATH = "buWebFilePath";

	// 移动端使用的小图文件夹
	public final static String APP_PIC_SMALL_DIR = "/appImages";

	// 表示是免费保养产品（送一次的产品）
	public final static String TBY_PDU_ONCE = "once";
	// 表示是免费保养产品（送全年的产品）
	public final static String TBY_PDU_ALLYEAR = "allyear";

	// 获取当前web路径
	public static String getWebFilePath() {
		return SystemProperty.getProperty(WEB_FILE_PATH);
	}

	// 获取当前Bu web路径
	public static String getBuWebFilePath() {
		return SystemProperty.getProperty(BU_WEB_FILE_PATH);
	}

	private static String TOKEN_COMMON_USE = null;

	public static String getCommonToken() {
		if (G.isNvl(TOKEN_COMMON_USE)) {
			TOKEN_COMMON_USE = SystemProperty.getProperty("commonToken");
		}
		return TOKEN_COMMON_USE;
	}

	// 以下是机油类别的id
	public final static String BOM_TYPE_JIYOU = "9ff8e7c8-9894-4333-b37a-b2babb746545";
	public final static String BOM_TYPE_JIYOU2 = "0d4e38d461af422880cbdfd0ca15fe60";
	public final static String BOM_TYPE_JILV = "a917f004-7ae7-45ae-bbfa-06efc95981bd";

	// 商户端对于免费产品需要拍照片的类型

	public final static String IMG_TYPE_CAR_QM = "free-car-qm";// 漆面服务的照片
	public final static String IMG_TYPE_CAR_QM_REPAIDED = "free-car-qm-repaired";// 漆面服务完成后的照片
	public final static String IMG_TYPE_CAR_HEDA = "free-car-head"; // 车头照片
	public final static String IMG_TYPE_CAR_VINCODE = "free-car-vincode";// 车架号照片
	public final static String IMG_TYPE_CAR_INSU = "free-car-insu";// 保单照片
	public final static String IMG_TYPE_CAR_TICKET = "free-car-ticket";// 工单照片
	public final static String IMG_TYPE_CAR_TRAVEL = "free-car-travel";// 行驶证照片
	public final static String IMG_TYPE_CAR_MILE = "free-car-mile";// 码表照片
	public final static String IMG_TYPE_CAR_OTHERS = "free-car-others";// 码表照片

	public final static String IMG_TYPE_CAR_CHECK_IMG = "carcheck";
	// 交接车时的签字
	public final static String IMG_TYPE_SIGN_IMG = "customersign";
	public final static String IMG_TYPE_CAR_REPAIR_IMG = "customercarrepair";
	// 车辆交接单图片
	public final static String IMG_TYPE_CAR_HANDOVER_IMG = "carhandover";
	// 任务完全结束后的客户签字
	public final static String IMG_TYPE_SIGN_FINISH_IMG = "customerfinishsign";

	public final static String IMG_TYPE_ASSESS_AGREEMENT = "assess-agreement";// 定损协议签名
	public final static String IMG_TYPE_ASSESS_TICKET = "assess-ticket";// 定损核销凭证

	// 后台运营人员手工添加图片
	public final static String IMG_TYPE_EMP_ADD = "bgoptempadd";

	// 商户拍摄的全部照片
	public final static String IMG_TYPE_CAR_BU = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
			IMG_TYPE_CAR_QM, IMG_TYPE_CAR_QM_REPAIDED, IMG_TYPE_CAR_HEDA, IMG_TYPE_CAR_VINCODE,
			IMG_TYPE_CAR_MILE, IMG_TYPE_CAR_OTHERS, IMG_TYPE_CAR_INSU, IMG_TYPE_CAR_TICKET,
			IMG_TYPE_CAR_TRAVEL, IMG_TYPE_ASSESS_TICKET, IMG_TYPE_ASSESS_AGREEMENT);

	// 取送车的全部照片
	public final static String IMG_TYPE_CAR_DILIVERY = String.format("%s,%s,%s,%s,%s",
			IMG_TYPE_CAR_CHECK_IMG, IMG_TYPE_SIGN_IMG, IMG_TYPE_CAR_REPAIR_IMG,
			IMG_TYPE_CAR_HANDOVER_IMG, IMG_TYPE_SIGN_FINISH_IMG);

	// 所有相关的照片
	public final static String IMG_TYPE_ALL = String.format("%s,%s,%s", IMG_TYPE_CAR_DILIVERY,
			IMG_TYPE_CAR_BU, IMG_TYPE_EMP_ADD);

	public final static String EXCHANGE_TYPE_GIFT = "gift";// 兑换券类别-礼品卡
	public final static String EXCHANGE_TYPE_COUPON = "coupon";// 兑换券类别-优惠券

	/**
	 * 小保养或者更换机油机滤的维保项目内部编号
	 */
	public final static String MA_XBY_TYPE_ID = "2";

	// 漆面服务的sid
	public final static String MA_QM_TYPE_ID = "1241";

	// 理赔类漆面服务的sid
	public final static String MA_QM_LP_TYPE_ID = "2121";

	// 钣金服务的sid
	public final static String MA_BANJIN_TYPE_ID = "2124";

	/**
	 * 小保养或者更换机油机滤的维保项目内部编号
	 */
	public final static String MA_JYJL_TYPE_ID = "2031";

	// 表示是免费保养产品定义，表示根据车款匹配
	public final static String TBY_PDU_DEFINE_BYCAR = "bycaruuid";

	// 表示是免费保养产品定义，表示根据保费匹配
	public final static String TBY_PDU_DEFINE_BYFEE = "byinsufee";

	// 表示是免费保养产品定义，表示剩下的其他匹配
	public final static String TBY_PDU_DEFINE_BYOTH = "byother";

	// 表示是免费保养产品定义，表示根据保费匹配(指定油品)
	public final static String TBY_PDU_DEFINE_BYFEE_OIL = "byinsufeedireoil";

	// 系统使用的字符串
	public final static String SYSTEM_KEY_STR_REVIEW = "review";

	public final static String BOM_TYPE_JIYOU_NAME = "发动机油";

	public final static String BOM_OIL_TYPE_QY = "汽油";
	public final static String BOM_OIL_TYPE_CY = "柴油";

	public final static String BOM_OIL_TYPE_KWY = "矿物油";
	public final static String BOM_OIL_TYPE_BHC = "半合成";
	public final static String BOM_OIL_TYPE_QHC = "全合成";

	// 默认是5w30机油
	public final static String BOM_OIL_MODEL = "5W-30";

	public final static String BOM_OIL_TYPE_KWY_ID = "kuangwuyou";
	public final static String BOM_OIL_TYPE_BHC_ID = "banhecheng";
	public final static String BOM_OIL_TYPE_QHC_ID = "quanhecheng";

	// 系统上配置的几种服务类型
	public final static String SERVICE_TYPE_BAOYANG = "85";
	public final static String SERVICE_TYPE_NIANJIAN = "86";
	public final static String SERVICE_TYPE_XICHE = "93";
	public final static String SERVICE_TYPE_QIMIAN = "94";

	// 漆面和保养的礼品卡code
	public final static String GIFT_TYPE_QIMIAN = "qimianmfk";
	public final static String GIFT_TYPE_BAOYANG = "shbymfk";

	// 呼叫中心webservice的url
	public final static String CC_WEBSERVICE_URL = "http://192.168.0.211:8888/UltraCRM_Webservice.asmx";
	// 呼叫中心录音文件的绝对地址前缀
	public final static String CC_SOUND_PRE_PATH = "http://192.168.0.211:8088";

	// 用于管理太保内部老师登录系统查看订单的商户内部sid
	public final static String TB_USER_PROV_SID = "222";

	// 虚拟车架号的开头
	public final static String VIRTUAL_VIN_CODE_HEAD = "LSVIN-";

	private final static String THREAD_RUN_SERVER = "threadRunServer";

	// 返回执行线程的server name
	public static String getThreadRunServer() {
		return SystemProperty.getProperty(THREAD_RUN_SERVER);
	}

	// 字符串的分隔符
	public final static String STRING_BREAK_NEW = "#_#";

}
