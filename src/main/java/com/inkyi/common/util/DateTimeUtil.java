package com.inkyi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 由java.util.Date到java.sql.Date的类型转换
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getSqlDate(java.util.Date date) {
		return new Date(date.getTime());
	}

	public static Date nowDate() {
		Calendar calendar = Calendar.getInstance();
		return getSqlDate(calendar.getTime());
	}

	/**
	 * 获得某一日期的后一天
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day + 1);
		return getSqlDate(calendar.getTime());
	}

	// 获得某一日期的后n天.
	public static java.util.Date getNextNDate(java.util.Date begin, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day + n);
		return calendar.getTime();
	}

	/**
	 * 根据当前时间 ，跨度取到时间
	 * 
	 * @param date
	 * @return Date
	 */
	public static String getDateStr(int date) {
		Date dNow = new Date(); // 当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, -date);
		Date dBefore = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		return sdf.format(dBefore);
	}

	/**
	 * 获得某一日期的前一天
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getPreviousDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - 1);
		return getSqlDate(calendar.getTime());
	}

	/**
	 * 获得某年某月第一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		return getSqlDate(calendar.getTime());
	}

	/**
	 * 获得某年某月最后一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return Date
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 1);
		return getPreviousDate(getSqlDate(calendar.getTime()));
	}

	/**
	 * 获得某一日期的后n月的日期
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getDayNextNMonth(int year, int month, int day, int nMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month + nMonth - 1);
		calendar.set(Calendar.DATE, day);
		return getSqlDate(calendar.getTime());
	}

	/**
	 * 由年月日构建java.sql.Date类型
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return Date
	 */
	public static Date buildDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		return getSqlDate(calendar.getTime());
	}

	/**
	 * 取得某月的天数
	 * 
	 * @param year
	 * @param month
	 * @return int
	 */
	public static int getDayCountOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 0);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获得某年某季度的最后一天的日期
	 * 
	 * @param year
	 * @param quarter
	 * @return Date
	 */
	public static Date getLastDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = quarter * 3;
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 获得某年某季度的第一天的日期
	 * 
	 * @param year
	 * @param quarter
	 * @return Date
	 */
	public static Date getFirstDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = (quarter - 1) * 3 + 1;
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 获得某年的第一天的日期
	 * 
	 * @param year
	 * @return Date
	 */
	public static Date getFirstDayOfYear(int year) {
		return getFirstDayOfMonth(year, 1);
	}

	/**
	 * 获得某年的最后一天的日期
	 * 
	 * @param year
	 * @return Date
	 */
	public static Date getLastDayOfYear(int year) {
		return getLastDayOfMonth(year, 12);
	}

	/**
	 * 功能：根据输入的日期字符串和日期格式转换为DATE类型 参数1：日期字符串 如1982-12-24 参数X：字符串格式 如yyyy-MM-dd
	 * 添加人：孙延来
	 */
	public static java.util.Date toDate(String dateStr, String formatStr) {
		int year = Integer.parseInt(dateStr.substring(formatStr.indexOf("y"),
				formatStr.lastIndexOf("y") + 1));
		int month = Integer.parseInt(dateStr.substring(formatStr.indexOf("M"),
				formatStr.lastIndexOf("M") + 1)) - 1;
		int day = Integer.parseInt(dateStr.substring(formatStr.indexOf("d"),
				formatStr.lastIndexOf("d") + 1));

		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		return c.getTime();
	}

	/**
	 * 功能：根据输入的日期字符串和日期格式转换为DATE类型 参数1：日期字符串 如1982-12-24 22:22:22 参数X：字符串格式
	 * 如yyyy-MM-dd HH:mm:ss
	 */
	public static java.util.Date toDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 功能：将Date类型转换为自定义格式的字符串样式 参数1：date 参数X：格式 如yyyy-MM-dd 添加人：孙延来
	 */
	public static String toStringDate(java.util.Date date, String formatStr) {
		String fdate = "";
		try {
			SimpleDateFormat f = new SimpleDateFormat(formatStr);
			fdate = f.format(date);
		} catch (Exception ex) {
			System.out.println("日期格式不正确");
		}
		return fdate;
	}
	
	/**
	 * 功能：通过输入的日期算出该人的年龄 参数1：生日串 参数X：格式 如yyyy-MM-dd 添加人：孙延来
	 */
	public static int getPeopleAge(String date, String formatStr) {
		// Calendar.YEAR
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR)
				- Integer.parseInt(date.substring(formatStr.indexOf("y"),
						formatStr.lastIndexOf("y") + 1));
	}

	/**
	 * 功能：通过输入的日期算出该人的年龄 参数1：参数解释
	 *
	 * 添加人：孙延来
	 */
	@SuppressWarnings("deprecation")
	public static int getPeopleAge(java.util.Date birthday) {
		// Calendar.YEAR

		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) - birthday.getYear();
	}

	/*
	 * 计算两个日期的相差天数
	 */
	public static int dataCount(String from_Date, String to_Date) {
		int dataCount = 0;
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long data_mm;
			java.util.Date from_D = s.parse(from_Date);
			java.util.Date to_D = s.parse(to_Date);

			data_mm = from_D.getTime() - to_D.getTime();
			dataCount = (int) (data_mm / 1000 / 60 / 60 / 24);
		} catch (Exception e) {
			System.out.println(e);
		}
		return dataCount;
	}

	// 计算两个日期间的间隔天数
	public static int getDaysFromTwoDate(String txtDate1, String txtDate2) {
		if (txtDate1 == null || txtDate1.equals("")) {
			return 0;
		}
		if (txtDate2 == null || txtDate2.equals("")) {
			return 0;
		}

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int days = 0;
		try {
			Date date1 = sDateFormat.parse(txtDate1);
			Date date2 = sDateFormat.parse(txtDate2);
			days = (int) (date2.getTime() - date1.getTime())
					/ (24 * 60 * 60 * 1000); // 通过getTime()方法，把时间Date转换成毫秒格式Long类型，进行计算
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return days + 1;
	}

	/*
	 * 检查日期字符串有效性 日期字符串为10位YYYY-MM-DD
	 */
	public static boolean checkDate(String str) {
		int year;
		int month;
		int day;
		int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (null == str || "".equals(str)) {
			return true;
		}

		if (!str.matches("\\d{4}-[01]\\d-[0123]\\d")) {
			return false;
		}
		year = Integer.parseInt(str.substring(0, 4));
		month = Integer.parseInt(str.substring(5, 7));
		day = Integer.parseInt(str.substring(8, 10));

		// Judge leap year.
		if (((0 == year % 4) && (0 != year % 100)) || (0 == year % 400)) {
			days[1]++;
		}
		// process month
		if (month < 1 || month > 12) {
			return false;
		}
		// process day
		if (day < 1 || day > days[month - 1]) {
			return false;
		}
		return true;
	}

	public static Date formatDate(String date) throws ParseException {
		Date time = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		time = format.parse(date);

		return time;

	}

	public static Date getPreviousDate(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - n);
		return calendar.getTime();
	}

	public static Date getBackDate(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day + n);
		return calendar.getTime();
	}

	public static String formatDateToString(Date date) {
		String formDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		formDate = format.format(date);

		return formDate;
	}

	public static String formatDateToStringSe(Date date) {
		String formDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		formDate = format.format(date);

		return formDate;
	}

	/**
	 * 时间比较
	 * 
	 * d1在数字上小于、等于或大于 d2 时，返回 -1、0 或 1
	 * 
	 */
	public static Integer compareDate(Date d1, Date d2) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Integer int1 = Integer.parseInt(format.format(d1));
			Integer int2 = Integer.parseInt(format.format(d2));
			if (int1 > int2) {
				return 1;
			} else if (int1 < int2) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static Integer compareDateSS(Date d1, Date d2) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Integer int1 = Integer.parseInt(format.format(d1));
			Integer int2 = Integer.parseInt(format.format(d2));
			if (int1 > int2) {
				return 1;
			} else if (int1 < int2) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 判断是否是润年
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("d==="+getDayOfMonth(2008,8,30,6));
		System.out.println(DateTimeUtil.toStringDate(new Date(1382694957*1000L), "yyyy-MM-dd HH:mm:ss"));
		//long loanDays = DateTimeUtil.dataCount("2008-03-02", "2008-02-01");
		// new Date("2008-02-01 10:10:10").getTime();
		// DateTimeUtil.
		/*
		 * try {
		 * System.err.println(DateTimeUtil.compareDateSS(DateTimeUtil.toDate
		 * ("2008-02-01 10:10:10"),DateTimeUtil.toDate("2010-02-01 10:10:10")));
		 * } catch (Exception e) { // TODO 自动生成的 catch 块 e.printStackTrace(); }
		 */
	}
}
