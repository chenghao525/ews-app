package com.corp.basic;

import com.corp.frame.core.base.SystemProperty;
import com.corp.oa.util.G;

public class Constant {

	private final static String SYSTEM_DEBUG = "system.debug";

	// ����ϵͳ�Ƿ���debugģʽ
	public static boolean isSystemDebug() {
		return ("true".equals(SystemProperty.getProperty(SYSTEM_DEBUG)));
	}

	private static String WEB_SERVICE_URL = null;

	// ����ϵͳ���ýӿڵ�url��ַ
	public static String getWebServiceUrl() {
		if (G.isNvl(WEB_SERVICE_URL)) {
			WEB_SERVICE_URL = SystemProperty.getProperty("ywdWebService");
		}
		return WEB_SERVICE_URL;
	}

	public final static String AJAX_PARAMETER_KEY_NAME = "data";

	// ǰ�����ɵ��¼�¼��־
	public final static String AJAX_NEW_RECORD_ID = "_newId_";

	// ��̨�����в鿴ͼƬ��url��ַ
	private final static String MIS_URL_BASE = "misUrlBase";
	private static String MIS_URL_BASE_USE = null;

	public static String getBaseUrl() {
		if (MIS_URL_BASE_USE == null || "".equals(MIS_URL_BASE_USE)) {
			MIS_URL_BASE_USE = SystemProperty.getProperty(MIS_URL_BASE);
		}
		return MIS_URL_BASE_USE;
	}

	// ֱ��ָ��ͼƬ������
	private final static String IMAGE_URL_BASE = "imageUrlBase";
	private static String IMAGE_URL_BASE_USE = null;

	public static String getImageBaseUrl() {
		if (IMAGE_URL_BASE_USE == null || "".equals(IMAGE_URL_BASE_USE)) {
			IMAGE_URL_BASE_USE = SystemProperty.getProperty(IMAGE_URL_BASE);
		}
		return IMAGE_URL_BASE_USE;
	}

	public final static String UPLOAD_IMG_FILE_PATH_WEB = "/upload/photo/";

	// �Ű� �ĳ���uuid
	public final static String SCHEDULE_CITY_ROOT_UUID = "-1";

	// �Ű� �ĳ��� �Ϻ� uuid
	public final static String SCHEDULE_CITY_SH_UUID = "-100";
	// ̫�����������
	public final static String CHANNEL_OUTER_TBM = "601601chechannel";

	// ̫��E������������
	public final static String CHANNEL_OUTER_TBMEYANGCHE = "commonchannel";

	// ���ݿ���������ʾ��ʱ���ַ���
	public final static String MA_TYPE_MAN_HOUR = "man-hour";

	// ���ݿ���������ʾ���ϵ��ַ���
	public final static String MA_TYPE_ACCE = "car-acces";

	// ƥ�����г��͵��ַ���
	public final static String ALL_CAR_TYPE_MATCH = "all-cars-match";

	// ƥ������������ַ���
	public final static String ALL_BOMS_MATCH = "all-boms-match";

	private final static String WEB_FILE_PATH = "webFilePath";
	private final static String BU_WEB_FILE_PATH = "buWebFilePath";

	// �ƶ���ʹ�õ�Сͼ�ļ���
	public final static String APP_PIC_SMALL_DIR = "/appImages";

	// ��ʾ����ѱ�����Ʒ����һ�εĲ�Ʒ��
	public final static String TBY_PDU_ONCE = "once";
	// ��ʾ����ѱ�����Ʒ����ȫ��Ĳ�Ʒ��
	public final static String TBY_PDU_ALLYEAR = "allyear";

	// ��ȡ��ǰweb·��
	public static String getWebFilePath() {
		return SystemProperty.getProperty(WEB_FILE_PATH);
	}

	// ��ȡ��ǰBu web·��
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

