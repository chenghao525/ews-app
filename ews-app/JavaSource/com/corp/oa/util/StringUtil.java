package com.corp.oa.util;

import java.util.Collection;
import java.util.Iterator;

public class StringUtil {
	public static boolean isNullOrEmpty(Object obj) {
		return obj == null || "".equals(obj.toString());
	}

	public static String toString(Object obj) {
		if (obj == null)
			return "null";
		return obj.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String join(Collection s, String delimiter) {
		StringBuffer buffer = new StringBuffer();
		Iterator iter = s.iterator();
		while (iter.hasNext()) {
			buffer.append(iter.next());
			if (iter.hasNext()) {
				buffer.append(delimiter);
			}
		}
		return buffer.toString();
	}

	/**
	 * �������ͽ�ȫ�����ַ����ضϳɴ����ڵģ����ߴ�ʱ���ַ���
	 * 
	 * @param strTime
	 * @param type
	 * @return
	 */
	public static String subTimeString(String strTime, String type) {
		String re = "";
		if (strTime == null)
			return re;
		int l = strTime.length();
		if (l <= 19) {// �ж���ȫʱ�ڸ�ʽ
			if ("date".equalsIgnoreCase(type)) {// ��ȡ����
				if (l <= 10)
					re = strTime.substring(0, l);
				else
					re = strTime.substring(0, 10);
			} else if ("time".equalsIgnoreCase(type)) {// ��ȡʱ��
				if (l > 11)
					if (l < 19)
						re = strTime.substring(11, l);
					else
						re = strTime.substring(11, 19);
			}
		}
		return re;
	}

	public static String subTime2Date(String strTime) {
		return subTimeString(strTime, "date");
	}

	public static String subTime2Time(String strTime) {
		return subTimeString(strTime, "time");
	}

	public static void main(String args[]) {
		String tt = "2012-07";
		System.out.println(subTime2Date(tt));

		System.out.println(subTime2Time(tt));
	}
}
