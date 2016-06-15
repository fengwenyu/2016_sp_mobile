package com.shangpin.wireless.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static void main(String[] args) {
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// System.out.println(compareDate(sdf1.parse("2012-10-01 13:03:26.0"), sdf1.parse("2012-10-02 02:00:00")));
			System.out.println(getDateOfLastMonth());
			System.out.println(getLastMonth("2011-05-31", 0, -1, 0));// 2010-12
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date five = sdf.parse("2013-05-01");
			System.out.println(getAfterAmountDay(five, 31, "yyyy-MM-dd"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = sdf.format(new Date());
		return dt;
	}		

	/**
	 * 比较时分秒
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-02-04
	 * @param firstDate
	 *            日期
	 * @param secondDate
	 *            日期
	 * @return int
	 * @throws ParseException
	 */
	public static Integer compareDate(Date firstDate, Date secondDate) throws ParseException {
		Calendar first = Calendar.getInstance();
		first.setTime(firstDate);
		int firstHour = first.get(Calendar.HOUR_OF_DAY);
		int firstMinute = first.get(Calendar.MINUTE);
		int firstSecond = first.get(Calendar.SECOND);
		Calendar second = Calendar.getInstance();
		second.setTime(secondDate);
		int secondHour = second.get(Calendar.HOUR_OF_DAY);
		int secondMinute = second.get(Calendar.MINUTE);
		int secondSecond = second.get(Calendar.SECOND);
		if (firstHour > secondHour) {
			return 1;
		} else if (firstHour < secondHour) {
			return -1;
		} else {
			if (firstMinute > secondMinute) {
				return 1;
			} else if (firstMinute < secondMinute) {
				return -1;
			} else {
				if (firstSecond > secondSecond) {
					return 1;
				} else if (firstSecond < secondSecond) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	
	/**
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-30
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的天数，如果是向前的天数，使用负数就可以.
	 * @param format
	 *            输出格式.
	 * @return Date 加上一定天数以后的Date对象
	 */
	public static String getAfterAmountDay(Date date, int amount, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, amount);
		return sdf.format(cal.getTime());
	}

	/**
	 * 将日期转成星期
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-30
	 * @param date
	 *            给定的日期对象
	 * @return String 返回当前日期所对应的星期
	 */
	public static String dateToWeek(Date date) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		// System.out.println(weekDaysName[intWeek]);
		return weekDaysName[intWeek];
	}

	// 上个月的现在
	public static String getDateOfLastMonth() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar aGregorianCalendar = new GregorianCalendar();
		aGregorianCalendar.set(Calendar.MONTH, aGregorianCalendar.get(Calendar.MONTH) - 1);
		String nowOfLastMonth = sdf.format(aGregorianCalendar.getTime());
		return nowOfLastMonth;
	}

	/**
	 * 获取指定月的前一月（年）或后一月（年）
	 * 
	 * @param dateStr
	 * @param addYear
	 * @param addMonth
	 * @param addDate
	 * @return 输入的时期格式为yyyy-MM-dd，输出的日期格式为yyyy-MM-dd
	 * @throws Exception
	 */
	public static String getLastMonth(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			java.util.Date sourceDate = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);
			cal.add(Calendar.YEAR, addYear);
			cal.add(Calendar.MONTH, addMonth);
			cal.add(Calendar.DATE, addDate);
			java.text.SimpleDateFormat returnSdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String dateTmp = returnSdf.format(cal.getTime());
			java.util.Date returnDate = returnSdf.parse(dateTmp);
			return dateTmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

}
