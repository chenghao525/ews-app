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

	// 函数功能：对输入的字符串截取指定长度的字串，不够则用字符填充
	// 函数参数：str等待截取的字符串；partition要截取的长度
	// filler填充的字符；left填充的位置
	// 函数返回值：被截取后的字符串
	public static String StrFormat(String str, int partition, char filler, boolean left) {

		// 如果str为null，则返回空串
		if (str == null)
			return "";
		// 如果截取的长度小于0，则返回字符串本身
		if (partition < 0)
			return str;
		// 如果截取长度等于0，则返回空串
		if (partition == 0)
			return "";

		// 将输入字符串转化为字节数组表示
		byte[] strbytes = str.getBytes();

		// 如果截取长度等于字节数组长度，则返回str本身
		if (partition == strbytes.length)
			return str;
		else {
			// 如果截取长度小于字符串长度
			if (partition < strbytes.length) {
				// 计算截取长度partition范围内时候会有最后一个
				// 是汉字，而且被截取一半的情况

				// count是奇数说明有最后一个汉字被截取一半的情况
				// 如果是偶数则不会有这个情况发生
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

					// 偶数直接就是partition的大小
					rtn = new byte[partition];
				else

					// 奇数需要partition减1，将最后的
					// 汉字一起祛除，而不是留半个
					rtn = new byte[partition - 1];

				// 将原str字符串的字节数组复制到rtn中
				for (int i = 0; i < rtn.length; i++)
					rtn[i] = strbytes[i];

				// 构造返回字符串strtmp
				String strtmp = new String(rtn);
				return strtmp + "…";
			} else
			// 如果截取长度大于字符串长度
			{
				StringBuffer strbuf = new StringBuffer();
				for (int i = 0; i < partition - strbytes.length; i++)
					strbuf.append(filler);

				// 如果是在右边填充字符串
				if (!left)
					return str + strbuf.toString();
				// 如果是在左边填充字符串
				else
					return strbuf.toString() + str;
			}

		}
	}

	// 转换中文
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
	 * 简单的加密系统
	 *
	 * @param infoToBeEncrypted
	 *            需要加密的数据
	 *
	 * @return 返回被加密的数据,数据长度20位
	 *
	 */

	@SuppressWarnings("finally")
	private static byte[] getEncryptedData(String infoToBeEncrypted) {

		byte[] digest = null; // new byte[20];
		try {
			// SHA算法返回20位长度，MD5算法返回16位长度
			// 系统设计数据库使用20位长度，所以这里使用SHA算法
			MessageDigest md = MessageDigest.getInstance("SHA"); // "SHA-1"
			md.update(infoToBeEncrypted.getBytes("UTF-8"));
			digest = md.digest();
		} catch (NoSuchAlgorithmException ex) {
		} finally {
			return digest;
		}
	}

	private static String byte2hex(byte[] b) { // 二进制转字符串
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
	 * 函数功能：对字符串加密 参数: 加密的字符串 返回值:加密后的字符串
	 */

	public static String getEncryptString(String str) {
		return byte2hex(getEncryptedData(str));
	}

	/**
	 * 函数功能：null--->"" 参数: 字符串 返回值:字符串
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
	 * 把单引号替换成两个单引号
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
	 * 创建UUID
	 *
	 * @return
	 */
	public static String uuid() {
		String u = UUID.randomUUID().toString();
		return u.replaceAll("-", "");
	}


	/**
	 * 按指定格式格式化指定的日期
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
	 * 返回指定格式格式化的当前时间
	 *
	 * @param format
	 * @return
	 */
	public static String getCurrDt(String format) {
		return formatDate(new Date(), format);
	}

	/**
	 * 返回当前日期的yyyy-MM-dd格式
	 *
	 * @param format
	 * @return
	 */
	public static String getCurrDate() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 返回当前时间，默认格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getCurrDt() {
		return getCurrDt("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取当前日期
	 *
	 * @return YYYY-MM-DD
	 */
	public static String getCurrentDate() {
		return DateFormat.getCurrent(DateFormat.DATE_STYLE_DATE);
	}

	/**
	 * 获取当前时间
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
			// 日期型字符串格式错误
		}
		int nSecs = (int) ((secondDate.getTime() - firstDate.getTime()) / 1000);
		return nSecs;
	}

	/**
	 * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名. 纵横软件制作中心雨亦奇2003.08.01
	 *
	 * @param s
	 *            原文件名
	 * @return 重新编码后的文件名
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
	 * 根据系统参数的keyname来查找系统参数值
	 *
	 * @param attrName
	 *            参数的keyname
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
		// 获取指定长度的key
		String sKeystr = sKey;
		int kLength;
		kLength = sKey.length();
		if (iLength <= kLength)
			sKeystr = sKey.substring(0, iLength); // 截取和加密字符串长度相等的key
		else {
			// 重复key使长度相等
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
		// 加密字符串
		String sTarget = "";
		String sKeystr; // 和加密字符串长度相等的key
		int sLength; // 加密字符串长度
		char cS, cK;
		int iS, iK, iT;
		int i;

		if (sSource == "") {
			sWrong = "字符串为空！";
			return sTarget;
		}
		if (sKey == "") {
			sWrong = "请指定密钥！";
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
			iT = 1000 + iT / 10 % 10 * 100 + iT / 100 * 10 + iT % 10; // 十位、百位、个位
			sTarget = sTarget + Integer.toString(iT).substring(1);
		}

		return sTarget;
	}

	public static String encrypt(String sSource) {
		return encrypt(sSkey, sSource);
	}

	public static String decode(String sKey, String sTarget) {
		// 解密

		String sSource = "";
		String sKeystr; // 和解密字符串长度相等的key
		int sLength; // 解密字符串长度
		char cS, cK;
		String cT;
		int iS, iK, iT;
		int i;

		if (sTarget == "") {
			sWrong = "字符串为空！";
			return sSource;
		}
		if (sKey == "") {
			sWrong = "请指定密钥！";
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

	// 加密
	public static String jiaMi(String str) {
		return encrypt(str);
	}

	// 解密
	public static String jieMi(String str) {
		if (str == null)
			return "";
		return decode(str);

	}

	// 把数组转换成用逗号格开的字符串
	public static String formate(String[] str) {
		String result = "";
		if (str != null) {
			for (int i = 0; i < str.length; i++)
				result = "," + str[i] + ",";
		}
		return result;
	}

	/**
	 * 获取当前时间
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
	 * 把数组里的内容用分割符号连接起来(1,2,3)
	 *
	 * @param seperator
	 *            分割符
	 * @param strings
	 *            分割的内容
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

	// 返回货币类型字符串
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

	// 返回标准数字型字符串
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
	 * 员工城市视图中的拼接字符串
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
	 *            员工内部编号
	 * @return 是否具备超级系统管理员角色
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
	 *            员工内部编号
	 * @return 是否具备超级业务用户角色
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
	 *            员工的内部编号
	 * @param infoType
	 *            查找的信息类别，目前：employee, expressemployee
	 * @return 图片的路径（相对）
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
	 *            员工的内部编号
	 * @param infoType
	 *            查找的信息类别，目前：employee, expressemployee
	 * @return 图片的路径（相对）
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
	 * @param empSid员工的内部编号
	 * @param infoType查找的信息类别
	 *            ，目前：employee, expressemployee
	 * @param path路径
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
	 * 返回从尾部替换成mask *
	 *
	 * @param source
	 *            源字符串
	 * @param len
	 *            mask的长度
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
			if (source.indexOf("@") > 0) {// 邮件
				r = r + source.substring(len, source.length());
			} else {
				r = source.substring(0, source.length() - len) + r;
			}
		}
		return r;
	}

	/**
	 * 返回比较人性化表达的日期间隔形势（与当前日期比较） 例如：1分钟前，10分钟前，1小时前， 1天前，3天前或者更早
	 *
	 * @param sDate
	 *            传入的时间
	 * @param mileStone
	 *            更早或者更晚的天数，例如只跟踪到3天前或者3天后，传入3
	 * @return 例如：1分钟前，10分钟前，1小时前， 1天前，3天前或者更早
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
				sBA = "前";
				sLong = "或更早";
				l = (curDate.getTime() - compareDate.getTime()) / 60000;
			} else if (i < 0) {
				sBA = "后";
				sLong = "或更晚";
				l = (compareDate.getTime() - curDate.getTime()) / 60000;
			}
			if (l < 60) {// 1小时内的情况
				r = String.valueOf(l) + "分钟" + sBA;
			} else {// 1小时以上的情况
				l = l / 60;
				if (l < 24) {// 1天以内的情况
					r = String.valueOf(l) + "小时" + sBA;
				} else {// 1天以上的情况
					l = l / 24;
					if (l <= mileStone) {// 在跟踪的时间内
						r = String.valueOf(l) + "天" + sBA;
					} else {
						r = String.valueOf(mileStone) + "天" + sBA + sLong;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("getNiceExpressDate:" + e.getMessage());
		}
		return r;
	}

	public static String getNiceExpressDate(String sDate) {
		// 默认mileStone=3
		return getNiceExpressDate(sDate, 3);
	}

	/*
	 * 浮点数的精确相减
	 */
	public static float floatSub(float v1, float v2) {

		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));

		return b1.subtract(b2).floatValue();

	}

	/**
	 * 发送短信的方法
	 *
	 * @param phone
	 *            手机号
	 * @param content
	 *            内容
	 * @param dm
	 * @return 该条短信的内部sid
	 */
	public static String sendSMS(String empSid, String phone, String content, DataManager dm) {

		if (isNvl(phone) || isNvl(content))
			return null;

		String re = null;
		try {
			// 替换变量
			JSONObject json = new JSONObject();
			json.put("PHONE", phone);
			json.put("CONTENT", content);
			callWebService("sendSMS", json.toString(), empSid);

		} catch (Exception e) {
		}
		return re;
	}

	/**
	 * 空字符串null的处理 如输入为null，返回rep，否则返回原值
	 *
	 * @param in
	 *            输入
	 * @param rep
	 *            如果为null的替换值
	 * @return
	 */
	public static String nvl(String in, String rep) {
		return ((in == null || "null".equalsIgnoreCase(in)) ? rep : in);
	}

	/**
	 * 判断传入的字符串是null，“null”，或者空字符串，或者空格字符串
	 *
	 * @param in
	 * @return
	 */
	public static boolean isNvl(String in) {
		return (in == null || "null".equalsIgnoreCase(in) || "undefined".equalsIgnoreCase(in) || ""
				.equals(in.trim()));
	}

	/**
	 * 插入其他的日志
	 *
	 * @param content
	 * @param dm
	 * @return
	 */
	public static void insertLog(String content, DataManager dm) {
		insertLog("0", null, content, dm);
	}

	/**
	 * 插入其他的日志
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
	 * 插入其他的日志
	 *
	 * @param content
	 * @param dm
	 * @return
	 */
	public static void insertLog(HttpServletRequest request, String content, DataManager dm) {
		insertLog(request, null, content, dm);
	}

	/**
	 * 插入其他的日志
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
				if (content.indexOf("导入") >= 0 || content.indexOf("新增") >= 0) {
					type = "43";
				} else if (content.indexOf("删除") >= 0) {
					type = "45";
				} else if (content.indexOf("保存") >= 0 || content.indexOf("更新") >= 0
						|| content.indexOf("设置") >= 0 || content.indexOf("审核") >= 0) {
					type = "44";
				} else if (content.indexOf("查询") >= 0 || content.indexOf("查看") >= 0
						|| content.indexOf("导出") >= 0) {
					type = "46";
				} else if (content.indexOf("服务接口") >= 0) {
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
	 * 调用指定的接口服务
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

			insertLog(empSid, "48", "后台调用接口：" + code + "，参数：" + params.toString(), null);

			GateService port = service.getGateServiceImplPort();
			result = port.call(code, params);
			logger.info("后台调用接口：" + code + "，参数：" + params.toString() + "。返回值：" + result);
			// logger.info("Interface[code=" + code + ",params="
			// + params.toString() + "] invoke.Result=" + result);

		} catch (Exception e) {
			logger.error("后台调用接口错误：[code=" + code + ",params=" + params.toString() + "].", e);
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

	// 从session中返回当前登录人员的内部编号
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
	//从session中返回当前登录账号的名字
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
	// 获取当前的登录账号
	public static String getUserLoginAccountFromSession(HttpServletRequest request) {

		String userAccount = (String) request.getSession().getAttribute("UserID");
		if (G.isNvl(userAccount)) {
			userAccount = request.getParameter("UserID");
		}

		return userAccount;
	}
	// 获取当前的对应u8c账号
	public static String getU8cAccountFromSession(HttpServletRequest request) {

		String userAccount = (String) request.getSession().getAttribute("U8C_CODE");
		if (G.isNvl(userAccount)) {
			userAccount = request.getParameter("U8C_CODE");
		}

		return userAccount;
	}

	//从session中获取到登录人员对应的平台类别
	public static String getEmployeeType(HttpServletRequest request) {
		String empType = (String) request.getSession().getAttribute("EMP_TYPE");
		if (G.isNvl(empType)) {
			empType = request.getParameter("EMP_TYPE");
		}
		return empType;
	}

	// 判断字符串是数字型
	public static boolean isNumeric(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern.compile("[-+]?\\d*\\.?\\d+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 通过HttpServletRequest返回IP地址
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
				ip = p[i].trim();// 多重代理可能有多个ip
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

	// / 返回配件的标准编号规则
	public static String getBomStandardId(String br, String bomId) {
		return "b" + br + "-" + bomId.replaceAll(" ", "").toLowerCase();// 匹配配件的编号规则
	}

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
				'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
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

	// 判断是否是正确的手机号
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(14[0-9])|(18[0-9])|(19[0-9])|(16[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		// System.out.println(m.matches() + "---");
		return m.matches();
	}

	/**
	 * 判断用户是否具有某个权限id的操作权限
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
	 * 对有参数的sql进行format
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
	 * 将list转成String[]
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
	 * 获取post参数
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
	 * 将request中的查询条件的字段转换成dataobject
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
			if (Constant.AJAX_PARAMETER_KEY_NAME.equals(name)) {// 是data参数
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

	// 前后增加英文逗号
	public static String commaBE(String str) {
		String myStr = str;
		if (isNvl(myStr)) {
			myStr = "";
		}
		return "," + myStr + ",";
	}

	// 设置没有权限的response
	public static HttpServletResponse setRespNoRight(HttpServletResponse response) throws Exception {
		response.setStatus(600);
		response.getWriter().write("没有权限");
		return response;
	}

	// 设置操作错误的response
	public static HttpServletResponse setRespOptFailed(HttpServletResponse response, String error)
			throws Exception {
		response.setStatus(601);
		response.getWriter().write(error);
		return response;
	}

	/**
	 * 对指定的日期加减天数
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
	 * 对具体的时间加减时分秒
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

	// 返回指定范围的随机数
	public static int RandomScope(int max, int min) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	/**
	 * 返回本机的主机名
	 *
	 * @return
	 */
	public static String getLocalHostName() {
		String address = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// String ip=addr.getHostAddress().toString();//获得本机IP
			address = addr.getHostName().toString();// 获得本机名称

			// System.out.println(ip);
		} catch (Exception e) {
		}
		logger.info("HostName=" + address);
		return address;
	}

	// oracle的按照拼音排序
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

	// double的四舍五入，保留两位小数
	public static double doubleRound(double d) {
		BigDecimal b = new BigDecimal(d);
		return (b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	// 判断当前时间是否在指定小时范围内
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

	// 获取特定的图片途径
	public static String getImgFullPath(String path) {
		if (isNvl(path)) {
			return null;
		}

		// 此变量需要从库表配置中读取
		String syncDate = SystemInfoConstant.getSysKeyInfo("imageUrlSyncDate");

		if (!path.startsWith(Constant.UPLOAD_IMG_FILE_PATH_WEB)
				|| path.length() < (Constant.UPLOAD_IMG_FILE_PATH_WEB + syncDate + "/").length()) {
			return Constant.getBaseUrl() + path;
		}

		String src = path.substring(13, 21).replaceAll("/", "");
		if (src.length() != syncDate.length()) {// 确保符合日期格式
			return Constant.getBaseUrl() + path;
		} else if (syncDate.compareTo(src) >= 0) {// 需要配置到image的服务器url
			return Constant.getImageBaseUrl() + path; // 需要修改，读配置文件
		} else {
			return Constant.getBaseUrl() + path;
		}
	}

	// 去除emoji特殊字符
	public static String removeEmoji(String str) {
		if (str == null)
			return null;
		return str.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5\\pP‘’“”]", "");
	}

	// 根据key值和对应的data下的文件名，返回text值
	public static String exchangeDataText(String inText, String txtName, HttpServletRequest request) {
		String outText = "";
		if (isNvl(inText))
			return outText;
		JSONArray ja = getJaFromDataText(txtName, request);
		if (ja == null)
			return outText;

		return exchangeDataText(inText, ja);
	}

	// 从文件目录下读取指定的txt文件，返回jsonarray
	public static JSONArray getJaFromDataText(String txtName, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("");
		if (isNvl(txtName))
			return null;
		return FileUtil.getJaFromDataTxt(path + "\\data\\" + txtName);
	}

	// 根据key值和对应的data下的文件名，返回text值
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
	 *            员工的内部编号
	 * @return 返回员工所在的公司内部编号，最顶层的公司
	 */
	public static String getEmployeeCorp(DataManager dm, String empSID) {
		String corp = "";
		String sql;
		try {
			String city = getEmployeeCity(dm, empSID);
			if (!"".equals(city)) {
				sql = "select sid from T_INFO_DEPT where state='1' and dept_city=" + city
						+ " and PARENTDEPT=1";// 查找到最顶层的公司
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
	 *            员工内部编号
	 * @return 返回该员工所在城市的内部编号
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

	// 获得表指定列的最大编号值
	/**
	 *
	 * @param dm
	 *            DataManager
	 * @param sTableName
	 *            String 表的名称
	 * @param sColumnName
	 *            String 列的名称
	 * @param sHeadStr
	 *            String 该列的字符串开始的字符串 例如 mtr0001---mtr
	 * @param numLen
	 *            int 数值的长度 例如 上面的值为4
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @return String 返回可以使用的编号 例如 mtr0002
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
			if (ss != null) { // 如果可以取到最大值
				iValue = Integer.valueOf(ss.substring(sHeadStr.length())).intValue();
			}
		}

		String sReturn = sHeadStr + decimalformat.format(iValue + 1);
		return sReturn;
	}

	public static void main(String[] args) {

		// 中文英文标点混合的字符串
		String str = "DataFunc([{\"retCode\": 1, \"车系名称\": \"奥迪Q5\", \"前后座椅加热\": \"前有/后可选装\"}])";

		// 将非数字、英文字母、汉字的部分替换为空后，就剩数字、英文字母、汉字了
		str = str.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5\\pP‘’“”]", "");

		// 输出为"薛聪美得让人心碎OhMyGod"
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
