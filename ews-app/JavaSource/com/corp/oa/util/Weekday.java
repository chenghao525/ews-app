package com.corp.oa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Weekday {

	/**
	 * 开始日期
	 * 
	 */

	private Date start;

	/**
	 * 结束日期
	 * 
	 */

	private Date end;

	/**
	 * 周一到周五,默认true,表示包括在内;不想包括在内设置为false
	 */

	private boolean workInMonday = true;

	private boolean workInTuesday = true;

	private boolean workInWednesday = true;

	private boolean workInThursday = true;

	private boolean workInFriday = true;

	/**
	 * 周六,周日,默认false
	 */

	private boolean workInSaturday = false;

	private boolean workInSunday = false;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public Date getStart() {
		return start;
	}

	public void setStart(java.util.Date start) {
		this.start = start;
		dropTime(this.start);
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(java.util.Date end) {
		this.end = end;
		this.dropTime(this.end);
	}

	public void setStart(String strStart) throws ParseException {
		if (strStart != null) {
			// strStart = collate(strStart);
			this.setStart(df.parse(strStart));
		}
	}

	public void setEnd(String strEnd) throws ParseException {
		if (strEnd != null) {
			// strEnd = collate(strEnd);
			this.setEnd(df.parse(strEnd));
		}
	}

	@SuppressWarnings("deprecation")
	private void dropTime(Date date) {
		if (date != null) {
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
		}
	}

	public static String collate(String strStart) {
		if (strStart != null) {
			int length = "yyyy-MM-dd".length();
			if (strStart.length() > length) {
				strStart = strStart.substring(0, length);
			}
		}
		return strStart;

	}

	/**
	 * 计算工作日的天数
	 * 
	 * @return int
	 * 
	 */

	public int getWorkday() {
		if (start == null || end == null) {
			return 0;
		}

		if (!start.before(end)) {
			return 0;
		}

		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(start);

		endCalendar.setTime(end);

		startCalendar.set(Calendar.MILLISECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);

		int days = 0;

		while (startCalendar.before(endCalendar)) {
			int dayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);

			// 这一天是星期几
			switch (dayOfWeek) {
			case 1:
				if (this.workInMonday) {
					days++;
				}
				break;
			case 2:
				if (this.workInTuesday) {
					days++;
				}
				break;
			case 3:
				if (this.workInWednesday) {
					days++;
				}
				break;
			case 4:
				if (this.workInThursday) {
					days++;
				}
				break;
			case 5:
				if (this.workInFriday) {
					days++;
				}
				break;
			case 6:
				if (this.workInSaturday) {
					days++;
				}
				// 周六
				break;
			case 7:
				if (this.workInSunday) {
					days++;
				}
				// 周日
				break;
			default:
				// error!;
				break;
			}
			startCalendar.add(Calendar.DATE, 1);
		}
		return days;
	}

	public boolean isWorkInSunday() {
		return workInSunday;
	}

	public void setWorkInSunday(boolean workInSunday) {
		this.workInSunday = workInSunday;
	}

	public boolean isWorkInMonday() {
		return workInMonday;
	}

	public void setWorkInMonday(boolean workInMonday) {
		this.workInMonday = workInMonday;
	}

	public boolean isWorkInTuesday() {
		return workInTuesday;
	}

	public void setWorkInTuesday(boolean workInTuesday) {
		this.workInTuesday = workInTuesday;
	}

	public boolean isWorkInWednesday() {
		return workInWednesday;
	}

	public void setWorkInWednesday(boolean workInWednesday) {
		this.workInWednesday = workInWednesday;
	}

	public boolean isWorkInThursday() {
		return workInThursday;
	}

	public void setWorkInThursday(boolean workInThursday) {
		this.workInThursday = workInThursday;
	}

	public boolean isWorkInFriday() {
		return workInFriday;
	}

	public void setWorkInFriday(boolean workInFriday) {
		this.workInFriday = workInFriday;
	}

	public boolean isWorkInSaturday() {
		return workInSaturday;
	}

	public void setWorkInSaturday(boolean workInSaturday) {
		this.workInSaturday = workInSaturday;
	}

	public static void main(String[] args) {

		try {
			// String a="A.state=1";
			// String b=a.substring(a.indexOf("A.STATE=")+1);
			// System.out.println("b="+b);
			Weekday wd = new Weekday();
			wd.setStart("2005-6-16 10:10:10");
			wd.setEnd("2005-6-20 12:10:10");
			// 包括周末
			boolean flag = false;
			wd.setWorkInSaturday(flag);
			wd.setWorkInSunday(flag);
			// 获得天数
			// int betweenDays = wd.getWorkday();
			System.out.println("2013-05-10 99 99".substring(0, 7));
		}

		catch (Exception ex) {
			System.out.println("" + ex.getMessage());
		}
	}
}
