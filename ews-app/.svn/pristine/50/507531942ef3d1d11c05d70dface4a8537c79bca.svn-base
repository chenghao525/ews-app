package com.corp.oa.util;

import java.util.Random;

public class RandomNumberString {
	/**
	 * 验证码类型为仅数字 0~9
	 */
	public static final int TYPE_NUM_ONLY = 0;

	/**
	 * 验证码类型为仅字母，即大写、小写字母混合
	 */
	public static final int TYPE_LETTER_ONLY = 1;

	/**
	 * 验证码类型为数字、大写字母、小写字母混合
	 */
	public static final int TYPE_ALL_MIXED = 2;

	/**
	 * 验证码类型为数字、大写字母混合
	 */
	public static final int TYPE_NUM_UPPER = 3;

	/**
	 * 验证码类型为数字、小写字母混合
	 */
	public static final int TYPE_NUM_LOWER = 4;

	/**
	 * 验证码类型为仅大写字母
	 */
	public static final int TYPE_UPPER_ONLY = 5;

	/**
	 * 验证码类型为仅小写字母
	 */
	public static final int TYPE_LOWER_ONLY = 6;

	/*
	 * 生成验证码 混合
	 */
	public static String genValidateNumber() {
		StringBuffer ret = new StringBuffer("");
		Random rnd = new Random();
		for (int i = 0; i < 5; i++) {
			int n = rnd.nextInt(62);

			if (n < 10) {
				ret.append(n);
			} else {
				if (n >= 36)
					n += 61; // 小写字母
				else if (n >= 10)
					n += 55; // 大写字母
				ret.append((char) n);
			}
		}
		return ret.toString();
	}

	/**
	 * 生成验证码字符串
	 * 
	 * @param type
	 *            验证码类型，参见本类的静态属性
	 * @param length
	 *            验证码长度，大于0的整数
	 * @param exChars
	 *            需排除的特殊字符（仅对数字、字母混合型验证码有效，无需排除则为null）
	 * @return 验证码字符串
	 */
	public static String generateTextCode(int type, int length, String exChars) {

		if (length <= 0)
			return "";

		StringBuffer code = new StringBuffer();
		int i = 0;
		Random r = new Random();

		switch (type) {
		// 仅数字
		case TYPE_NUM_ONLY:
			while (i < length) {
				int t = r.nextInt(10);
				if (exChars == null || exChars.indexOf(t + "") < 0) {// 排除特殊字符
					code.append(t);
					i++;
				}
			}
			break;
		// 仅字母（即大写字母、小写字母混合）
		case TYPE_LETTER_ONLY:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97 || (t >= 65 && t <= 90))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;
		// 数字、大写字母、小写字母混合
		case TYPE_ALL_MIXED:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;
		// 数字、大写字母混合
		case TYPE_NUM_UPPER:
			while (i < length) {
				int t = r.nextInt(91);
				if ((t >= 65 || (t >= 48 && t <= 57))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;
		// 数字、小写字母混合
		case TYPE_NUM_LOWER:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97 || (t >= 48 && t <= 57))
						&& (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;
		// 仅大写字母
		case TYPE_UPPER_ONLY:
			while (i < length) {
				int t = r.nextInt(91);
				if ((t >= 65) && (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;
		// 仅小写字母
		case TYPE_LOWER_ONLY:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t >= 97) && (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
			break;
		}
		return code.toString();
	}
}