	// �����ǻ�������id
	public final static String BOM_TYPE_JIYOU = "9ff8e7c8-9894-4333-b37a-b2babb746545";
	public final static String BOM_TYPE_JIYOU2 = "0d4e38d461af422880cbdfd0ca15fe60";
	public final static String BOM_TYPE_JILV = "a917f004-7ae7-45ae-bbfa-06efc95981bd";

	// �̻��˶�����Ѳ�Ʒ��Ҫ����Ƭ������

	public final static String IMG_TYPE_CAR_QM = "free-car-qm";// ����������Ƭ
	public final static String IMG_TYPE_CAR_QM_REPAIDED = "free-car-qm-repaired";// ���������ɺ����Ƭ
	public final static String IMG_TYPE_CAR_HEDA = "free-car-head"; // ��ͷ��Ƭ
	public final static String IMG_TYPE_CAR_VINCODE = "free-car-vincode";// ���ܺ���Ƭ
	public final static String IMG_TYPE_CAR_INSU = "free-car-insu";// ������Ƭ
	public final static String IMG_TYPE_CAR_TICKET = "free-car-ticket";// ������Ƭ
	public final static String IMG_TYPE_CAR_TRAVEL = "free-car-travel";// ��ʻ֤��Ƭ
	public final static String IMG_TYPE_CAR_MILE = "free-car-mile";// �����Ƭ
	public final static String IMG_TYPE_CAR_OTHERS = "free-car-others";// �����Ƭ

	public final static String IMG_TYPE_CAR_CHECK_IMG = "carcheck";
	// ���ӳ�ʱ��ǩ��
	public final static String IMG_TYPE_SIGN_IMG = "customersign";
	public final static String IMG_TYPE_CAR_REPAIR_IMG = "customercarrepair";
	// �������ӵ�ͼƬ
	public final static String IMG_TYPE_CAR_HANDOVER_IMG = "carhandover";
	// ������ȫ������Ŀͻ�ǩ��
	public final static String IMG_TYPE_SIGN_FINISH_IMG = "customerfinishsign";

	public final static String IMG_TYPE_ASSESS_AGREEMENT = "assess-agreement";// ����Э��ǩ��
	public final static String IMG_TYPE_ASSESS_TICKET = "assess-ticket";// �������ƾ֤

	// ��̨��Ӫ��Ա�ֹ����ͼƬ
	public final static String IMG_TYPE_EMP_ADD = "bgoptempadd";

