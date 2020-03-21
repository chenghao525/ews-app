package com.corp.oa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.corp.basic.Constant;
import com.corp.basic.SystemInfoConstant;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.base.SystemConfig;
import com.corp.frame.core.base.defaultdriver.UserView;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.util.DateFormat;
import com.corp.oa.login.OaUserView;
import com.corp.webservice.client.eps.GateService;
import com.corp.webservice.client.eps.GateService_Service;


public class G {
	// private static Random random = new Random();

	private final static String sSkey = "207806514039";

	protected final static Log logger = LogFactory.getLog(G.class);

	static String sWrong = "";

	// �������ܣ���������ַ�����ȡָ�����ȵ��ִ������������ַ����
	// ����������str�ȴ���ȡ���ַ�����partitionҪ��ȡ�ĳ���
	// filler�����ַ���left����λ��
	// ��������ֵ������ȡ����ַ���
	public static String StrFormat(String str, int partition, char filler, boolean left) {

		// ���strΪnull���򷵻ؿմ�
		if (str == null)
			return "";
		// �����ȡ�ĳ���С��0���򷵻��ַ�������
		if (partition < 0)
			return str;
		// �����ȡ���ȵ���0���򷵻ؿմ�
		if (partition == 0)
			return "";

		// �������ַ���ת��Ϊ�ֽ������ʾ
		byte[] strbytes = str.getBytes();

		// �����ȡ���ȵ����ֽ����鳤�ȣ��򷵻�str����
		if (partition == strbytes.length)
			return str;
		else {
			// �����ȡ����С���ַ�������
			if (partition < strbytes.length) {
				// �����ȡ����partition��Χ��ʱ��������һ��
				// �Ǻ��֣����ұ���ȡһ������

				// count������˵�������һ�����ֱ���ȡһ������
				// �����ż���򲻻�������������
				int count = 0;
				for (int k = 0; k < partition; k++) {
					if (strbytes[k] > 0) {
						count = 0;
						continue;
					} else {
						count++;
					}
				}

				byte[] rtn;
				if (count % 2 == 0)

					// ż��ֱ�Ӿ���partition�Ĵ�С
					rtn = new byte[partition];
				else

					// ������Ҫpartition��1��������
					// ����һ������������������
					rtn = new byte[partition - 1];

				// ��ԭstr�ַ������ֽ����鸴�Ƶ�rtn��
				for (int i = 0; i < rtn.length; i++)
					rtn[i] = strbytes[i];

				// ���췵���ַ���strtmp
				String strtmp = new String(rtn);
				return strtmp + "��";
			} else
			// �����ȡ���ȴ����ַ�������
			{
				StringBuffer strbuf = new StringBuffer();
				for (int i = 0; i < partition - strbytes.length; i++)
					strbuf.append(filler);

				// ��������ұ�����ַ���
				if (!left)
					return str + strbuf.toString();
				// ��������������ַ���
				else
					return strbuf.toString() + str;
			}

		}
	}