	// �̻������ȫ����Ƭ
	public final static String IMG_TYPE_CAR_BU = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
			IMG_TYPE_CAR_QM, IMG_TYPE_CAR_QM_REPAIDED, IMG_TYPE_CAR_HEDA, IMG_TYPE_CAR_VINCODE,
			IMG_TYPE_CAR_MILE, IMG_TYPE_CAR_OTHERS, IMG_TYPE_CAR_INSU, IMG_TYPE_CAR_TICKET,
			IMG_TYPE_CAR_TRAVEL, IMG_TYPE_ASSESS_TICKET, IMG_TYPE_ASSESS_AGREEMENT);

	// ȡ�ͳ���ȫ����Ƭ
	public final static String IMG_TYPE_CAR_DILIVERY = String.format("%s,%s,%s,%s,%s",
			IMG_TYPE_CAR_CHECK_IMG, IMG_TYPE_SIGN_IMG, IMG_TYPE_CAR_REPAIR_IMG,
			IMG_TYPE_CAR_HANDOVER_IMG, IMG_TYPE_SIGN_FINISH_IMG);

	// ������ص���Ƭ
	public final static String IMG_TYPE_ALL = String.format("%s,%s,%s", IMG_TYPE_CAR_DILIVERY,
			IMG_TYPE_CAR_BU, IMG_TYPE_EMP_ADD);

	public final static String EXCHANGE_TYPE_GIFT = "gift";// �һ�ȯ���-��Ʒ��
	public final static String EXCHANGE_TYPE_COUPON = "coupon";// �һ�ȯ���-�Ż�ȯ

	/**
	 * С�������߸������ͻ��˵�ά����Ŀ�ڲ����
	 */
	public final static String MA_XBY_TYPE_ID = "2";

	// ��������sid
	public final static String MA_QM_TYPE_ID = "1241";

	// ��������������sid
	public final static String MA_QM_LP_TYPE_ID = "2121";

	// �ӽ�����sid
	public final static String MA_BANJIN_TYPE_ID = "2124";

	/**
	 * С�������߸������ͻ��˵�ά����Ŀ�ڲ����
	 */
	public final static String MA_JYJL_TYPE_ID = "2031";

	// ��ʾ����ѱ�����Ʒ���壬��ʾ���ݳ���ƥ��
	public final static String TBY_PDU_DEFINE_BYCAR = "bycaruuid";

	// ��ʾ����ѱ�����Ʒ���壬��ʾ���ݱ���ƥ��
	public final static String TBY_PDU_DEFINE_BYFEE = "byinsufee";

	// ��ʾ����ѱ�����Ʒ���壬��ʾʣ�µ�����ƥ��
	public final static String TBY_PDU_DEFINE_BYOTH = "byother";

	// ��ʾ����ѱ�����Ʒ���壬��ʾ���ݱ���ƥ��(ָ����Ʒ)
	public final static String TBY_PDU_DEFINE_BYFEE_OIL = "byinsufeedireoil";

	// ϵͳʹ�õ��ַ���
	public final static String SYSTEM_KEY_STR_REVIEW = "review";

	public final static String BOM_TYPE_JIYOU_NAME = "��������";

	public final static String BOM_OIL_TYPE_QY = "����";
	public final static String BOM_OIL_TYPE_CY = "����";

	public final static String BOM_OIL_TYPE_KWY = "������";
	public final static String BOM_OIL_TYPE_BHC = "��ϳ�";
	public final static String BOM_OIL_TYPE_QHC = "ȫ�ϳ�";

	// Ĭ����5w30����
	public final static String BOM_OIL_MODEL = "5W-30";

	public final static String BOM_OIL_TYPE_KWY_ID = "kuangwuyou";
	public final static String BOM_OIL_TYPE_BHC_ID = "banhecheng";
	public final static String BOM_OIL_TYPE_QHC_ID = "quanhecheng";

	// ϵͳ�����õļ��ַ�������
	public final static String SERVICE_TYPE_BAOYANG = "85";
	public final static String SERVICE_TYPE_NIANJIAN = "86";
	public final static String SERVICE_TYPE_XICHE = "93";
	public final static String SERVICE_TYPE_QIMIAN = "94";

	// ����ͱ�������Ʒ��code
	public final static String GIFT_TYPE_QIMIAN = "qimianmfk";
	public final static String GIFT_TYPE_BAOYANG = "shbymfk";

	// ��������webservice��url
	public final static String CC_WEBSERVICE_URL = "http://192.168.0.211:8888/UltraCRM_Webservice.asmx";
	// ��������¼���ļ��ľ��Ե�ַǰ׺
	public final static String CC_SOUND_PRE_PATH = "http://192.168.0.211:8088";

	// ���ڹ���̫���ڲ���ʦ��¼ϵͳ�鿴�������̻��ڲ�sid
	public final static String TB_USER_PROV_SID = "222";

	// ���⳵�ܺŵĿ�ͷ
	public final static String VIRTUAL_VIN_CODE_HEAD = "LSVIN-";

	private final static String THREAD_RUN_SERVER = "threadRunServer";

	// ����ִ���̵߳�server name
	public static String getThreadRunServer() {
		return SystemProperty.getProperty(THREAD_RUN_SERVER);
	}

	// �ַ����ķָ���
	public final static String STRING_BREAK_NEW = "#_#";

}