	// ת������
	public static String convertChinese(String str) {
		if (str != null && !str.equals("")) {
			try {
				str = new String(str.getBytes("8859_1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				System.out.println("BaseActionServlet UnsupportedEncodingException: "
						+ e.getMessage());
			}
			return str;
		} else {
			return "";
		}
	}

	/**
	 * �򵥵ļ���ϵͳ
	 *
	 * @param infoToBeEncrypted
	 *            ��Ҫ���ܵ�����
	 *
	 * @return ���ر����ܵ�����,���ݳ���20λ
	 *
	 */

	@SuppressWarnings("finally")
	private static byte[] getEncryptedData(String infoToBeEncrypted) {

		byte[] digest = null; // new byte[20];
		try {
			// SHA�㷨����20λ���ȣ�MD5�㷨����16λ����
			// ϵͳ������ݿ�ʹ��20λ���ȣ���������ʹ��SHA�㷨
			MessageDigest md = MessageDigest.getInstance("SHA"); // "SHA-1"
			md.update(infoToBeEncrypted.getBytes("UTF-8"));
			digest = md.digest();
		} catch (NoSuchAlgorithmException ex) {
		} finally {
			return digest;
		}
	}

	private static String byte2hex(byte[] b) { // ������ת�ַ���
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * �������ܣ����ַ������� ����: ���ܵ��ַ��� ����ֵ:���ܺ���ַ���
	 */

	public static String getEncryptString(String str) {
		return byte2hex(getEncryptedData(str));
	}

	/**
	 * �������ܣ�null--->"" ����: �ַ��� ����ֵ:�ַ���
	 */
	public static String empty(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	public static String JudgeEmpty(String str) {
		String temp = "";
		temp = str;
		if (temp == null)
			temp = "";
		return temp;
	}

	/**
	 * �ѵ������滻������������
	 *
	 * @param str
	 *            String
	 * @return String
	 */
	private static String replaceQuotationMarks(String str) {
		str = str.replaceAll("'", "''").replaceAll("[*?]", " ").replaceAll("--", " ");
		return str;
	}

	public static String rqm(String str) {
		return replaceQuotationMarks(str);
	}

	public static String makeInstrSql(String field, String fs) {
		return " instr(" + field + ",'" + G.rqm(fs) + "')>0 ";
	}

	public static String makeEqualsSql(String field, String fs) {
		return " " + field + "='" + G.rqm(fs) + "' ";
	}

	public static String getSequence(String seqName, DataManager dm) throws SQLException {
		String value = SystemConfig.getSequence(seqName, dm);
		if (value == null) {
			throw new SQLException("can't find sequence name[" + seqName + "]");
		} else {
			return value;
		}
	}

	public static String getSequence(String seqName) throws SQLException {
		DataManager dm = null;
		try {
			dm = DataManager.getInstance();
			return getSequence(seqName, dm);
		} finally {
			if (dm != null) {
				dm.close();
			}
		}
	}

	/**
	 * ����UUID
	 *
	 * @return
	 */
	public static String uuid() {
		String u = UUID.randomUUID().toString();
		return u.replaceAll("-", "");
	}


	/**
	 * ��ָ����ʽ��ʽ��ָ��������
	 *
	 * @param d
	 * @param format
	 * @return
	 */
	public static String formatDate(Date d, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * ����ָ����ʽ��ʽ���ĵ�ǰʱ��
	 *
	 * @param format
	 * @return
	 */
	public static String getCurrDt(String format) {
		return formatDate(new Date(), format);
	}

	/**
	 * ���ص�ǰ���ڵ�yyyy-MM-dd��ʽ
	 *
	 * @param format
	 * @return
	 */
	public static String getCurrDate() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * ���ص�ǰʱ�䣬Ĭ�ϸ�ʽ��yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getCurrDt() {
		return getCurrDt("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ��ȡ��ǰ����
	 *
	 * @return YYYY-MM-DD
	 */
	public static String getCurrentDate() {
		return DateFormat.getCurrent(DateFormat.DATE_STYLE_DATE);
	}

	/**
	 * ��ȡ��ǰʱ��
	 *
	 * @return YYYY-MM-DD hh24:mi:ss
	 */
	public static String getCurrentTime() {
		return DateFormat.getCurrent(DateFormat.DATE_STYLE_FULL);
	}

	public static int getSecsBetweenTwoDate(String firstString, String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// �������ַ�����ʽ����
		}
		int nSecs = (int) ((secondDate.getTime() - firstDate.getTime()) / 1000);
		return nSecs;
	}

	/**
	 * ���ļ����еĺ���תΪUTF8����Ĵ�,�Ա�����ʱ����ȷ��ʾ�����ļ���. �ݺ������������������2003.08.01
	 *
	 * @param s
	 *            ԭ�ļ���
	 * @return ���±������ļ���
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * ����ϵͳ������keyname������ϵͳ����ֵ
	 *
	 * @param attrName
	 *            ������keyname
	 * @param dm
	 * @return
	 * @throws java.lang.Exception
	 */

	public static String getSystemInfoValueByKeyname(String attrName, DataManager dm) {
		return SystemInfoConstant.getSysKeyInfo(attrName);
	}

	public static String getSystemInfoValueByKeyname(String attrName) {
		return SystemInfoConstant.getSysKeyInfo(attrName);
	}

	public static void setSystemInfoValueByKeyname(String attrName, String attrValue, DataManager dm)
			throws Exception {
		String sql = "update t_info_system t set t.attrvalue=?,t.UPTDATE=? where t.attrname=?";
		dm.update(sql, new String[] { attrValue, getCurrentTime(), attrName });
		return;
	}

	public static String encryptkey(String sKey, int iLength) {
		// ��ȡָ�����ȵ�key
		String sKeystr = sKey;
		int kLength;
		kLength = sKey.length();
		if (iLength <= kLength)
			sKeystr = sKey.substring(0, iLength); // ��ȡ�ͼ����ַ���������ȵ�key
		else {
			// �ظ�keyʹ�������
			while (kLength < iLength) {
				if (kLength + sKey.length() >= iLength) {
					sKeystr = sKeystr + sKey.substring(0, iLength - kLength);
				} else {
					sKeystr = sKeystr + sKey;
				}
				kLength = sKeystr.length();
			}
		}
		return sKeystr;
	}

	public static String encrypt(String sKey, String sSource) {
		// �����ַ���
		String sTarget = "";
		String sKeystr; // �ͼ����ַ���������ȵ�key
		int sLength; // �����ַ�������
		char cS, cK;
		int iS, iK, iT;
		int i;

		if (sSource == "") {
			sWrong = "�ַ���Ϊ�գ�";
			return sTarget;
		}
		if (sKey == "") {
			sWrong = "��ָ����Կ��";
			return sTarget;
		}

		sLength = sSource.length();
		sKeystr = encryptkey(sKey, sLength);

		for (i = 0; i < sLength; i++) {
			cS = sSource.charAt(i);
			cK = sKeystr.charAt(i);
			iS = (int) cS;
			iK = (int) cK;
			iT = iS ^ iK;
			// System.out.print(iS);
			// System.out.print(" ");
			// System.out.print(iK);
			// System.out.print(" ");
			// System.out.print(iT);
			// System.out.print(" ");
			// System.out.println(iT+29);
			iT = iT + 29;
			iT = 1000 + iT / 10 % 10 * 100 + iT / 100 * 10 + iT % 10; // ʮλ����λ����λ
			sTarget = sTarget + Integer.toString(iT).substring(1);
		}

		return sTarget;
	}

	public static String encrypt(String sSource) {
		return encrypt(sSkey, sSource);
	}

	public static String decode(String sKey, String sTarget) {
		// ����

		String sSource = "";
		String sKeystr; // �ͽ����ַ���������ȵ�key
		int sLength; // �����ַ�������
		char cS, cK;
		String cT;
		int iS, iK, iT;
		int i;

		if (sTarget == "") {
			sWrong = "�ַ���Ϊ�գ�";
			return sSource;
		}
		if (sKey == "") {
			sWrong = "��ָ����Կ��";
			return sSource;
		}

		sLength = sTarget.length() / 3;
		sKeystr = encryptkey(sKey, sLength);

		for (i = 0; i < sLength; i++) {
			cT = sTarget.substring(i * 3, (i + 1) * 3);
			cK = sKeystr.charAt(i);
			iT = Integer.parseInt(cT);
			iT = iT / 10 % 10 * 100 + iT / 100 * 10 + iT % 10;
			iK = (int) cK;
			iS = (iT - 29) ^ iK;
			// System.out.print(iT);
			// System.out.print(" ");
			// System.out.print(iT-29);
			// System.out.print(" ");
			// System.out.print(iK);
			// System.out.print(" ");
			// System.out.println(iS);
			cS = (char) iS;
			sSource = sSource + cS;
		}

		return sSource;
	}

	public static String decode(String sTarget) {
		return decode(sSkey, sTarget);
	}

	// ����
	public static String jiaMi(String str) {
		return encrypt(str);
	}

	// ����
	public static String jieMi(String str) {
		if (str == null)
			return "";
		return decode(str);

	}

	// ������ת�����ö��Ÿ񿪵��ַ���
	public static String formate(String[] str) {
		String result = "";
		if (str != null) {
			for (int i = 0; i < str.length; i++)
				result = "," + str[i] + ",";
		}
		return result;
	}

	/**
	 * ��ȡ��ǰʱ��
	 *
	 * @return YYYY-MM-DD hh24:mi:ss
	 */
	public static String getDateTime() {
		return getCurrentTime();
	}

	public static boolean jumpdian(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.')
				return true;
		}
		return false;
	}

	/**
	 * ��������������÷ָ������������(1,2,3)
	 *
	 * @param seperator
	 *            �ָ��
	 * @param strings
	 *            �ָ������
	 *
	 */
	public static String join(String seperator, String[] strings) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
		for (int i = 1; i < length; i++) {
			buf.append(seperator).append(strings[i]);
		}
		return buf.toString();
	}

	// ���ػ��������ַ���
	public static String getCurrencyString(String dbl) {
		try {
			String sResult = "";
			if (dbl == null || "".equals(dbl)) {
				return sResult;
			}

			Double d = new Double(dbl);

			sResult = NumberFormat.getCurrencyInstance().format(d);// .substring(1);

			return sResult;
		} catch (Exception e) {
			return "";
		}

	}

	// ���ر�׼�������ַ���
	public static String getNumberString(String nub) {
		try {
			String sResult = "";
			if (nub == null || "".equals(nub)) {
				return sResult;
			}

			Double d = new Double(nub);

			sResult = NumberFormat.getNumberInstance().format(d);
			sResult = sResult.replaceAll(",", "");
			return sResult;
		} catch (Exception e) {
			return "";
		}
	}

	public static double setNumberRound(double v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * Ա��������ͼ�е�ƴ���ַ���
	 *
	 * @param city
	 * @return
	 */
	public static String empCityViewSql(String city) {
		return (isNvl(city) ? "" : " and city in('" + city.replaceAll(",", "','") + "') ");
	}

	/**
	 *
	 * @param dm
	 * @param empSid
	 *            Ա���ڲ����
	 * @return �Ƿ�߱�����ϵͳ����Ա��ɫ
	 */
	public static boolean isSuperSystemUserRole(DataManager dm, String empSid) {
		boolean is = false;
		String superUserRole = "00000018";
		String sql = "";
		try {
			sql = "select * from t_right_userrole t where t.empid="
					+ "(select sid from t_info_employee where sid=" + empSid
					+ " and state=1) and t.roleid='" + superUserRole + "'";
			DataObject dob = dm.findByPrimaryKey(sql, null);
			is = (dob != null);

		} catch (Exception e) {
		}
		return is;
	}

	/**
	 *
	 * @param dm
	 * @param empSid
	 *            Ա���ڲ����
	 * @return �Ƿ�߱�����ҵ���û���ɫ
	 */
	public static boolean isSuperBusinessUserRole(DataManager dm, String empSid) {
		boolean is = false;
		String superUserRole = "00000003";
		String sql = "";
		try {
			sql = "select * from t_right_userrole t where t.empid="
					+ "(select sid from t_info_employee where sid=" + empSid
					+ " and state=1) and t.roleid='" + superUserRole + "'";
			DataObject dob = dm.findByPrimaryKey(sql, null);
			is = (dob != null);

		} catch (Exception e) {
		}
		return is;
	}

	/**
	 *
	 * @param dm
	 * @param empSid
	 *            Ա�����ڲ����
	 * @param infoType
	 *            ���ҵ���Ϣ���Ŀǰ��employee, expressemployee
	 * @return ͼƬ��·������ԣ�
	 */
	public static String getImagePath(DataManager dm, String sid, String infoType) {
		String path = "";
		try {
			String sql = "select t.* from t_info_image t where t.state='1' and t.sid=? and t.INFO_TYPE=?";
			DataObject dob = dm.findByPrimaryKey(sql, new String[] { sid, infoType });
			if (dob != null && !isNvl(dob.getString("PATH"))) {
				path = G.getImgFullPath(dob.getString("PATH"));
			}
		} catch (Exception e) {
		}
		return path;
	}

	/**
	 *
	 * @param dm
	 * @param empSid
	 *            Ա�����ڲ����
	 * @param infoType
	 *            ���ҵ���Ϣ���Ŀǰ��employee, expressemployee
	 * @return ͼƬ��·������ԣ�
	 */
	public static String getImagePath(String sid, String infoType) {
		String path = "";
		DataManager dm = null;
		try {
			dm = DataManager.getInstance();
			path = getImagePath(dm, sid, infoType);

		} catch (Exception e) {
		} finally {
			if (dm != null) {
				dm.close();
			}
		}
		return path;
	}

	/**
	 *
	 * @param dm
	 * @param empSidԱ�����ڲ����
	 * @param infoType���ҵ���Ϣ���
	 *            ��Ŀǰ��employee, expressemployee
	 * @param path·��
	 * @return
	 */
	public static boolean setImagePath(DataManager dm, String sid, String infoType, String path) {
		try {
			DataObject dob = new DataObject("T_INFO_IMAGE");
			dob.setString("SID", sid);
			dob.setString("INFO_TYPE", infoType);
			dob.setString("PATH", path.replaceAll(Constant.getBaseUrl(), ""));
			dob.setString("STATE", "1");
			dob.setString("CRTUSER", "0");
			dob.setString("CRTDATE", getCurrentTime());
			dm.save(dob);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * ���ش�β���滻��mask *
	 *
	 * @param source
	 *            Դ�ַ���
	 * @param len
	 *            mask�ĳ���
	 * @return
	 */
	public static String setStringMask(String source, int len) {
		String r = "";
		if (source == null || len <= 0)
			return null;

		for (int i = 0; i < len; i++)
			r += "*";

		int sl = source.length();

		if (sl <= len) {
			return r;
		} else if (sl == 11) {
			r = source.substring(0, 3) + r + source.substring(7, 11);
		} else {
			if (source.indexOf("@") > 0) {// �ʼ�
				r = r + source.substring(len, source.length());
			} else {
				r = source.substring(0, source.length() - len) + r;
			}
		}
		return r;
	}

	/**
	 * ���رȽ����Ի��������ڼ�����ƣ��뵱ǰ���ڱȽϣ� ���磺1����ǰ��10����ǰ��1Сʱǰ�� 1��ǰ��3��ǰ���߸���
	 *
	 * @param sDate
	 *            �����ʱ��
	 * @param mileStone
	 *            ������߸��������������ֻ���ٵ�3��ǰ����3��󣬴���3
	 * @return ���磺1����ǰ��10����ǰ��1Сʱǰ�� 1��ǰ��3��ǰ���߸���
	 */
	public static String getNiceExpressDate(String sDate, int mileStone) {
		String r = "";
		try {
			String sBA = "";
			String sLong = "";

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date compareDate = df.parse(sDate);

			Date curDate = new Date();
			int i = curDate.compareTo(compareDate);
			long l = 0;

			if (i > 0) {
				sBA = "ǰ";
				sLong = "�����";
				l = (curDate.getTime() - compareDate.getTime()) / 60000;
			} else if (i < 0) {
				sBA = "��";
				sLong = "�����";
				l = (compareDate.getTime() - curDate.getTime()) / 60000;
			}
			if (l < 60) {// 1Сʱ�ڵ����
				r = String.valueOf(l) + "����" + sBA;
			} else {// 1Сʱ���ϵ����
				l = l / 60;
				if (l < 24) {// 1�����ڵ����
					r = String.valueOf(l) + "Сʱ" + sBA;
				} else {// 1�����ϵ����
					l = l / 24;
					if (l <= mileStone) {// �ڸ��ٵ�ʱ����
						r = String.valueOf(l) + "��" + sBA;
					} else {
						r = String.valueOf(mileStone) + "��" + sBA + sLong;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("getNiceExpressDate:" + e.getMessage());
		}
		return r;
	}

	public static String getNiceExpressDate(String sDate) {
		// Ĭ��mileStone=3
		return getNiceExpressDate(sDate, 3);
	}

	/*
	 * �������ľ�ȷ���
	 */
	public static float floatSub(float v1, float v2) {

		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));

		return b1.subtract(b2).floatValue();

	}

	/**
	 * ���Ͷ��ŵķ���
	 *
	 * @param phone
	 *            �ֻ���
	 * @param content
	 *            ����
	 * @param dm
	 * @return �������ŵ��ڲ�sid
	 */
	public static String sendSMS(String empSid, String phone, String content, DataManager dm) {

		if (isNvl(phone) || isNvl(content))
			return null;

		String re = null;
		try {
			// �滻����
			JSONObject json = new JSONObject();
			json.put("PHONE", phone);
			json.put("CONTENT", content);
			callWebService("sendSMS", json.toString(), empSid);

		} catch (Exception e) {
		}
		return re;
	}

	/**
	 * ���ַ���null�Ĵ��� ������Ϊnull������rep�����򷵻�ԭֵ
	 *
	 * @param in
	 *            ����
	 * @param rep
	 *            ���Ϊnull���滻ֵ
	 * @return
	 */
	public static String nvl(String in, String rep) {
		return ((in == null || "null".equalsIgnoreCase(in)) ? rep : in);
	}

	/**
	 * �жϴ�����ַ�����null����null�������߿��ַ��������߿ո��ַ���
	 *
	 * @param in
	 * @return
	 */
	public static boolean isNvl(String in) {
		return (in == null || "null".equalsIgnoreCase(in) || "undefined".equalsIgnoreCase(in) || ""
				.equals(in.trim()));
	}

	/**
	 * ������������־
	 *
	 * @param content
	 * @param dm
	 * @return
	 */
	public static void insertLog(String content, DataManager dm) {
		insertLog("0", null, content, dm);
	}

	/**
	 * ������������־
	 *
	 * @param content
	 * @param dm
	 * @return
	 */
	public static void insertLog(HttpServletRequest request, String type, String content,
			DataManager dm) {
		String empSid = getUserIdFromSession(request);
		if (!"0".equals(empSid) && !"3945".equals(empSid)) {
			insertLog(empSid, type, content, dm);
		}
	}

	/**
	 * ������������־
	 *
	 * @param content
	 * @param dm
	 * @return
	 */
	public static void insertLog(HttpServletRequest request, String content, DataManager dm) {
		insertLog(request, null, content, dm);
	}

	/**
	 * ������������־
	 *
	 * @param content
	 * @param dm
	 * @return
	 */
	public static void insertLog(String empSid, String type, String content, DataManager dm) {
		if (isNvl(content))
			return;
		try {
			DataObject dob = new DataObject("T_INFO_LOG");
			dob.setString("MENUID", "00000000");
			dob.setString("OPTEMPID", empSid);
			dob.setString("LOGTYPE", "A");
			if (type == null && content != null) {
				if (content.indexOf("����") >= 0 || content.indexOf("����") >= 0) {
					type = "43";
				} else if (content.indexOf("ɾ��") >= 0) {
					type = "45";
				} else if (content.indexOf("����") >= 0 || content.indexOf("����") >= 0
						|| content.indexOf("����") >= 0 || content.indexOf("���") >= 0) {
					type = "44";
				} else if (content.indexOf("��ѯ") >= 0 || content.indexOf("�鿴") >= 0
						|| content.indexOf("����") >= 0) {
					type = "46";
				} else if (content.indexOf("����ӿ�") >= 0) {
					type = "48";
				}
			}
			dob.setString("OPTTYPE", type);
			dob.setString("OPTATTR", content);
			dob.setString("OPTTIME", getDateTime());

			if (dm == null) {
				DataManager localDm = null;
				try {
					localDm = DataManager.getInstance();
					dob.setString("SID", localDm.getSequence("SEQ_T_INFO_LOG"));
					localDm.insert(dob);
				} catch (Exception ex) {
				} finally {
					if (localDm != null)
						localDm.close();
				}
			} else {
				dob.setString("SID", dm.getSequence("SEQ_T_INFO_LOG"));
				dm.insert(dob);
			}
		} catch (Exception e) {
		}
	}

	public static void inserExceptionLog(String content, DataManager dm) {
		insertLog("0", "58", content, dm);
	}

	/**
	 * ����ָ���Ľӿڷ���
	 *
	 * @param code
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String callWebService(String code, String params, String empSid) {

		String result = "";
		GateService_Service service = null;
		try {

			code = nvl(code, "");
			params = nvl(params, "");

			String wsUrl = Constant.getWebServiceUrl();
			service = new GateService_Service(new URL(wsUrl), new QName(
					"http://webservice.corp.com/", "GateService"));

			insertLog(empSid, "48", "��̨���ýӿڣ�" + code + "��������" + params.toString(), null);

			GateService port = service.getGateServiceImplPort();
			result = port.call(code, params);
			logger.info("��̨���ýӿڣ�" + code + "��������" + params.toString() + "������ֵ��" + result);
			// logger.info("Interface[code=" + code + ",params="
			// + params.toString() + "] invoke.Result=" + result);

		} catch (Exception e) {
			logger.error("��̨���ýӿڴ���[code=" + code + ",params=" + params.toString() + "].", e);
		}
		return result;
	}

	public static String getUTCToLocalTime(String date) {
		final String year = date.substring(0, 4);
		final String month = date.substring(5, 7);
		final String day = date.substring(8, 10);
		final String hour = date.substring(11, 13);
		final String minute = date.substring(14, 16);
		final String second = date.substring(17, 19);
		final int millisecond = Integer.valueOf(date.substring(20, 23));
		Calendar result = new GregorianCalendar(Integer.valueOf(year), Integer.valueOf(month) - 1,
				Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(minute),
				Integer.valueOf(second));
		result.set(Calendar.MILLISECOND, millisecond);
		result.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

		Date mydate = new Date(result.getTimeInMillis());

		return ((new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(mydate));
	}

	// ��session�з��ص�ǰ��¼��Ա���ڲ����
	public static String getUserIdFromSession(HttpServletRequest request) {
		String id = null;
		try {
			UserView uv = (UserView) request.getSession().getAttribute(
					SystemConfig.getWebUserViewName());
			if (uv != null)
				id = uv.getUserId();

		} catch (Exception e) {

		}
		return id;
	}
	//��session�з��ص�ǰ��¼�˺ŵ�����
	public static String getUserNameFromSession(HttpServletRequest request) {
		String name = null;
		try {
			name = (String) request.getSession().getAttribute("EMPNAME");
//			if (uv != null)
//				name = uv.getUserCNName();
			if (G.isNvl(name)) {
				name = request.getParameter("EMPNAME");
			}

		} catch (Exception e) {

		}
		return name;

	}
	public static String getEWSProvSidFromSession(HttpServletRequest request) {

		String ewsProvSid = (String) request.getSession().getAttribute("PROV_SID");
		if (G.isNvl(ewsProvSid)) {
			ewsProvSid = request.getParameter("PROV_SID");
		}

		return ewsProvSid;
	}
	// ��ȡ��ǰ�ĵ�¼�˺�
	public static String getUserLoginAccountFromSession(HttpServletRequest request) {

		String userAccount = (String) request.getSession().getAttribute("UserID");
		if (G.isNvl(userAccount)) {
			userAccount = request.getParameter("UserID");
		}

		return userAccount;
	}
	// ��ȡ��ǰ�Ķ�Ӧu8c�˺�
	public static String getU8cAccountFromSession(HttpServletRequest request) {

		String userAccount = (String) request.getSession().getAttribute("U8C_CODE");
		if (G.isNvl(userAccount)) {
			userAccount = request.getParameter("U8C_CODE");
		}

		return userAccount;
	}

	//��session�л�ȡ����¼��Ա��Ӧ��ƽ̨���
	public static String getEmployeeType(HttpServletRequest request) {
		String empType = (String) request.getSession().getAttribute("EMP_TYPE");
		if (G.isNvl(empType)) {
			empType = request.getParameter("EMP_TYPE");
		}
		return empType;
	}

	// �ж��ַ�����������
	public static boolean isNumeric(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern.compile("[-+]?\\d*\\.?\\d+");
		return pattern.matcher(str).matches();
	}

	/**
	 * ͨ��HttpServletRequest����IP��ַ
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return ip String
	 * @throws Exception
	 */
	public static String getIpAddr(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("x-forwarded-for");
		String uk = "unknown";
		if (ip == null || ip.length() == 0 || uk.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || uk.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || uk.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || uk.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || uk.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null) {
			String[] p = ip.split(",");
			ip = "";
			for (int i = 0; i < p.length; i++) {
				ip = p[i].trim();// ���ش�������ж��ip
				if (ip != null && !uk.equalsIgnoreCase(ip) && !"".equals(ip.trim()))
					return ip;
			}
		}
		return ip;
	}

	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	public static byte[] base64Decode(String str) throws IOException {
		return new BASE64Decoder().decodeBuffer(str);
	}

	// / ��������ı�׼��Ź���
	public static String getBomStandardId(String br, String bomId) {
		return "b" + br + "-" + bomId.replaceAll(" ", "").toLowerCase();// ƥ������ı�Ź���
	}

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
				'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			byte[] md = mdInst.digest();
			// ������ת����ʮ�����Ƶ��ַ�����ʽ
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// �ж��Ƿ�����ȷ���ֻ���
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(14[0-9])|(18[0-9])|(19[0-9])|(16[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		// System.out.println(m.matches() + "---");
		return m.matches();
	}

	/**
	 * �ж��û��Ƿ����ĳ��Ȩ��id�Ĳ���Ȩ��
	 *
	 * @param rightId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static boolean isOwnRight(String rightId, HttpServletRequest request) {
		boolean bOwn = false;
		if (isNvl(rightId))
			return bOwn;
		try {
			String userId = request.getParameter("SID");
			OaUserView uv = null;
			if (!isNvl(userId)) {
				uv = new OaUserView();
				uv.setUserId(userId);
			} else {
				uv = (OaUserView) request.getSession().getAttribute(
						SystemConfig.getWebUserViewName());

			}
			if (uv != null) {
				String[] r = rightId.split(",");
				for (int i = 0; i < r.length; i++) {
					bOwn = uv.hasPermission(r[i]);
					if (bOwn)
						return bOwn;
				}
			}
		} catch (Exception e) {
		}
		return bOwn;
	}

	/**
	 * ���в�����sql����format
	 * @param sql
	 * @param parameter
	 * @return
	 */
	public static String formatSql(String sql, Object parameter) {
		String _sql = sql;
		if (parameter == null)
			return _sql;

		if (parameter instanceof String[]) {
			String[] str = (String[]) parameter;
			for (int i = 0; i < str.length; i++) {
				_sql = _sql.replaceFirst("\\?", "'" + str[i] + "'");
			}
		} else if (parameter instanceof String) {
			String str = (String) parameter;
			_sql = _sql.replaceFirst("\\?", "'" + str + "'");
		}
		return _sql;
	}
	/**
	 * ��listת��String[]
	 * @param pl
	 * @return
	 */
	public static String[] listToStringArray(ArrayList<String> pl) {
		if (pl == null || pl.isEmpty()) {
			return null;
		}

		return pl.toArray(new String[pl.size()]);
	}

	/**
	 * ��ȡpost����
	 * @param request
	 * @return
	 */
	public static DataObject getSearchFromRequestByPost(HttpServletRequest request) {
		try {
			BufferedReader reader = request.getReader();
			String result = "";
			String tmp = "";
			while ((tmp = reader.readLine()) != null) {
				result += tmp;
			}
			DataObject dob = new DataObject(result);
			return dob.getDataObject(Constant.AJAX_PARAMETER_KEY_NAME);

		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * ��request�еĲ�ѯ�������ֶ�ת����dataobject
	 *
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static DataObject getSearchFromRequest(HttpServletRequest request)  {
		DataObject dob = new DataObject();
		String name = "", value = "";
		Enumeration names = request.getParameterNames();

		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			if (name == null)
				continue;
			if (Constant.AJAX_PARAMETER_KEY_NAME.equals(name)) {// ��data����
				value = request.getParameter(name);
				if (value != null) {
					JSONObject json = new JSONObject(value);

					if (json != null && json.length() > 0) {
						String key = "";
						for (Iterator<String> it = json.keys(); it.hasNext();) {
							key = it.next();
							if (key.startsWith("S_")) {
								try {
									value = json.getString(key);
									if (!isNvl(value))
										dob.setString(key, value);
								} catch (Exception e) {
								}
							}
						}
					}
				}

			} else if (name.startsWith("S_")) {
				value = request.getParameter(name);
				if (!isNvl(value))
					dob.setString(name, value);
			}
		}

		return dob;
	}

	// ǰ������Ӣ�Ķ���
	public static String commaBE(String str) {
		String myStr = str;
		if (isNvl(myStr)) {
			myStr = "";
		}
		return "," + myStr + ",";
	}

	// ����û��Ȩ�޵�response
	public static HttpServletResponse setRespNoRight(HttpServletResponse response) throws Exception {
		response.setStatus(600);
		response.getWriter().write("û��Ȩ��");
		return response;
	}

	// ���ò��������response
	public static HttpServletResponse setRespOptFailed(HttpServletResponse response, String error)
			throws Exception {
		response.setStatus(601);
		response.getWriter().write(error);
		return response;
	}

	/**
	 * ��ָ�������ڼӼ�����
	 *
	 * @param dayType
	 * @param addNum
	 * @param baseDay
	 * @return
	 */
	public static String addDay(int dayType, int addNum, String baseDay) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String newDay = "";
		try {
			Date myDate = formatter.parse(baseDay);
			Calendar c = Calendar.getInstance();
			c.setTime(myDate);
			c.add(dayType, addNum);
			myDate = c.getTime();
			newDay = formatter.format(myDate);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return newDay;
	}

	public static String addDay(int addNum, String baseDay) {
		return addDay(Calendar.DATE, addNum, baseDay);
	}

	/**
	 * �Ծ����ʱ��Ӽ�ʱ����
	 *
	 * @param timeType
	 * @param addNum
	 * @param baseTime
	 * @return
	 */
	public static String addTime(int timeType, int addNum, String baseTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newTime = "";
		try {
			Date myDate = formatter.parse(baseTime);
			Calendar c = Calendar.getInstance();
			c.setTime(myDate);
			c.add(timeType, addNum);
			myDate = c.getTime();
			newTime = formatter.format(myDate);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return newTime;
	}

	// ����ָ����Χ�������
	public static int RandomScope(int max, int min) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	/**
	 * ���ر�����������
	 *
	 * @return
	 */
	public static String getLocalHostName() {
		String address = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// String ip=addr.getHostAddress().toString();//��ñ���IP
			address = addr.getHostName().toString();// ��ñ�������

			// System.out.println(ip);
		} catch (Exception e) {
		}
		logger.info("HostName=" + address);
		return address;
	}

	// oracle�İ���ƴ������
	public static String getNlssort(String field, String sort) {
		if (isNvl(field))
			return "";
		String px = " nlssort(" + field + ",'NLS_SORT=SCHINESE_PINYIN_M') ";
		if (!isNvl(sort)) {
			px += sort + " ";
		}
		return px;
	}

	public static String getNlssort(String field) {
		return getNlssort(field, null);
	}

	// double���������룬������λС��
	public static double doubleRound(double d) {
		BigDecimal b = new BigDecimal(d);
		return (b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	// �жϵ�ǰʱ���Ƿ���ָ��Сʱ��Χ��
	public static boolean isHourScope(int startHour, int endHour) {
		int hour = Integer.parseInt(G.getCurrentTime().substring(11, 13));
		if (startHour >= 0 && endHour >= 0) {
			return ((hour >= startHour) && (hour <= endHour));
		} else if (startHour >= 0 && endHour < 0) {
			return (hour >= startHour);
		} else if (startHour < 0 && endHour >= 0) {
			return (hour <= endHour);
		} else
			return true;
	}

	// ��ȡ�ض���ͼƬ;��
	public static String getImgFullPath(String path) {
		if (isNvl(path)) {
			return null;
		}

		// �˱�����Ҫ�ӿ�������ж�ȡ
		String syncDate = SystemInfoConstant.getSysKeyInfo("imageUrlSyncDate");

		if (!path.startsWith(Constant.UPLOAD_IMG_FILE_PATH_WEB)
				|| path.length() < (Constant.UPLOAD_IMG_FILE_PATH_WEB + syncDate + "/").length()) {
			return Constant.getBaseUrl() + path;
		}

		String src = path.substring(13, 21).replaceAll("/", "");
		if (src.length() != syncDate.length()) {// ȷ���������ڸ�ʽ
			return Constant.getBaseUrl() + path;
		} else if (syncDate.compareTo(src) >= 0) {// ��Ҫ���õ�image�ķ�����url
			return Constant.getImageBaseUrl() + path; // ��Ҫ�޸ģ��������ļ�
		} else {
			return Constant.getBaseUrl() + path;
		}
	}

	// ȥ��emoji�����ַ�
	public static String removeEmoji(String str) {
		if (str == null)
			return null;
		return str.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5\\pP��������]", "");
	}

	// ����keyֵ�Ͷ�Ӧ��data�µ��ļ���������textֵ
	public static String exchangeDataText(String inText, String txtName, HttpServletRequest request) {
		String outText = "";
		if (isNvl(inText))
			return outText;
		JSONArray ja = getJaFromDataText(txtName, request);
		if (ja == null)
			return outText;

		return exchangeDataText(inText, ja);
	}

	// ���ļ�Ŀ¼�¶�ȡָ����txt�ļ�������jsonarray
	public static JSONArray getJaFromDataText(String txtName, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("");
		if (isNvl(txtName))
			return null;
		return FileUtil.getJaFromDataTxt(path + "\\data\\" + txtName);
	}

	// ����keyֵ�Ͷ�Ӧ��data�µ��ļ���������textֵ
	public static String exchangeDataText(String inText, JSONArray ja) {
		String outText = "";
		if (isNvl(inText))
			return outText;
		if (ja == null)
			return outText;

		String[] t = inText.split(",");
		JSONObject json = null;
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < ja.length(); j++) {
				json = ja.getJSONObject(j);
				if (t[i].equals(json.getString("id"))) {
					outText += json.getString("text") + ",";
					break;
				}
			}
		}
		if (!"".equals(outText)) {
			outText = outText.substring(0, outText.length() - 1);
		}
		return outText;
	}

	/**
	 *
	 * @param dm
	 * @param empSID
	 *            Ա�����ڲ����
	 * @return ����Ա�����ڵĹ�˾�ڲ���ţ����Ĺ�˾
	 */
	public static String getEmployeeCorp(DataManager dm, String empSID) {
		String corp = "";
		String sql;
		try {
			String city = getEmployeeCity(dm, empSID);
			if (!"".equals(city)) {
				sql = "select sid from T_INFO_DEPT where state='1' and dept_city=" + city
						+ " and PARENTDEPT=1";// ���ҵ����Ĺ�˾
				DataObject dob = dm.findByPrimaryKey(sql, null);
				if (dob != null) {
					corp = dob.getString("SID");
				}
			}
		} catch (Exception e) {
		}
		return corp;
	}

	/**
	 *
	 * @param dm
	 * @param empSID
	 *            Ա���ڲ����
	 * @return ���ظ�Ա�����ڳ��е��ڲ����
	 */
	public static String getEmployeeCity(DataManager dm, String empSID) throws Exception {
		String city = "-1000";
		String sql = "";
		DataObject dob = null;
		if (!isNvl(empSID)) {
			sql = "select dept_city from t_info_dept where sid="
					+ " (select t.parentdept from t_info_dept t where t.sid="
					+ "(select dutysid from t_info_employee where sid=" + empSID + "))";
			dob = dm.findByPrimaryKey(sql, null);
			if (dob != null) {
				city = dob.getString("DEPT_CITY");
			}
		}
		return city;
	}

	// ��ñ�ָ���е������ֵ
	/**
	 *
	 * @param dm
	 *            DataManager
	 * @param sTableName
	 *            String �������
	 * @param sColumnName
	 *            String �е�����
	 * @param sHeadStr
	 *            String ���е��ַ�����ʼ���ַ��� ���� mtr0001---mtr
	 * @param numLen
	 *            int ��ֵ�ĳ��� ���� �����ֵΪ4
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @return String ���ؿ���ʹ�õı�� ���� mtr0002
	 */
	public static String getColumMaxID(DataManager dm, String sTableName, String sColumnName,
			String sHeadStr, int numLen) throws NumberFormatException, SQLException {
		String sSql = "";
		int iValue = 0;
		DecimalFormat decimalformat = null;

		String sTemp = "";
		for (int i = 0; i < numLen; i++)
			sTemp += "0";

		decimalformat = new DecimalFormat(sTemp);

		sSql = "select max(upper(" + sColumnName + ")) as max from " + sTableName + " where upper("
				+ sColumnName + ") like upper('" + sHeadStr + "%') and instr(" + sColumnName
				+ ",'-')=0";
		DataObject do1 = dm.findByPrimaryKey(sSql, null);
		if (do1 != null) {
			String ss = do1.getString("max");
			if (ss != null) { // �������ȡ�����ֵ
				iValue = Integer.valueOf(ss.substring(sHeadStr.length())).intValue();
			}
		}

		String sReturn = sHeadStr + decimalformat.format(iValue + 1);
		return sReturn;
	}

	public static void main(String[] args) {

		// ����Ӣ�ı���ϵ��ַ���
		String str = "DataFunc([{\"retCode\": 1, \"��ϵ����\": \"�µ�Q5\", \"ǰ�����μ���\": \"ǰ��/���ѡװ\"}])";

		// �������֡�Ӣ����ĸ�����ֵĲ����滻Ϊ�պ󣬾�ʣ���֡�Ӣ����ĸ��������
		str = str.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5\\pP��������]", "");

		// ���Ϊ"Ѧ��������������OhMyGod"
		System.out.println(str);

		// double tt = -23.45;

		double f = ((double) 2995) / (double) 600;

		f = (f * 599) / 600;

		System.out.println(G.getNumberString(".32432"));
		System.out.println(getCurrencyString("-0098.5"));

		System.out.println("isNumeric==" + G.isNumeric("211,21.98"));

		System.out.println(G.addDay(Calendar.MONTH, -1, "2017-05-01").substring(0, 7));

	}

}
